package ru.job4j.tracker;
/**
 * Input interface.
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 03.06.2018
 */
public interface Input {
    String ask(String question);

    int ask(String question, int[] range);
}
