package ru.job4j.bot;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * ServerTest
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 20.03.2019
 */
public class ServerTest {
    private static final String LS = System.lineSeparator();

    @Test
    public void whenHelloExitThenReturnLine() throws IOException {
        this.testServer(
                Joiner.on(LS).join("hello", "exit"),
                Joiner.on(LS).join(
                        "Hello, dear friend, I'm a oracle.",
                        "",
                        "Goodbye, my dear friend.",
                        "",
                        ""
                )
        );
    }

    @Test
    public void whenNoHelloExitThenReturnLine() throws IOException {
        this.testServer(
                Joiner.on(LS).join("I AM GROOT", "exit"),
                Joiner.on(LS).join(
                        "Pleas repeat.",
                        "",
                        "Goodbye, my dear friend.",
                        "",
                        ""
                )
        );
    }

    @Test
    public void whenExitThenGoodbye() throws IOException {
        this.testServer(
                "exit",
                Joiner.on(LS).join(
                        "Goodbye, my dear friend.",
                        "",
                        ""
                )
        );
    }

    public void testServer(String in, String exp) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Socket socket = mock(Socket.class);
        when(socket.getOutputStream()).thenReturn(outputStream);
        when(socket.getInputStream()).thenReturn(new ByteArrayInputStream(in.getBytes()));
        new Server(socket).start();
        assertThat(String.valueOf(outputStream), is(exp));
    }
}