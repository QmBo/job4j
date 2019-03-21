package ru.job4j.bot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Server
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 20.03.2019
 */
public class Server {
    private static final Logger LOG = LogManager.getLogger(Server.class);
    /**
     * Hello command.
     */
    private static final String HELLO = "hello";
    /**
     * Exit command.
     */
    private static final String EXIT = "exit";
    /**
     * Socket.
     */
    private final Socket socket;

    /**
     * Constructor.
     * @param socket socket.
     */
    public Server(final Socket socket) {
        this.socket = socket;
    }

    /**
     * Start Point
     * @param args non supported.
     */
    public static void main(String[] args) {
        try (Socket socket = new ServerSocket(48900).accept()) {
            new Server(socket).start();
        } catch (IOException e) {
            LOG.error("message", e);
        }
    }

    /**
     * Logic.
     */
    public void start() {
        try (
                PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))
        ) {
            String ask;
            do {
                LOG.info("wait command ...");
                ask = in.readLine();
                LOG.info(ask);
                if (HELLO.equals(ask)) {
                    out.println("Hello, dear friend, I'm a oracle.");
                    out.println();
                } else if (!EXIT.equals(ask)) {
                    out.println("Pleas repeat.");
                    out.println();
                }
            } while (!EXIT.equals(ask));
            out.println("Goodbye, my dear friend.");
            out.println();
        } catch (IOException e) {
            LOG.error("message", e);
        }
    }
}
