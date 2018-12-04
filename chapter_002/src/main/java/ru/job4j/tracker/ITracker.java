package ru.job4j.tracker;

import java.util.List;

public interface ITracker {
    /**
     * Add item in array.
     * @param item item to add.
     * @return item with id.
     */
    Item add(Item item);
    /**
     * Replace item by id.
     * @param id id.
     * @param item item.
     */
    void replace(String id, Item item);
    /**
     * Deleted item from array by id.
     * @param id id.
     */
    void delete(String id);
    /**
     * Return copy of all items.
     * @return all items.
     */
    List<Item> getAll();
    /**
     * Find item by name.
     * @param key name.
     * @return all item with name.
     */
    List<Item> findByName(String key);
    /**
     * Find item by id.
     * @param id id.
     * @return all item with id.
     */
    Item findById(String id);
    /**
     * Check is exit time.
     * @return isExit.
     */
    boolean isExit();
    /**
     * Set exit flag true.
     */
    void timeToExit();
}