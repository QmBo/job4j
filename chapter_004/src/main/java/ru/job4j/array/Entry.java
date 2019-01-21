package ru.job4j.array;
/**
 * Entry
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 18.01.2019
 */
public class Entry {
    /**
     * Capacity.
     */
    private final int value;

    /**
     * Constructor.
     * @param value value.
     */
    public Entry(int value) {
        this.value = value;
    }

    /**
     * Value getter.
     * @return value.
     */
    public int getValue() {
        return this.value;
    }
}
