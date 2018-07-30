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
        Node<T> slow = first;
        Node<T> fast = first;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                result = true;
                break;
            }
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
