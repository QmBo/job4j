package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Cache
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 27.12.2019
 */
public abstract class Cache<T> {
    /**
     * Capacity.
     */
    protected final Map<String, SoftReference<Item<T>>> capacity = new HashMap<>(100);

    /**
     * Add new Item.
     * @param name name.
     * @param item item.
     */
    public void newItem(final String name, final Item<T> item) {
        this.capacity.put(name, new SoftReference<>(item));
    }

    /**
     * Get object from item.
     * @param name name.
     * @return object from item.
     */
    public abstract T getCache(final String name);
}
