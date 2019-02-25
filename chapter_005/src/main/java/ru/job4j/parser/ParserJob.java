package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.quartz.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;

import static java.lang.String.format;

/**
 * ParserJob
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 13.02.2019
 */
public class ParserJob implements Job {
    /**
     * Logger.
     */
    private static final Logger LOG = LogManager.getLogger(ParserJob.class);
    /**
     * Vacancy.
     */
    private Map<String, String> vacancy = new LinkedHashMap<>(100);
    /**
     * Decryption.
     */
    private Map<String, String> decryption = new HashMap<>(100);
    /**
     * Dead line.
     */
    private Date lastStart;
    /**
     * Data base connection.
     */
    private Connection connection;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        LOG.info("Execute!");
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        this.connection = (Connection) dataMap.get("Connection");
        LOG.debug("LastStart is {}", this.lastStart);
        this.lastStart = jobExecutionContext.getPreviousFireTime();
        LOG.info("LastStart set {}", this.lastStart);
        if (this.lastStart == null) {
            this.lastStart = (Date) dataMap.get("defaultBreakDate");
            LOG.info("LastStart set default {}", this.lastStart);
        }
        int pages = this.countPage();
        if (pages > 0) {
            this.dataAnalyser(pages);
            if (this.vacancy.size() > 0) {
                LOG.info("Total vacancy found is {}.", this.vacancy.size());
                this.dbWork();
            } else {
                LOG.info("Vacancy not found.");
            }
        }
        LOG.info("Next star {}", jobExecutionContext.getNextFireTime());
    }

    /**
     * Page counter.
     * @return number of pages.
     */
    public int countPage() {
        int result = -1;
        try {
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
            Elements elements = doc.getElementsByClass("sort_options");
            String text = elements.last().getElementsByTag("td").first().text();
            LOG.debug(text);
            String[] split = text.split("[.][.] ");
            if (split.length == 2) {
                int pages = Integer.parseInt(split[1]);
                LOG.info("Page found {}", pages);
                result = pages;
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
        return result;
    }

    /**
     * Page reader.
     * @param pages number of pages.
     */
    private void dataAnalyser(int pages) {
        for (int i = 1; i <= pages; i++) {
            readPage(format("https://www.sql.ru/forum/job-offers/%s", i));
        }
    }

    /**
     * Page reader. Vacancy finder.
     * @param html link to read page.
     */
    private void readPage(String html) {
        try {
            Document doc = Jsoup.connect(html).get();
            Elements elements = doc.getElementsByClass("postslisttopic");
            for (Element e : elements) {
                String name = e.text().replace("[new]", "");
                String link = e.select("a[href]").get(0).attr("href");
                LOG.debug(name);
                LOG.debug(e.select("a[href]").get(0).attr("href"));
                this.checkVacancy(name, link);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }

    /**
     * Vacancy validator.
     * @param name name of vacancy.
     * @param link linc of vacancy.
     */
    private void checkVacancy(String name, String link) {
        if (name != null && link != null) {
            String workName = name.toLowerCase();
            if (workName.contains("java")
                    && !workName.contains("javasc") && !workName.contains("java sc")) {
                if (this.dataCheck(link)) {
                    LOG.info("Vacancy \"{}\" found.", name);
                    this.vacancy.putIfAbsent(name, link);
                }
            }
        }
    }

    /**
     * Validator helper. Check dead line.
     * @param link linc of vacancy.
     * @return suits?
     */
    private boolean dataCheck(String link) {
        boolean result = false;
        try {
            Document doc = Jsoup.connect(link).get();
            Elements elements = doc.getElementsByClass("msgFooter");
            String[] text = elements.get(0).text().split(" \\[");
            LOG.debug("Date {}", text[0]);
            String date = text[0];
            SimpleDateFormat sfReader = new SimpleDateFormat("d MMM yy, HH:mm", Locale.getDefault());
            SimpleDateFormat sfWriter = new SimpleDateFormat("d MMM yy", Locale.getDefault());
            Calendar calendar = new GregorianCalendar();
            if (date.startsWith("сегодня")) {
                date = date.replace("сегодня", sfWriter.format(calendar.getTime()));
                LOG.debug("Date {}", date);
            } else if (date.startsWith("вчера")) {
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                date = date.replace("вчера", sfWriter.format(calendar.getTime()));
                LOG.debug("Date {}", date);
            }
            Date vacancyTime = sfReader.parse(date);
            LOG.debug("Compare {}", vacancyTime.compareTo(this.lastStart));
            if (vacancyTime.compareTo(this.lastStart) > 0) {
                result = true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
        return result;
    }

    /**
     * Writ vacancy in data base if absent.
     */
    private void dbWork() {
        LOG.debug("Vacancy {}", this.vacancy);
        try {
            try (PreparedStatement st = connection.prepareStatement(
                    "select count(*) as c from vacancy where name = ?")) {
                for (String name : this.vacancy.keySet()) {
                    st.setString(1, name);
                    LOG.debug("Vacancy name {}", name);
                    ResultSet resultSet = st.executeQuery();
                    while (resultSet.next()) {
                        if (resultSet.getInt("c") == 0) {
                            this.getDecryption(name);
                        }
                    }
                }
            }
            connection.setAutoCommit(false);
            try (PreparedStatement pst = connection.prepareStatement(
                    "insert into vacancy (name, text, link) values (?, ?, ?)")) {
                int count = 0;
                int batchSize = 1000;
                for (String name : this.decryption.keySet()) {
                    pst.setString(1, name);
                    pst.setString(2, this.decryption.get(name));
                    pst.setString(3, this.vacancy.get(name));
                    pst.addBatch();
                    if (++count % batchSize == 0) {
                        pst.executeBatch();
                    }
                }
                pst.executeBatch();
                connection.commit();
            }
            connection.setAutoCommit(true);
            LOG.info("{} Vacancy add.", this.decryption.size());
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }

    /**
     * Get vacancy decryption.
     * @param name name of vacancy.
     */
    private void getDecryption(String name) {
        try {
            Document doc = Jsoup.connect(this.vacancy.get(name)).get();
            Elements element = doc.getElementsByClass("msgBody");
            List<Node> dec = element.get(1).childNodes();
            StringBuilder stringBuilder = new StringBuilder();
            for (Node node : dec) {
                stringBuilder.append(node.toString());
                stringBuilder.append(" ");
            }
            String text = stringBuilder.toString();
            if (text.length() > 3999) {
                text = format("%s %s", text.substring(0, 3990), "...");
            }
            LOG.info("Vacancy {} add text.", name);
            LOG.debug("Text {}", text);
            this.decryption.put(name, text);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }
}