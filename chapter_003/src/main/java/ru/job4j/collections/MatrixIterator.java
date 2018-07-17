package ru.job4j.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * MatrixIterator
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 17.07.2018
 */
public class MatrixIterator implements Iterator<Integer> {
    /**
     * Array.
     */
    private final int[][] array;
    /**
     * Position in array.
     */
    private int line = 0;
    /**
     * Position in array.
     */
    private int position = 0;

    /**
     * Constructor.
     * @param array array to iterate.
     */
    public MatrixIterator(final int[][] array) {
        this.array = array;
    }

    /**
     * Check nas next element.
     * @return has next.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        if (line != array.length && position != array[line].length) {
            result = true;
        }
        return result;
    }

    /**
     * Return next element.
     * @return next element.
     */
    @Override
    public Integer next() {
        Integer result;
        try {
            result = array[line][position++];
        } catch (IndexOutOfBoundsException ioobe) {
            throw new NoSuchElementException(ioobe.getMessage());
        }
        if (this.position == array[line].length) {
            this.position = 0;
            this.line++;
        }
        return result;
    }
}
