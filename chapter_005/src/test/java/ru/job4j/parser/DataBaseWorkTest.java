package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.sql.*;
import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class DataBaseWorkTest {
private static final Logger LOG = LogManager.getLogger(DataBaseWorkTest.class);


    @Test
    public void whenDoubleThenAddFirst() {
        int count = -1;
        Config config = new Config();
        config.init();
        try {
            Class.forName(config.get("jdbc.driver"));
            try (Connection connection = DriverManager.getConnection(
                    config.get("jdbc.url"),
                    config.get("jdbc.username"),
                    config.get("jdbc.password")
            )) {
                HashMap<String, String> vac = new HashMap<>();
                HashMap<String, String> dec = new HashMap<>();
                vac.put("test name", "test link");
                vac.put("test name1", "test link");
                dec.put("test name", "test dec");
                dec.put("test name1", "test dec");
                new DataBaseWork(connection, vac, dec);
                vac.put("test name2", "test link");
                dec.put("test name2", "test dec");
                new DataBaseWork(connection, vac, dec);
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT name, count(name) from vacancy group by name having count(name) > 1");
                count++;
                while (rs.next()) {
                    count++;
                }
                st.executeUpdate("delete from vacancy where text = 'test dec'");
            }
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
        assertThat(count, is(0));
    }

}