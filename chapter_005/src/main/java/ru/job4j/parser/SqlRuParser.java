package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.Date;

import static java.lang.String.format;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
/**
 * SqlRuParser
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 13.02.2019
 */
public class SqlRuParser {
    /**
     * Logger
     */
    private static final Logger LOG = LogManager.getLogger(SqlRuParser.class.getName());
    /**
     * Config.
     */
    private final Config config;

    /**
     * Constructor.
     * @param config config.
     */
    public SqlRuParser(final Config config) {
        this.config = config;
        this.config.init();
        this.init();
    }

    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        new SqlRuParser(new Config());
    }

    /**
     * Init Job.
     */
    private void init() {
        try {
            Calendar cal = new GregorianCalendar();
            LOG.info("Time {}", cal.getTime());
            SimpleDateFormat sd = new SimpleDateFormat("dd/MM/YYYY");
            String[] years = cal.getTime().toString().split(" ");
            String year = years[years.length - 1];
            Date defaultBreakDate = sd.parse(format("01/01/%s", year));
            LOG.info("Start time set to {}", defaultBreakDate);
            Class.forName(this.config.get("jdbc.driver"));
            Connection connection = DriverManager.getConnection(
                    this.config.get("jdbc.url"),
                    this.config.get("jdbc.username"),
                    this.config.get("jdbc.password")
            );
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler scheduler = sf.getScheduler();
            HashMap<String, Object> map = new HashMap<>();
            map.put("Connection", connection);
            map.put("defaultBreakDate", defaultBreakDate);
            JobDataMap jobDataMap = new JobDataMap(map);
            scheduler.start();
            JobDetail job = newJob(ParserJob.class)
                    .withIdentity("SqlRuParser", "group1")
                    .usingJobData(jobDataMap)
                    .build();
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .withSchedule(cronSchedule(this.config.get("cron.time")))
                    .build();
            LOG.info("Connect.");
            scheduler.scheduleJob(job, trigger);
            LOG.info("Scheduler start.");
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(Long.MAX_VALUE);
            }
            scheduler.shutdown(true);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }
}