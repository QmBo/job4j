package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.String.format;

public class VacancyParser {
    private static final Logger LOG = LogManager.getLogger(VacancyParser.class);
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

    public VacancyParser(Date lastStart) {
        this.lastStart = lastStart;
    }

    /**
     * Starter.
     */
    public void startParser() {
        int pages = this.countPage();
        if (pages > 0) {
            this.dataAnalyser(pages);
        } else {
            LOG.info("Page not found. {}", pages);
        }
    }

    /**
     * Vacancy getter.
     * @return map name/link.
     */
    public Map<String, String> getVacancy() {
        return this.vacancy;
    }

    /**
     * Decryption getter.
     * @return map name/decryption.
     */
    public Map<String, String> getDecryption() {
        return this.decryption;
    }

    /**
     * Page counter.
     * @return number of pages.
     */
    private int countPage() {
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
                    this.getTextDecryption(name);
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
        }
        return result;
    }

    /**
     * Get vacancy decryption.
     * @param name name of vacancy.
     */
    private void getTextDecryption(String name) {
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
        }
    }
}
