package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * SimpleHashMap
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 05.08.2018
 */
public class SimpleHashMap<K, V> implements Iterable<V> {
    /**
     * Capacity.
     */
    private Node<K, V>[] tab;
    /**
     * Modification.
     */
    private int modCount;
    /**
     * Number of elements
     */
    private int size;
    /**
     * Load factor.
     */
    private final static double LOAD_FACTOR = 0.75;

    /**
     * Constructor.
     * @param length start length of capacity.
     */
    public SimpleHashMap(int length) {
        this.tab = new Node[length];
    }

    /**
     * Insert node in capacity.
     * @param key key.
     * @param value value.
     * @return is inserted.
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        if (this.tab[hash(key)] == null) {
            this.tab[hash(key)] = new Node<>(key, value);
            this.size++;
            this.modCount++;
            result = true;
            if (this.size >= this.tab.length * LOAD_FACTOR) {
                this.tabRise();
            }
        }
        return result;
    }

    /**
     * Rise of capacity.
     */
    private void tabRise() {
        Node<K, V>[] temp = this.tab;
        this.tab = new Node[this.tab.length * 2];
        for (Node node : temp) {
            if (node != null) {
                this.insert((K) node.key, (V) node.value);
                this.size--;
            }
        }
    }

    /**
     * Get value of key.
     * @param key key.
     * @return value.
     */
    public V get(K key) {
        V result = null;
        if (this.tab[hash(key)] != null) {
            result = this.tab[hash(key)].value;
        }
        return result;
    }

    /**
     * Delete node.
     * @param key kay.
     * @return is deleted.
     */
    public boolean delete(K key) {
        boolean result = false;
        if (this.tab[hash(key)] != null) {
            this.tab[hash(key)] = null;
            this.size--;
            this.modCount++;
            result = true;
        }
        return result;
    }

    /**
     * Return position i capacity.
     * @param key key.
     * @return position.
     */
    private int hash(K key) {
        return key == null ? 0 : (key.hashCode() * 31) % this.tab.length;
    }

    /**
     * Return all values as array.
     * @return array of al values.
     */
    private V[] arrayValues() {
        V[] result = (V[]) new Object[this.size];
        int index = 0;
        for (Node node : this.tab) {
            if (node != null) {
                result[index++] = (V) node.value;
            }
        }
        return result;
    }

    /**
     * Iterator getter.
     * @return iterator.
     */
    @Override
    public Iterator<V> iterator() {
        return new It(this.modCount, this.arrayValues());
    }

    /**
     * Node class.
     */
    private class Node<K, V> {
        private final K key;
        private V value;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Iterator class.
     */
    private class It implements Iterator<V> {
        /**
         * Values capacity.
         */
        private final V[] values;
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
         * @param values values.
         */
        private It(final int modCount, final V[] values) {
            this.expectedModCount = modCount;
            this.values = values;
        }

        /**
         * Check nas next element.
         * @return has next.
         */
        @Override
        public boolean hasNext() {
            if (this.expectedModCount != SimpleHashMap.this.modCount) {
                throw new ConcurrentModificationException("ConcurrentModificationException");
            }
            boolean result = false;
            if (this.position != SimpleHashMap.this.size) {
                result = true;
            }
            return result;
        }

        /**
         * Return next element.
         * @return next element.
         */
        @Override
        public V next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("Next element is absent.");
            }
            return this.values[this.position++];
        }
    }
}