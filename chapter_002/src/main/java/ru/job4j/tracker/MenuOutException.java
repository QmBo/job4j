package ru.job4j.tracker;
/**
 * MenuOutException
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 07.06.2018
 */
public class MenuOutException extends RuntimeException {
    /**
     * Constructor.
     * @param msg massage.
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}
