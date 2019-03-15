package ru.job4j.chat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Answers
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 15.03.2019
 */
public class Answers {
    private static final Logger LOG = LogManager.getLogger(Answers.class);
    /**
     * Path to file this answers.
     */
    private String name;
    /**
     * List of answers.
     */
    private List<String> answers = new ArrayList<>(100);

    /**
     * Constructor.
     */
    public Answers() {
        this("chat/Answers.txt");
    }

    /**
     * Constructor.
     * @param name path to file this answers.
     */
    public Answers(String name) {
        this.name = name;
    }

    /**
     * Load answers.
     * @return lit of answers.
     */
    public List<String> init() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(name)) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                String line = br.readLine();
                while (line != null) {
                    this.answers.add(line);
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return this.answers;
    }

    /**
     * Return random answer.
     * @return answer.
     */
    public String getAnswers() {
        int random = (int) (this.answers.size() * Math.random());
        return this.answers.get(random);
    }
}
