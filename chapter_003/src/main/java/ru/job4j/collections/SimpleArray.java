package ru.job4j.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static java.lang.String.format;

/**
 * SimpleArray
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 19.07.2018
 */
public class SimpleArray<T> implements Iterable<T> {
    /**
     * Elements.
     */
    private Object[] array;
    /**
     * Amount elements.
     */
    private int size = 0;

    /**
     * Constructor.
     * @param length siz of array.
     */
    public SimpleArray(int length) {
        if (length < 0) {
            throw new IllegalArgumentException(
                    format("IllegalArgumentException %s", length)
            );
        }
        this.array = new Object[length];
    }

    /**
     * Add element in array.
     * @param model new element.
     */
    public void add(T model) {
        if (this.size == this.array.length) {
            throw new IllegalStateException("Array fool.");
        }
        this.array[size++] = model;
    }

    /**
     * Rewrite element of index.
     * @param index index.
     * @param model new element.
     */
    public void set(int index, T model) {
        this.indexCheck(index);
        this.array[index] = model;
    }

    /**
     * Find index of element.
     * @param model element.
     * @return index.
     */
    public int indexOf(T model) {
        int result = -1;
        for (int index = 0; index < this.size; index++) {
            if (this.array[index].equals(model)) {
                result = index;
                break;
            }
        }
        return result;
    }

    /**
     * Delete element of index.
     * @param index index.
     */
    public void delete(int index) {
        this.indexCheck(index);
        this.array[index] = null;
        this.size--;
        System.arraycopy(
                this.array, index + 1, this.array,
                index, size - index
        );
    }

    /**
     * Get element of index.
     * @param index index.
     * @return element.
     */
    public T get(int index) {
        this.indexCheck(index);
        return (T) this.array[index];
    }

    /**
     * Check index to correct.
     * @param index index.
     */
    private void indexCheck(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(
                    format("%s not in rang 0 - %s", index, this.size - 1)
            );
        }
    }

    /**
     * Return iterator of elements.
     * @return iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new It();
    }

    /**
     * Iterator Class.
     */
    private class It implements Iterator<T> {
        /**
         * Iterator focus.
         */
        private int position = 0;

        /**
         * Check nas next element.
         * @return has next.
         */
        @Override
        public boolean hasNext() {
            boolean result = false;
            if (this.findElement() != -1) {
                result = true;
            }
            return result;
        }

        /**
         * Return next element.
         * @return next element.
         */
        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("NoSuchElementException");
            }
            T result = (T) SimpleArray.this.array[this.findElement()];
            this.position++;
            return result;
        }

        /**
         * Find next element.
         * If nas not next element return -1.
         * @return index of element.
         */
        private int findElement() {
            int result = -1;
            for (int index = this.position; index < SimpleArray.this.array.length; index++) {
                if (SimpleArray.this.array[index] != null) {
                    result = index;
                    break;
                }
            }
            return result;
        }
    }
}
