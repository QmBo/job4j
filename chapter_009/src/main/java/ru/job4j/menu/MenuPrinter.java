package ru.job4j.menu;

import java.util.ArrayList;
import java.util.List;
/**
 * MenuPrinter
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 22.07.2019
 */
public class MenuPrinter {
    /**
     * line Separator.
     */
    private static final String LS = System.lineSeparator();
    /**
     * Menu capacity.
     */
    private List<Menu> menu = new ArrayList<>(100);

    /**
     * Menu init.
     */
    public void initMenu() {
        Menu chang = new Menu(1, "Chang");
        Menu add = new Menu(1, "Add", chang);
        AddFirst addFirst = new AddFirst(1, "AddFirst", add);
        AddLast addLast = new AddLast(2, "AddLast", add);
        Menu remove = new Menu(2, "Remove", chang);
        RemoveFirst removeFirst = new RemoveFirst(1, "RemoveFirst", remove);
        RemoveLast removeLast = new RemoveLast(2, "RemoveLast", remove);
        this.menu.add(chang);
        this.menu.add(add);
        this.menu.add(addFirst);
        this.menu.add(addLast);
        this.menu.add(remove);
        this.menu.add(removeFirst);
        this.menu.add(removeLast);
    }

    /**
     * Menu shower.
     * @return menu.
     */
    public String showMenu() {
        this.menu.forEach(System.out::println);
        StringBuilder result = new StringBuilder();
        this.menu.forEach(menu1 -> {
            result.append(menu1);
            result.append(LS);
        });
        return result.toString();
    }

    /**
     * Action.
     * @param key menu key.
     * @throws IllegalStateException if not supported.
     */
    public String action(int key) throws IllegalStateException {
        return this.menu.get(key).printWork();
    }

    /**
     * Add First class.
     */
    private class AddFirst extends Menu {
        /**
         * Constructor.
         * @param pos position.
         * @param name name.
         * @param parent parent.
         */
        public AddFirst(int pos, String name, Menu parent) {
            super(pos, name, parent);
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
    private class AddLast extends Menu {
        /**
         * Constructor.
         * @param pos position.
         * @param name name.
         * @param parent parent.
         */
        public AddLast(int pos, String name, Menu parent) {
            super(pos, name, parent);
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
    private class RemoveFirst extends Menu {
        /**
         * Constructor.
         * @param pos position.
         * @param name name.
         * @param parent parent.
         */
        public RemoveFirst(int pos, String name, Menu parent) {
            super(pos, name, parent);
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
    private class RemoveLast extends Menu {
        /**
         * Constructor.
         * @param pos position.
         * @param name name.
         * @param parent parent.
         */
        public RemoveLast(int pos, String name, Menu parent) {
            super(pos, name, parent);
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
