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
     * Time to exit.
     */
    private boolean exit = false;

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
        for (int index = 0; index < this.position; index++) {
            if (this.items[index].getId().equals(id)) {
                item.setId(this.items[index].getId());
                this.items[index] = item;
                break;
            }
            if (index == this.position - 1) {
                System.out.println("Item not found.");
            }
        }
    }

    /**
     * Deleted item from array by id.
     * @param id id.
     */
    public void delete(String id) {
        for (int index = 0; index < this.position; index++) {
            if (this.items[index].getId().equals(id)) {
                System.arraycopy(this.items, index + 1, this.items, index, this.items.length - index - 1);
                this.position--;
                System.out.println("Item deleted.");
                break;
            }
            if (index == this.position - 1) {
                System.out.println("Item not found.");
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
        int find = 0;
        for (int index = 0; index < this.position; index++) {
            if (this.items[index].getName().equals(key)) {
                temp[find++] = this.items[index];
            }
        }
        Item[] result = new Item[find];
        if (find == 0) {
            System.out.println("Item not found.");
        } else {
            System.arraycopy(temp, 0, result, 0, find);
        }
        return result;
    }

    /**
     * Find item by id.
     * @param id id.
     * @return all item with id.
     */
    public Item findById(String id) {
        Item result = null;
        for (int index = 0; index < this.position; index++) {
            if (this.items[index].getId().equals(id)) {
                result = this.items[index];
                break;
            }
            if (index == this.position - 1) {
                System.out.println("Item not found.");
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

    public boolean isExit() {
        return this.exit;
    }

    public void timeToExit() {
        this.exit = true;
    }
}
