package ru.job4j.lists;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static java.lang.String.format;
/**
 * SimpleLinkedList
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.07.2018
 */
public class SimpleLinkedList<E> implements Iterable<E> {
    /**
     * First element.
     */
    private Node<E> first;
    /**
     * Last element.
     */
    private Node<E> last;
    /**
     * Modification.
     */
    private int modCount;
    /**
     * Number of elements
     */
    private int size;

    /**
     * Add element to List.
     * @param model new element.
     */
    public void add(E model) {
        Node<E> newNode = new Node<>(model);
        if (this.first == null) {
            this.first = newNode;
            this.last = newNode;
        } else {
            this.last.next = newNode;
            this.last = newNode;
        }
        this.size++;
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
                    format("%s not in rang 0 - %s", index, this.size - 1));
        }
        if (this.size == 0) {
            throw new NoSuchElementException("NoSuchElementException");
        }
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Iterator getter.
     * @return iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new SimpleLinkedListIt(modCount);
    }

    /**
     * Node class.
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        /**
         * Constructor.
         * @param data element.
         */
        private Node(E data) {
            this.data = data;
        }
    }

    /**
     * Iterator class.
     */
    private class SimpleLinkedListIt implements Iterator<E> {
        /**
         * Modification.
         */
        private final int expectedModCount;
        /**
         * Iterator focus.
         */
        private int position = 0;
        /**
         * Last return element.
         */
        private Node<E> focus;

        /**
         * Constructor.
         * @param modCount modification.
         */
        private SimpleLinkedListIt(final int modCount) {
            this.expectedModCount = modCount;
        }

        /**
         * Check nas next element.
         * @return has next.
         */
        @Override
        public boolean hasNext() {
            if (this.expectedModCount != SimpleLinkedList.this.modCount) {
                throw new ConcurrentModificationException("ConcurrentModificationException");
            }
            boolean result = false;
            if (this.position != SimpleLinkedList.this.size) {
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
            if (this.position == 0) {
                this.focus = SimpleLinkedList.this.first;
            } else {
                this.focus = this.focus.next;
            }
            this.position++;
            return this.focus.data;
        }
    }
}
