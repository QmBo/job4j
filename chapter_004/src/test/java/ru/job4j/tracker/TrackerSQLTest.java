package ru.job4j.tracker;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {
    private static final Logger LOG = LoggerFactory.getLogger(TrackerSQL.class);

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "desc"));
            assertThat(tracker.findByName("name").size(), is(1));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenGetAllThenTestItem() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item(
                    "Test", "This is test description.",
                    1514783471000L
            );
            item.setId("1");
            assertThat(tracker.getAll().get(0), is(item));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void whenFindByNameThenTestItem() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item(
                    "Found item", "This Found item is test description.",
                    1514783471000L
            );
            item.setId(tracker.add(item).getId());
            List<Item> result = tracker.findByName("oun");
            assertThat(result.get(result.size() - 1), is(item));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void whenFindByIdThenTestItem() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item(
                    "Test", "This is test description.",
                    1514783471000L
            );
            item.setId("1");
            assertThat(tracker.findById("1"), is(item));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }


    @Test
    public void whenReplaceItemThenNewDescription() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
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
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void whenItemDeleteThenItemNotFound() {
        try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item item = new Item(
                    "Delete Item", "This is Delete item description.", 1514783471000L
            );
            item.setId(tracker.add(item).getId());
            tracker.delete(item.getId());
            assertNull(tracker.findById(item.getId()));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}