package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Map;

public class DataBaseWork {
    private static final Logger LOG = LogManager.getLogger(DataBaseWork.class);
    private final Map<String, String> vacancy;
    private final Connection connection;
    private final Map<String, String> decryption;

    public DataBaseWork(final Connection connection, final Map<String, String> vacancy,
                        final Map<String, String> decryption) {
        this.connection = connection;
        this.vacancy = vacancy;
        this.decryption = decryption;
        this.dbWork();
    }

    /**
     * Writ vacancy in data base if absent.
     */
    private void dbWork() {
        LOG.debug("Vacancy {}", this.vacancy);
        try (PreparedStatement pst = connection.prepareStatement(
                "insert into vacancy (name, text, link) values (?, ?, ?)")) {
            int count = 0;
            connection.setAutoCommit(false);
            Savepoint savepoint;
            for (String name : this.vacancy.keySet()) {
                savepoint = this.connection.setSavepoint("Save Point");
                pst.setString(1, name);
                pst.setString(2, this.decryption.get(name));
                pst.setString(3, this.vacancy.get(name));
                pst.addBatch();
                count++;
                try {
                    pst.executeBatch();
                } catch (SQLException e) {
                    LOG.info("Vacancy {} already exists !!!! Roll back !!!!", name);
                    count--;
                    this.connection.rollback(savepoint);
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            LOG.info("{} Vacancy add.", count);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
