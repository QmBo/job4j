package ru.job4j.products;

import java.util.HashSet;
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
}
