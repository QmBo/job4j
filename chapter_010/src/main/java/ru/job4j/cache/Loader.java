package ru.job4j.cache;

import com.google.common.base.Joiner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
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
    public String getCache(String name) {
        String result = "";
        if (!this.capacity.containsKey(name)) {
            try (Stream<String> lines = Files.lines(Path.of(name))) {
                result = Joiner.on(LS).join(lines.toArray());
                System.out.println("LOAD");
                this.newItem(name, new Item<>(result));
            } catch (IOException e) {
                LOG.error("message", e);
            }
        } else {
            Item<String> item = this.capacity.get(name).get();
            result = Objects.requireNonNull(item).getObject();
            System.out.println("RECall");
        }
        return result;
    }
}
