package ru.job4j.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.String.format;
/**
 * EmailNotification
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 03.09.2018
 */
public class EmailNotification {
    /**
     * Pool.
     */
    private ExecutorService pool;

    /**
     * Constructor.
     */
    public EmailNotification() {
        this.pool = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
    }

    /**
     * Prepares email to send.
     * @param user user.
     */
    public void emailTo(User user) {
        String subject = format("Notification %s to %s", user.getUsername(), user.getEmail());
        String body = format("Add a new event to %s", user.getEmail());
        pool.submit(
                () -> {
                    this.send(subject, body, user.getEmail());
                }
        );
    }

    /**
     * Mail Sender.
     * @param subject subject.
     * @param body body.
     * @param email email.
     */
    private void send(String subject, String body, String email) {
    }

    /**
     * Terminate a pool.
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
