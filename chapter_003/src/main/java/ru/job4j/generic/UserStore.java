package ru.job4j.generic;

/**
 * UserStore
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 25.07.2018
 */
public class UserStore<T extends User> extends AbstractStore<T> {

    /**
     * Constructor.
     * @param length length.
     */
    protected UserStore(int length) {
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
