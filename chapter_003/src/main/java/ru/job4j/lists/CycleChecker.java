package ru.job4j.lists;
/**
 * CycleChecker
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 27.07.2018
 */
public class CycleChecker<T> {

    /**
     * Check cycle.
     * @param first start element.
     * @return cycle.
     */
    boolean hasCycle(Node<T> first) {
        boolean result = false;
        if (first.next != null) {
            result = checkCycle(first);
            if (!result) {
                this.hasCycle(first.next);
            }
        }
        return result;
    }

    /**
     * Checker helper.
     * @param start check element.
     * @return cycle.
     */
    private boolean checkCycle(Node<T> start) {
        boolean result = false;
        Node<T> next = start.next;
        while (next.next != null) {
            if (start == next) {
                result = true;
                break;
            }
            next = next.next;
        }
        return result;
    }

    /**
     * Node class.
     */
    protected static class Node<T> {
        /**
         * Data.
         */
        private final T value;
        /**
         * Next element.
         */
        protected Node<T> next;

        /**
         * Constructor.
         * @param value
         */
        protected Node(final T value) {
            this.value = value;
        }
    }
}
