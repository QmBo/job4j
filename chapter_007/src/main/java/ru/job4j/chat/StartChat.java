package ru.job4j.chat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * StartChat
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 15.03.2019
 */
public class StartChat {
    private static final Logger LOG = LogManager.getLogger(StartChat.class);
    /**
     * Reader.
     */
    private final BufferedReader br;
    /**
     * Answers class.
     */
    private final Answers answers;
    /**
     * Logger.
     */
    private final ChatLogger logger;
    /**
     * Stop flag.
     */
    private boolean stop = false;
    /**
     * Pause flag.
     */
    private boolean pause = false;

    /**
     * Constructor.
     * @param br reader.
     * @param answers answers class.
     * @param logger logger.
     */
    public StartChat(BufferedReader br, Answers answers, ChatLogger logger) {
        this.br = br;
        this.answers = answers;
        this.logger = logger;
        this.answers.init();
    }

    /**
     * Main.
     * @param args not sensitive.
     */
    public static void main(String[] args) {
        new StartChat(
                new BufferedReader(
                        new InputStreamReader(System.in)
                ),
                new Answers(),
                new ChatLogger()
        ).chat();
    }

    /**
     * Loop.
     */
    private void chat() {
        this.logger.init("ChatLog.txt");
        while (!this.stop) {
            this.start();
        }
    }

    /**
     * Logic.
     * @return line from reader.
     */
    public String start() {
        String result = this.waiteLine();
        StringBuilder sb = new StringBuilder();
        sb.append(result).append(System.lineSeparator());
        if ("стоп".equals(result)) {
            this.pause = true;
        } else if ("продолжить".equals(result)) {
            this.pause = false;
        } else if ("закончить".equals(result)) {
            this.stop = true;
        }
        if (!this.pause && !this.stop) {
            String answer = answers.getAnswers();
            sb.append(answer).append(System.lineSeparator());
            System.out.println(answer);
        }
        this.logger.write(sb.toString());
        return result;
    }

    /**
     * Waiting line method.
     * @return line.
     */
    private String waiteLine() {
        String result = null;
        try {
            while (result == null) {
                result = br.readLine();
            }
        } catch (IOException e) {
            LOG.error("message", e);
        }
        return result;
    }
}
