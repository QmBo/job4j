package ru.job4j.max;

/**
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version $Id$
 * @since 0.1
 */
public class Max {

    /**
     * Equals two integers and return maximum.
     * @param first first integer.
     * @param second second integer.
     * @return maximum of two integers.
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }
}
