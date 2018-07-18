package ru.job4j.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * EvenNumbersIterator
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 17.07.2018
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    /**
     * Array.
     */
    private final int[] array;
    /**
     * Position in array.
     */
    private int position = 0;

    /**
     * Constructor.
     * @param array array to iterate.
     */
    public EvenNumbersIterator(int[] array) {
        this.array = array;
    }

    /**
     * Check nas next even element.
     * @return has next.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        if (this.findEven() != -1) {
            result = true;
        }
        return result;
    }

    /**
     * Return next even element.
     * @return next even element.
     */
    @Override
    public Integer next() {
        Integer result;
        if (this.hasNext()) {
            int index = this.findEven();
            result = this.array[index];
            this.position = ++index;
        } else {
            throw new NoSuchElementException("no element");
        }
        return result;
    }

    /**
     * Find index of next even element.
     * If has't next even element return -1.
     * @return index of next even element.
     */
    private int findEven() {
        int result = -1;
        for (int index = this.position; index != this.array.length; index++) {
            if (this.array[index] % 2 == 0) {
                result = index;
                break;
            }
        }
        return result;
    }
}
