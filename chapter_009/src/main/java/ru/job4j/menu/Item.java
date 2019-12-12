package ru.job4j.menu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.String.format;

public class Item implements Out, Work {
    /**
     * Name.
     */
    private final String name;
    /**
     * Parent Item.
     */
    private Item parent;
    /**
     * Item number item.
     */
    private String index;
    /**
     * Child Items.
     */
    private final List<Item> items = new ArrayList<>(100);

    /**
     * Constructor.
     * @param name Item name.
     */
    public Item(final String name) {
        this.name = name;
    }

    /**
     * Parent setter.
     * @param parent parent Item.
     */
    public void setParent(Item parent) {
        this.parent = parent;
    }

    /**
     * Child Item add.
     * @param item chile Item.
     */
    public void addItem(final Item item) {
        this.items.add(item);
    }

    /**
     * Print names with number of item and all childes.
     */
    public void showName() {
        StringBuilder name = new StringBuilder();
        if (this.parent != null) {
            name.append(this.parent.index);
            name.append(".");
            name.append(this.parent.items.indexOf(this) + 1);
        } else {
            name.append("1");
        }
        this.index = name.toString();
        System.out.println(format("%s %s", name.toString(), this.name));
        this.printItems();
    }

    /**
     * Printer helper.
     * If have childes.
     */
    public void printItems() {
        for (Item item : this.items) {
            item.setParent(this);
            item.showName();
        }
    }

    /**
     * Return This item and all childes.
     * @return list of Items.
     */
    public List<Item> getItems() {
        List<Item> result = new LinkedList<>();
        result.add(this);
        for (Item item : this.items) {
            result.addAll(item.getItems());
        }
        return result;
    }


    @Override
    public String printName() {
        return null;
    }

    @Override
    public String printWork() {
        return null;
    }

    /**
     * Add First class.
     */
    public static class AddFirst extends Item {
        /**
         * Constructor.
         * @param name name.
         */
        public AddFirst(final String name) {
            super(name);
        }
        /**
         * Main work.
         * @return work.
         */
        @Override
        public String printWork() {
            System.out.println("AddFirst");
            return "AddFirst";
        }
    }

    /**
     * Add Last class.
     */
    public static class AddLast extends Item {
        /**
         * Constructor.
         * @param name name.
         */
        public AddLast(final String name) {
            super(name);
        }
        /**
         * Main work.
         * @return work.
         */
        @Override
        public String printWork() {
            System.out.println("AddLast");
            return "AddLast";
        }
    }

    /**
     * Remove First class.
     */
    public static class RemoveFirst extends Item {
        /**
         * Constructor.
         * @param name name.
         */
        public RemoveFirst(final String name) {
            super(name);
        }
        /**
         * Main work.
         * @return work.
         */
        @Override
        public String printWork() {
            System.out.println("RemoveFirst");
            return "RemoveFirst";
        }
    }

    /**
     * Add Last class.
     */
    public static class RemoveLast extends Item {
        /**
         * Constructor.
         * @param name name.
         */
        public RemoveLast(final String name) {
            super(name);
        }
        /**
         * Main work.
         * @return work.
         */
        @Override
        public String printWork() {
            System.out.println("RemoveFirst");
            return "RemoveFirst";
        }
    }
}
