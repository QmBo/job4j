package ru.job4j.lists;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static java.lang.String.format;
/**
 * DynamicArrayList
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.07.2018
 */
public class DynamicArrayList<E> implements Iterable<E> {
    /**
     * Container.
     */
    private Object[] container;
    /**
     * Number of elements.
     */
    private int size;
    /**
     * Modification.
     */
    private int modCount;

    /**
     * Constructor.
     * @param length length of container.
     */
    protected DynamicArrayList(int length) {
        this.container = new Object[length];
    }

    /**
     * Add element in end of container.
     * @param model element.
     */
    public void add(E model) {
        if (this.size == this.container.length) {
            this.container = Arrays.copyOf(this.container, this.container.length * 2);
        }
        this.container[size++] = model;
        this.modCount++;
    }

    /**
     * Element getter.
     * @param index index of element.
     * @return element.
     */
    public E get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(
                    format("%s not in rang 0 - %s", index, this.size - 1)
            );
        }
        return (E) this.container[index];
    }

    /**
     * Iterator getter.
     * @return iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new DynamicArrayListIterator(modCount);
    }

    /**
     * Iterator class.
     */
    private class DynamicArrayListIterator implements Iterator<E> {
        /**
         * Modification.
         */
        private final int expectedModCount;
        /**
         * Iterator focus.
         */
        private int position = 0;

        /**
         * Constructor.
         * @param modCount modification.
         */
        private DynamicArrayListIterator(final int modCount) {
            this.expectedModCount = modCount;
        }

        /**
         * Check nas next element.
         * @return has next.
         */
        @Override
        public boolean hasNext() {
            if (this.expectedModCount != DynamicArrayList.this.modCount) {
                throw new ConcurrentModificationException("ConcurrentModificationException");
            }
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
        public E next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("NoSuchElementException");
            }
            E result = (E) DynamicArrayList.this.container[this.findElement()];
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
            for (int index = this.position; index < DynamicArrayList.this.size; index++) {
                result = index;
                break;
            }
            return result;
        }
    }
}
