package ru.job4j.array;


import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.*;
/**
 * StoreSQL
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 18.01.2019
 */
public class StoreSQL implements Closeable {
    /**
     * Connection to SQL.
     */
    private Connection conn;
    /**
     * Config.
     */
    private final Config config;

    /**
     * Constructor.
     * @param config config.
     */
    public StoreSQL(final Config config) {
        this.config = config;
        this.config.init();
        this.init();
    }

    /**
     * Init Connection.
     * @return init is ok.
     */
    public boolean init() {
        try {
            this.conn = DriverManager.getConnection(
                    this.config.get("url"),
                    this.config.get("username"),
                    this.config.get("password")
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return this.conn != null;
    }

    /**
     * Generator n writs.
     * @param n number of writes.
     */
    public void generate(int n) {
        try (Statement stmt = this.conn.createStatement()) {
            stmt.executeUpdate("create table if not exists entry (field integer)");
            this.checkTab();
            this.conn.setAutoCommit(false);
            try {
                for (int i = 1; i <= n; i++) {
                    stmt.execute(format("insert into entry (field) values (%s)", i));
                }
                this.conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                this.conn.rollback();
            }
            this.conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Counter of writs.
     * @param tabName name of tab.
     * @return count.
     */
    public int count(String tabName) {
        int result = -1;
        try (Statement st = this.conn.createStatement()) {
            ResultSet rs = st.executeQuery(format("select count(*) as count from %s", tabName));
            while (rs.next()) {
                result = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Generator helper.
     */
    private void checkTab() {
        int count = this.count("entry");
        if (count != 0) {
            try (Statement statement = this.conn.createStatement()) {
                statement.executeUpdate("drop table entry");
                statement.executeUpdate("create table entry (field integer)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Write List of Entrys.
     * @return list of entrys.
     */
    public List<Entry> toList() {
        List<Entry> result = new ArrayList<>(2000000);
        try (Statement statement = this.conn.createStatement();
             ResultSet set = statement.executeQuery("select * from entry")) {
            while (set.next()) {
                result.add(new Entry(set.getInt("field")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Close.
     */
    @Override
    public void close() {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
