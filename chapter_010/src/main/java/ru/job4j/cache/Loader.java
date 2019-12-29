package ru.job4j.cache;

import com.google.common.base.Joiner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Loader
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 27.12.2019
 */
public class Loader extends Cache<String> {
    private static final Logger LOG = LogManager.getLogger(Loader.class);
    private static final String LS = System.lineSeparator();

    /**
     * Load cache if absent.
     * @param name name.
     * @return string from cache.
     */
    @Override
    public String getCache(final String name) {
        Item<String> item;
        String result;
        if (!this.capacity.containsKey(name) || this.capacity.get(name).get() == null) {
            item = this.load(name);
            result = item.getObject();
        } else {
            item = this.capacity.get(name).get();
            if (item == null) {
                item = load(name);
            }
            result = item.getObject();
        }
        return result;
    }

    /**
     * Cache loader.
     * @param name fil name.
     * @return load item.
     */
    private Item<String> load(final String name) {
        Item<String> result = null;
        try (Stream<String> lines = Files.lines(Path.of(name))) {
            String joinLines = Joiner.on(LS).join(lines.toArray());
            result = new Item<>(joinLines);
            this.newItem(name, new Item<>(joinLines));
        } catch (IOException e) {
            LOG.error("message", e);
        }
        return result;
    }
}
