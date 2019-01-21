package ru.job4j.array;

import java.io.InputStream;
import java.util.Properties;
/**
 * Config
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 18.01.2019
 */
public class Config {
    private final Properties values = new Properties();

    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("array/app.properties")) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}
