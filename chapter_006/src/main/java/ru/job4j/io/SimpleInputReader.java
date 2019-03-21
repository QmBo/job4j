package ru.job4j.io;

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
            LOG.error("message", e);
        }
        return result;
    }

    /**
     * Looking exception words in stream word to word and delete it.
     * @param in input stream.
     * @param out output stream.
     * @param abuse exception words.
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            StringBuilder word = new StringBuilder();
            int code = br.read();
            while (code != -1) {
                char character = (char) code;
                word.append(character);
                if (character == ' ') {
                    String outString = word.toString();
                    if (outString.length() > 0) {
                        for (String abu : abuse) {
                            outString = outString.replace(abu, "");
                        }
                        out.write(outString.getBytes());
                        word = new StringBuilder();
                    }
                }
                code = br.read();
            }
        } catch (IOException e) {
            LOG.error("message", e);
        }
    }
}

