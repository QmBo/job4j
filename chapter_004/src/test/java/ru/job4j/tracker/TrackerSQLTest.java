package ru.job4j.tracker;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {
    private static final Logger LOG = LoggerFactory.getLogger(TrackerSQL.class);

    @Test
    public void checkConnection() {
        TrackerSQL sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
    }

    @Test
    public void whenNoDBThenCreate() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Connection connection = DriverManager.getConnection(
                    config.getProperty("url-create"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            try (Statement st = connection.createStatement()) {
                st.execute("SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'tracker'");
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
            try (Statement st = connection.createStatement()) {
                st.execute("DROP DATABASE tracker");
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        } catch (IOException | SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        try (TrackerSQL sql = new TrackerSQL()) {
            assertThat(sql.init(), is(true));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void whenNoTabsThenCreate() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Connection connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            try (Statement st = connection.createStatement()) {
                st.execute("drop table users CASCADE ");
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        } catch (IOException | SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        try (TrackerSQL sql = new TrackerSQL()) {
            assertThat(sql.init(), is(true));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void whenGetAllThenTestItem() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            Item item = new Item(
                    "Test", "This is test description.",
                    1514783471000L
            );
            item.setId("1");
            assertThat(tracker.getAll().get(0), is(item));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void whenFindByNameThenTestItem() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            Item item = new Item(
                    "Found item", "This Found item is test description.",
                    1514783471000L
            );
            item.setId(tracker.add(item).getId());
            List<Item> result = tracker.findByName("oun");
            assertThat(result.get(result.size() - 1), is(item));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void whenFindByIdThenTestItem() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            Item item = new Item(
                    "Test", "This is test description.",
                    1514783471000L
            );
            item.setId("1");
            assertThat(tracker.findById("1"), is(item));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void whenAddItemThenItemInDB() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            Item item = new Item(
                    "Item2", "This is second test description.", 1514783471000L
            );
            item.setId(tracker.add(item).getId());
            assertThat(tracker.findById(item.getId()), is(item));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void whenReplaceItemThenNewDescription() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            Item item = new Item(
                    "Item", "This is test item description.", 1514783471000L
            );
            Item replaceItem = new Item(
                    "Item", "This is new test item description.", 1514783472000L
            );
            item.setId(tracker.add(item).getId());
            tracker.replace(item.getId(), replaceItem);
            replaceItem.setId(item.getId());
            assertThat(tracker.findById(item.getId()), is(replaceItem));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void whenItemDeleteThenItemNotFound() {
        try (TrackerSQL tracker = new TrackerSQL()) {
            Item item = new Item(
                    "Delete Item", "This is Delete item description.", 1514783471000L
            );
            item.setId(tracker.add(item).getId());
            tracker.delete(item.getId());
            assertNull(tracker.findById(item.getId()));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}