package ru.job4j.menu;

import com.google.common.base.Joiner;

/**
 * Menu
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 22.07.2019
 */
public class Menu implements Out, Work {
    /**
     * Position.
     */
    private final int pos;
    /**
     * Name.
     */
    private final String name;
    /**
     * Parent.
     */
    private Menu parent;

    /**
     * Constructor.
     * @param pos position.
     * @param name name.
     */
    public Menu(final int pos, final String name) {
        this(pos, name, null);
    }

    /**
     * Constructor.
     * @param pos position.
     * @param name name.
     * @param parent parent.
     */
    public Menu(final int pos, final String name, final Menu parent) {
        this.pos = pos;
        this.name = name;
        this.parent = parent;
    }

    /**
     * Position getter.
     * @return true position.
     */
    public String getPosition() {
        StringBuilder result = new StringBuilder(this.pos);
        if (this.parent != null) {
            result.insert(0, ".");
            result.insert(0, this.parent.getPosition());
        }
        result.append(this.pos);
        return result.toString();
    }

    /**
     * Name printer.
     * @return position and name.
     */
    @Override
    public String printName() {
        return Joiner.on(" ").join(this.getPosition(), this.name);
    }

    /**
     * @throws IllegalStateException Not supported.
     */
    @Override
    public String printWork() throws IllegalStateException {
        throw new IllegalStateException("Not supported.");
    }

    @Override
    public String toString() {
        return this.printName();
    }
}
