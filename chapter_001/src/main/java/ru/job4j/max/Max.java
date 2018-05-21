package ru.job4j.max;

/**
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
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

    /**
     * Equals three integers and return maximum.
     * @param first first integer.
     * @param second second integer.
     * @param third third integer.
     * @return maximum of three integers.
     */
    public int max(int first, int second, int third) {
        int temp = this.max(first, second);
        return this.max(temp, third);
    }
}
