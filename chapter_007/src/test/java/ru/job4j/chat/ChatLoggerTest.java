package ru.job4j.chat;

import org.junit.After;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * ChatLoggerTest
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 15.03.2019
 */
public class ChatLoggerTest {
    private String name = "test.txt";
    String path = format("%s/%s", System.getProperty("java.io.tmpdir"), "ChatTest");

    @After
    public void unSetUp() {
        new File(format("%s/%s", path, name)).delete();
        new File(path).delete();
    }

    @Test
    public void whenInitThenCreateFile() {
        ChatLogger logger = new ChatLogger(path);
        assertThat(logger.init(name).exists(), is(true));
    }

    @Test
    public void whenWriteThenCreateFileAndWrite() throws Exception {
        ChatLogger logger = new ChatLogger(path);
        logger.init(name);
        String line = "Test Line";
        String res;
        logger.write(line);
        try (
                BufferedReader bf = new BufferedReader(
                        new FileReader(
                                new File(format("%s/%s", path, name))
                        )
                )
        ) {
            res = bf.readLine();
        }
        assertThat(res, is(line));
    }
}