package ru.job4j.bot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
/**
 * Client
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 20.03.2019
 */
public class Client {
    private static final Logger LOG = LogManager.getLogger(Client.class);
    /**
     * Exit command.
     */
    private static final String EXIT = "exit";
    /**
     * Socket.
     */
    private final Socket socket;
    /**
     * Scanner.
     */
    private final Scanner console;

    /**
     * Constructor.
     * @param socket socket.
     * @param console scanner.
     */
    public Client(Socket socket, Scanner console) {
        this.socket = socket;
        this.console = console;
    }

    /**
     * Start point.
     * @param args non supported.
     */
    public static void main(String[] args) {
        try (
                Socket inSocket = new Socket(InetAddress.getLoopbackAddress(), 48900);
                Scanner scanner = new Scanner(System.in)
        ) {
            new Client(inSocket, scanner).start();
        } catch (Exception e) {
            LOG.error("error", e);
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
                ask = this.console.nextLine();
                out.println(ask);
                if (in.ready()) {
                    String str = in.readLine();
                    while (!str.isEmpty()) {
                        System.out.println(str);
                        str = in.readLine();
                    }
                }
            } while (!EXIT.equals(ask));
        } catch (Exception e) {
            LOG.error("error", e);
        }
    }
}
