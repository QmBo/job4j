package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
/**
 * SimpleInputReader
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 01.03.2019
 */
public class SimpleInputReader {
    /**
     * Logger.
     */
    private static final Logger LOG = LogManager.getLogger(SimpleInputReader.class);

    /**
     * Check number fo event.
     * @param in numbers stream.
     * @return iv event.
     */
    public boolean isNumber(InputStream in) {
        boolean result = false;
        try (ByteArrayInputStream reader = (ByteArrayInputStream) in) {
            int number = reader.read();
            if (number % 2 == 0) {
                result = true;
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return result;
    }
}
