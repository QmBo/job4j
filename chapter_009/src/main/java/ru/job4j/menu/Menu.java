package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Menu
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 12.12.2019
 */
public class Menu {
    /**
     * Menu items capacity.
     */
    private final Item items;

    /**
     * Constructor.
     * @param item menu items.
     */
    public Menu(final Item item) {
        this.items = item;
    }

    /**
     * Print Menu.
     */
    public void printMenu() {
        items.showName();
    }

    /**
     * Menu capacity.
     */
    private List<Item> menu = new ArrayList<>(100);

    /**
     * Menu init.
     */
    public int initMenu() {
        this.menu = this.items.getItems();
        return this.menu.size();
    }

    /**
     * Action.
     * @param key menu key.
     * @throws IllegalStateException if not supported.
     */
    public String action(int key) throws IllegalStateException {
        return this.menu.get(key).printWork();
    }
}
