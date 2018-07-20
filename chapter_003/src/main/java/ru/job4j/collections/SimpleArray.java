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
        if (length >= 0) {
            this.array = new Object[length];
        } else {
            throw new IllegalArgumentException(
                    format("IllegalArgumentException %s", length)
            );
        }
    }

    /**
     * Add element in array.
     * @param model new element.
     */
    public void add(T model) {
        if (this.size != this.array.length) {
            this.array[size++] = model;
        } else {
            throw new IllegalStateException("Array fool.");
        }
    }

    /**
     * Rewrite element of index.
     * @param index index.
     * @param model new element.
     */
    public void set(int index, T model) {
        if (index > -1 && index < this.size) {
            this.array[index] = model;
        } else {
            throw new IndexOutOfBoundsException(
                    format("%s not in rang 0 - %s", index, this.size - 1)
            );
        }
    }

    /**
     * Delete element of index.
     * @param index index.
     */
    public void delete(int index) {
        if (index > -1 && index < this.size) {
            this.array[index] = null;
            this.size--;
            System.arraycopy(
                    this.array, index + 1, this.array,
                    index, size - index
            );
        } else {
            throw new IndexOutOfBoundsException(
                    format("%s not in rang 0 - %s", index, this.size - 1)
            );
        }
    }

    /**
     * Get element of index.
     * @param index index.
     * @return element.
     */
    public T get(int index) {
        T result;
        if (index > -1 && index < this.size) {
            result = (T) this.array[index];
        } else {
            throw new IndexOutOfBoundsException(
                    format("%s not in rang 0 - %s", index, this.size - 1)
            );
        }
        return result;
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
            T result;
            if (this.hasNext()) {
                result = (T) SimpleArray.this.array[this.findElement()];
                this.position++;
            } else {
                throw new NoSuchElementException("NoSuchElementException");
            }
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
