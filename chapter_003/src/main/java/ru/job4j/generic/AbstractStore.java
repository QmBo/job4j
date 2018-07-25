package ru.job4j.generic;

import ru.job4j.collections.SimpleArray;
/**
 * AbstractStore
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 25.07.2018
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {
    /**
     * Element capacity.
     */
    private SimpleArray<T> simpleArray;

    /**
     * Constructor.
     * @param length length.
     */
    protected AbstractStore(int length) {
        this.simpleArray = new SimpleArray<>(length);
    }

    /**
     * Add element to capacity.
     * @param model element.
     */
    @Override
    public void add(T model) {
        this.simpleArray.add(model);
    }

    /**
     * Replace element in capacity.
     * @param id id.
     * @param model new element.
     * @return replaced.
     */
    @Override
    public boolean replace(String id, T model) {
        boolean ok = false;
        if (findById(id) != null) {
            int index = this.simpleArray.indexOf(findById(id));
            this.simpleArray.set(index, model);
            ok = true;
        }
        return ok;
    }

    /**
     * Delete element from capacity.
     * @param id id.
     * @return deleted.
     */
    @Override
    public boolean delete(String id) {
        boolean ok = false;
        if (findById(id) != null) {
            int index = this.simpleArray.indexOf(findById(id));
            this.simpleArray.delete(index);
            ok = true;
        }
        return ok;
    }

    /**
     * Fine element by id.
     * @param id id.
     * @return element.
     */
    @Override
    public T findById(String id) {
        T result = null;
        for (T base : this.simpleArray) {
            if (base.getId().equals(id)) {
                result = base;
            }
        }
        return result;
    }
}
