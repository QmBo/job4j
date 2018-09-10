package ru.job4j.thread;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Cash
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 03.09.2018
 */
public class Cash {
    /**
     * Capacity.
     */
    private final ConcurrentHashMap<Integer, Base> cash;

    /**
     * Constructor.
     */
    public Cash() {
        this.cash = new ConcurrentHashMap<>();
    }

    /**
     * Add element in capacity if element absent.
     * @param model new element.
     * @return is add.
     */
    public boolean add(Base model) {
        return this.cash.putIfAbsent(model.id, model) == null;
    }

    /**
     * Update element in capacity.
     * @param model element to update.
     */
    public void update(Base model) {
        this.cash.computeIfPresent(
                model.id, (integer, base) -> {
                    if (model.version != cash.get(model.id).version) {
                        throw new OptimisticException("Optimistic Exception");
                    }
                    return new Base(integer, ++base.version);
                }
        );
        System.out.println(this.cash.get(model.id));
    }

    /**
     * Delete element from capacity.
     * @param model element.
     */
    public void delete(Base model) {
        synchronized (this.cash) {
            if (this.cash.get(model.id).version == model.version) {
                throw new OptimisticException("Optimistic Exception");
            }
            this.cash.remove(model.id, model);
        }
    }

}
