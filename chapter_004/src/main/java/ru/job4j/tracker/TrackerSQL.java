package ru.job4j.tracker;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * TrackerSQL
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 03.12.2018
 */
public class TrackerSQL implements ITracker, Closeable {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(TrackerSQL.class);
    /**
     * Data base connection.
     */
    private Connection connection;
    /**
     * Time to exit.
     */
    private boolean exit = false;

    /**
     * Tack Connection.
     * @return is connecting.
     */
    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            try {
                Class.forName(config.getProperty("driver-class-name"));
                this.connection = DriverManager.getConnection(
                        config.getProperty("url"),
                        config.getProperty("username"),
                        config.getProperty("password")
                );
                try (Statement st = this.connection.createStatement()) {
                    ResultSet result = st.executeQuery("select count(*) from information_schema.tables where table_schema='public'");
                    result.next();
                    String tabCount = config.getProperty("tab-count");
                    if (!result.getString("count").equals(tabCount)) {
                        this.reInit(config, true);
                    }
                }
            } catch (PSQLException pe) {
                if (pe.getMessage().contains("\"tracker\"")) {
                    this.reInit(config, false);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    /**
     * Rebuild data base.
     * @param config data base config.
     * @param drop drop DB if true.
     * @throws SQLException if bad connection.
     */
    private void reInit(Properties config, boolean drop) throws SQLException {
        this.connection = DriverManager.getConnection(
                config.getProperty("url-create"),
                config.getProperty("username"),
                config.getProperty("password")
        );
        if (drop) {
            try (Statement st = this.connection.createStatement()) {
                st.execute("SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'tracker'");
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
            try (Statement st = this.connection.createStatement()) {
                st.execute("DROP DATABASE tracker");
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        try (Statement st = this.connection.createStatement()) {
            st.execute("CREATE DATABASE tracker");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        this.connection = DriverManager.getConnection(
                config.getProperty("url"),
                config.getProperty("username"),
                config.getProperty("password")
        );
        this.createTab();
    }

    /**
     * Create DB.
     */
    private void createTab() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("create.sql")) {
            try (Statement st = this.connection.createStatement()) {
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                    line = reader.readLine();
                    while (line != null) {
                        stringBuilder.append(line);
                        line = reader.readLine();
                    }
                }
                String[] sql = stringBuilder.toString().split(";");
                for (String q : sql) {
                    st.executeUpdate(q);
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Add item in array.
     * @param item item to add.
     * @return item with id.
     */
    @Override
    public Item add(Item item) {
        ArrayList<Item> result = this.sqlRequest(
                String.format(
                        "insert into item (name, description, create_time) values ('%s', '%s', %s) RETURNING *",
                        item.getName(), item.getDescription(), item.getCreated()
                )
        );
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     * Replace item by id.
     * @param id id.
     * @param item item.
     */
    @Override
    public void replace(String id, Item item) {
        this.sqlRequest(
                String.format(
                        "update item set name = '%s', description = '%s', create_time = '%s' where id = %s RETURNING *",
                        item.getName(), item.getDescription(), item.getCreated(), id
                )
        );
    }

    /**
     * Deleted item from array by id.
     * @param id id.
     */
    @Override
    public void delete(String id) {
        this.sqlRequest(
                String.format(
                        "delete from item where id = %s RETURNING *", id
                )
        );
    }

    /**
     * Return copy of all items.
     * @return all items.
     */
    @Override
    public ArrayList<Item> getAll() {
        return this.sqlRequest("select * from item");
    }

    /**
     * Find item by name.
     * @param key name.
     * @return all item with name.
     */
    @Override
    public ArrayList<Item> findByName(String key) {
        return this.sqlRequest(String.format("select * from item where name like '%%%s%%'", key));
    }

    /**
     * Find item by id.
     * @param id id.
     * @return all item with id.
     */
    @Override
    public Item findById(String id) {
        ArrayList<Item> result = this.sqlRequest(String.format("select * from item where id = %s", id));
        return result.isEmpty() ? null : result.get(0);
    }

    /**
     * Check is exit time.
     * @return isExit.
     */
    @Override
    public boolean isExit() {
        return this.exit;
    }

    /**
     * Set exit flag true.
     */
    @Override
    public void timeToExit() {
        this.exit = true;
    }

    /**
     * SQL Requester.
     * @param sql request.
     * @return list of items at request.
     */
    private ArrayList<Item> sqlRequest(String sql) {
        ArrayList<Item> result = new ArrayList<>(100);
        this.init();
        try (Statement st = this.connection.createStatement()) {
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    Item item = new Item(
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getLong("create_time")
                    );
                    item.setId(rs.getString("id"));
                    result.add(item);
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Closer.
     * @throws IOException exception.
     */
    @Override
    public void close() throws IOException {
        try {
            this.connection.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw new IOException(e);
        }
    }
}
