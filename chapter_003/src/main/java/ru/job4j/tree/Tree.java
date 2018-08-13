package ru.job4j.tree;

import java.util.*;
/**
 * Tree
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 13.08.2018
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Root of tree.
     */
    private Node<E> root;
    /**
     * Modification.
     */
    private int modCount;

    /**
     * Constructor.
     * @param root root.
     */
    public Tree(E root) {
        this.root = new Node<>(root);
    }

    /**
     * Add element to tree.
     * @param parent parent.
     * @param child child.
     * @return
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> node = this.findBy(parent);
        if (node.isPresent()) {
            boolean duplicate = false;
            for (Node<E> value : node.get().leaves()) {
                if (value.eqValue(child)) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                node.get().add(new Node<>(child));
                result = true;
                this.modCount++;
            }
        }
        return result;
    }

    /**
     * Find element in tree.
     * @param value element.
     * @return node.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Iterator getter.
     * @return iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new It(this.modCount, this.root);
    }

    /**
     * Iterator class.
     */
    private class It<E extends Comparable<E>> implements Iterator<E> {
        /**
         * Modification.
         */
        private final int expectedModCount;
        /**
         * Position.
         */
        private int position;
        /**
         * Capacity.
         */
        private List<E> list = new ArrayList<>(100);

        /**
         * Constructor.
         * @param modCount modification.
         */
        private It(final int modCount, Node<E> root) {
            this.expectedModCount = modCount;
            this.list.add(root.getValue());
            if (!root.leaves().isEmpty()) {
                setUp(root);
            }
        }

        /**
         * Create capacity.
         * @param start
         */
        private void setUp(Node<E> start) {
            if (!start.leaves().isEmpty()) {
                for (Node<E> node : start.leaves()) {
                    this.list.add(node.getValue());
                    this.setUp(node);
                }
            }
        }

        /**
         * Check nas next element.
         * @return has next.
         */
        @Override
        public boolean hasNext() {
            if (this.expectedModCount != Tree.this.modCount) {
                throw new ConcurrentModificationException("ConcurrentModificationException");
            }
            boolean result = false;
            if (this.position < this.list.size()) {
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
                throw new NoSuchElementException("Next element is absent.");
            }
            return this.list.get(position++);
        }
    }
}
