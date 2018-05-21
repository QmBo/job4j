package ru.job4j.loop;

/**
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 0.1
 */
public class Counter {

    /**
     * Return the sum of even numbers in the range.
     * @param start start of range.
     * @param finish finish of range.
     * @return sum.
     */
    public int add(int start, int finish) {
        int result = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                result += i;
            }
        }
        return result;
    }
}
