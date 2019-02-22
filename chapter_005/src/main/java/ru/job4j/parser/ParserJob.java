package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;

import java.sql.Connection;
import java.util.*;


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
     * Main job.
     * @param jobExecutionContext job settings.
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        LOG.info("Execute!");
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        Date lastStart = jobExecutionContext.getPreviousFireTime();
        LOG.info("LastStart set {}", lastStart);
        if (lastStart == null) {
            lastStart = (Date) dataMap.get("defaultBreakDate");
            LOG.info("LastStart set default {}", lastStart);
        }
        VacancyParser parser = new VacancyParser(lastStart);
        parser.startParser();
        Map<String, String> vacancy = parser.getVacancy();
        Map<String, String> decryption = parser.getDecryption();
        if (vacancy.size() > 0) {
            LOG.info("Total vacancy found is {}.", vacancy.size());
            new DataBaseWork((Connection) dataMap.get("Connection"),
                    vacancy, decryption);
        } else {
            LOG.info("Vacancy not found.");
        }
        LOG.info("Next star {}", jobExecutionContext.getNextFireTime());
    }
}