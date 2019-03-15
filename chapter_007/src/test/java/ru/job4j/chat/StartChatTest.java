package ru.job4j.chat;

import org.junit.After;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * StartChatTest
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 15.03.2019
 */
public class StartChatTest {
    private String name = "test.txt";
    String path = format("%s/%s", System.getProperty("java.io.tmpdir"), "ChatTest");

    @After
    public void unSetUp() {
        new File(format("%s/%s", path, name)).delete();
        new File(path).delete();
    }

    @Test
    public void whenTestWordThenReturnTestWord() {
        ChatLogger logger = new ChatLogger(path);
        logger.init(name);
        StartChat chat = new StartChat(
                new BufferedReader(
                        new InputStreamReader(
                                new ByteArrayInputStream("Test Line".getBytes())
                        )
                ),
                new Answers("chat/TestAnswers.txt"),
                logger
        );
        assertThat(chat.start(), is("Test Line"));
    }
}