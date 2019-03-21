package ru.job4j.chat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;

import static java.lang.String.format;
/**
 * ChatLogger
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 15.03.2019
 */
public class ChatLogger {
    private static final Logger LOG = LogManager.getLogger(ChatLogger.class);
    /**
     * Path.
     */
    private final String path;
    /**
     * File to log.
     */
    private File log;

    /**
     * Constructor.
     */
    public ChatLogger() {
        this(format("%s/%s", System.getProperty("java.io.tmpdir"), "Chat"));
    }

    /**
     * Constructor.
     * @param path path to file log.
     */
    public ChatLogger(String path) {
        this.path = path;
    }

    /**
     * Setup the logger.
     * @param name name to file to log.
     * @return log file.
     */
    public File init(String name) {
        new File(this.path).mkdirs();
        this.log = new File(format("%s/%s", this.path, name));
        if (!log.exists()) {
            try {
                log.createNewFile();
            } catch (IOException e) {
                LOG.error("message", e);
            }
        }
        return this.log;
    }

    /**
     * Write lines to file.
     * @param lines lines to write.
     */
    public void write(String lines) {
        try (FileWriter writer = new FileWriter(this.log, true)) {
            if (this.log.canWrite()) {
                writer.write(lines);
            }
        } catch (IOException e) {
            LOG.error("message", e);
        }
    }
}
