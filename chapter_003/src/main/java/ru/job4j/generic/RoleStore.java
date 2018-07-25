package ru.job4j.generic;

/**
 * RoleStore
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 25.07.2018
 */
public class RoleStore<T extends Role> extends AbstractStore<T> {

    /**
     * Constructor.
     * @param length length.
     */
    protected RoleStore(int length) {
        super(length);
    }

    /**
     * Add element to capacity.
     * @param model element.
     */
    @Override
    public void add(T model) {
        super.add(model);
    }
}
