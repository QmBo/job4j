package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        try {
            try (PreparedStatement st = this.connection.prepareStatement(
                    "select count(*) as c from vacancy where name = ?")) {
                for (String name : this.vacancy.keySet()) {
                    st.setString(1, name);
                    LOG.debug("Vacancy name {}", name);
                    ResultSet resultSet = st.executeQuery();
                    while (resultSet.next()) {
                        if (resultSet.getInt("c") != 0) {
                            this.decryption.remove(name);
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
}
