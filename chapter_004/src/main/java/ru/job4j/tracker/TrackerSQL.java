package ru.job4j.tracker;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * TrackerSQL
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 03.12.2018
 */
public class TrackerSQL implements ITracker, AutoCloseable {
    /**
     * Logger.
     */
    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class);
    /**
     * Data base connection.
     */
    private Connection connection;
    /**
     * Time to exit.
     */
    private boolean exit = false;

    /**
     * Constructor.
     */
    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    /**
     * Add item in array.
     * @param item item to add.
     * @return item with id.
     */
    @Override
    public Item add(Item item) {
        List<Item> result = this.sqlRequest(
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
    public List<Item> getAll() {
        return this.sqlRequest("select * from item");
    }

    /**
     * Find item by name.
     * @param key name.
     * @return all item with name.
     */
    @Override
    public List<Item> findByName(String key) {
        return this.sqlRequest(String.format("select * from item where name like '%%%s%%'", key));
    }

    /**
     * Find item by id.
     * @param id id.
     * @return all item with id.
     */
    @Override
    public Item findById(String id) {
        List<Item> result = this.sqlRequest(String.format("select * from item where id = %s", id));
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
    private List<Item> sqlRequest(String sql) {
        List<Item> result = new ArrayList<>(100);
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
