package ru.job4j.products;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Stock
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 19.06.2019
 */
public abstract class Stock implements Strategy {
    /**
     * Capacity.
     */
    protected Set<Food> capacity = new HashSet<>(100);

    /**
     * Size limit.
     * -1 to unlimited.
     */
    private int sizeLimit;

    /**
     * Constructor.
     */
    public Stock() {
        this(-1);
    }

    /**
     * Constructor.
     * @param sizeLimit size limit, -1 to unlimited.
     */
    public Stock(final int sizeLimit) {
        this.sizeLimit = sizeLimit;
    }
    /**
     * Add list to capacity.
     * @param list set of food.
     */
    public void add(Set<Food> list) {
        this.capacity.addAll(list);
    }

    /**
     * Capacity getter.
     * @return set of food.
     */
    public Set<Food> getStock() {
        return this.capacity;
    }

    /**
     * Add food to capacity.
     * @param food food.
     */
    public void add(Food food) {
        this.capacity.add(food);
    }

    /**
     * Return and clear capacity.
     * @return stock.
     */
    public Set<Food> removeAll() {
        Set<Food> result = new HashSet<>(this.capacity);
        this.capacity.clear();
        return result;
    }
    /**
     * Remove list from capacity.
     * @param list list of food.
     */
    public void removeAll(List<Food> list) {
        this.capacity.removeAll(list);
    }

    /**
     * Check place to insert.
     * @return have place.
     */
    public boolean havePlace() {
        boolean result = true;
        if (this.sizeLimit > -1) {
            if (this.sizeLimit - this.capacity.size() < 1) {
                result = false;
            }
        }
        return result;
    }
}
