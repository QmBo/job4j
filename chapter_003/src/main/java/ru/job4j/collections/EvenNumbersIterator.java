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
        for (int index = this.position; index != this.array.length; index++) {
            if (this.array[index] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Return next even element.
     * @return next even element.
     */
    @Override
    public Integer next() {
        Integer result = -1;
        for (int index = this.position; index != this.array.length; index++) {
            if (this.array[index] % 2 == 0) {
                result = this.array[index];
                this.position = ++index;
                break;
            }
        }
        if (result == -1) {
            throw new NoSuchElementException("no element");
        }
        return result;
    }
}
