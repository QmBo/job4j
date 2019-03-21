package ru.job4j.bot;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * ClientTest
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 20.03.2019
 */
public class ClientTest {

    private static final String LS = System.lineSeparator();

    private void testClient(String input, String scan, String exp) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        when(socket.getOutputStream()).thenReturn(out);
        when(socket.getInputStream()).thenReturn(in);
        Scanner scanner = new Scanner(scan);
        Client client = new Client(socket, scanner);
        client.start();
        assertThat(in.read(), is(-1));
        assertThat(String.valueOf(out), is(exp));
    }

    @Test
    public void whenExitThenStop() throws IOException {
        this.testClient(
                Joiner.on(LS).join("Hello.", "Hi", "   Test", "", ""),
                "exit",
                Joiner.on(LS).join("exit", "")
        );
    }

    @Test
    public void whenLineAndExitThenLineAndStop() throws Exception {
        this.testClient(
                Joiner.on(LS).join("Hello.", "Hi", "   Test", "", ""),
                Joiner.on(LS).join("hi", "bye", "exit"),
                Joiner.on(LS).join("hi", "bye", "exit", "")
        );
    }
}
