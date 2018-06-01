package ru.job4j.tracker;

import java.util.Random;

/**
 * Tracker class
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 01.06.2018
 */
public class Tracker {
    /**
     * All Items.
     */
    private final Item[] items = new Item[100];
    /**
     * Random generator for id.
     */
    private static final Random RM = new Random(100);
    /**
     * Number of Items.
     */
    private int position = 0;

    /**
     * Add item in array.
     * @param item item to add.
     * @return item with id.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Replace item by id.
     * @param id id.
     * @param item item.
     */
    public void replace(String id, Item item) {
        for (int index = 0; index < this.items.length; index++) {
            if (this.items[index] == null) {
                break;
            }
            if (this.items[index].getId().equals(id)) {
                this.items[index] = item;
                break;
            }
        }
    }

    /**
     * Deleted item from array by id.
     * @param id id.
     */
    public void delete(String id) {
        for (int index = 0; index < this.items.length; index++) {
            if (this.items[index] == null) {
                System.out.println("Item not found.");
                break;
            }
            if (this.items[index].getId().equals(id)) {
                System.arraycopy(this.items, index + 1, this.items, index, this.items.length - index - 1);
                this.position--;
                break;
            }
        }
    }

    /**
     * Find item by name.
     * @param key name.
     * @return all item with name.
     */
    public Item[] findByName(String key) {
        Item[] temp = new Item[this.position];
        int index = 0;
        for (Item item : this.items) {
            if (item == null) {
                if (index == 0) {
                    System.out.println("Item not found.");
                }
                break;
            }
            if (item.getName().equals(key)) {
                temp[index++] = item;
            }
        }
        Item[] result = new Item[index];
        System.arraycopy(temp, 0, result, 0, index);
        return result;
    }

    /**
     * Find item by id.
     * @param id id.
     * @return all item with id.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item: this.items) {
            if (item == null) {
                System.out.println("Item not found.");
                break;
            }
            if (item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Return copy of all items.
     * @return all items.
     */
    public Item[] getAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index < this.position; index++) {
            if (this.items[index] == null) {
                break;
            }
            result[index] = this.items[index];
        }
        return result;
    }

    /**
     * Id generator.
     * @return unique id.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RM.nextInt());
    }
}
