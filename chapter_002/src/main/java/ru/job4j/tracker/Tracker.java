package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Random;

/**
 * Tracker class
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 01.06.2018
 */
public class Tracker implements ITracker {
    /**
     * All Items.
     */
    private ArrayList<Item> items = new ArrayList<>();
    /**
     * Random generator for id.
     */
    private static final Random RM = new Random(100);
    /**
     * Time to exit.
     */
    private boolean exit = false;

    /**
     * Add item in array.
     * @param item item to add.
     * @return item with id.
     */
    @Override
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Replace item by id.
     * @param id id.
     * @param item item.
     */
    @Override
    public void replace(String id, Item item) {
        boolean find = false;
        for (Item task : this.items) {
            if (task.getId().equals(id)) {
                item.setId(task.getId());
                this.items.set(this.items.indexOf(task), item);
                find = true;
                break;
            }
        }
        if (!find) {
            System.out.println("Item not found.");
        }
    }

    /**
     * Deleted item from array by id.
     * @param id id.
     */
    @Override
    public void delete(String id) {
        boolean find = false;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                this.items.remove(item);
                System.out.println("Item deleted.");
                find = true;
                break;
            }
        }
        if (!find) {
            System.out.println("Item not found.");
        }
    }

    /**
     * Find item by name.
     * @param key name.
     * @return all item with name.
     */
    @Override
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item item : this.items) {
            if (item.getName().equals(key)) {
                result.add(item);
            }
        }
        if (result.size() == 0) {
            System.out.println("Item not found.");
        }
        return result;
    }

    /**
     * Find item by id.
     * @param id id.
     * @return all item with id.
     */
    @Override
    public Item findById(String id) {
        Item result = null;
        boolean find = false;
        for (Item task : this.items) {
            if (task.getId().equals(id)) {
                result = task;
                find = true;
                break;
            }
        }
        if (!find) {
            System.out.println("Item not found.");
        }
        return result;
    }

    /**
     * Return copy of all items.
     * @return all items.
     */
    @Override
    public ArrayList<Item> getAll() {
        return this.items;
    }

    /**
     * Id generator.
     * @return unique id.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RM.nextInt());
    }

    /**
     * Check is exit time.
     * @return isExit.
     */
    @Override
    public boolean isExit() {
        return this.exit;
    }
    /**
     * Set exit flag true.
     */
    @Override
    public void timeToExit() {
        this.exit = true;
    }
}
