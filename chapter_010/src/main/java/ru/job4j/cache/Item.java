package ru.job4j.cache;
/**
 * Item
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 27.12.2019
 */
public class Item<T> {
    /**
     * Capacity.
     */
    private final T object;

    /**
     * Constructor.
     * @param object object.
     */
    public Item(final T object) {
        this.object = object;
    }

    /**
     * Object getter.
     * @return object.
     */
    public T getObject() {
        return this.object;
    }
}
