package ru.job4j.products;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * ControlQuality
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 19.06.2019
 */
public class ControlQuality {
    /**
     * Calculate date.
     */
    protected final Date date;
    /**
     * Stock store.
     */
    protected final Stock stock;

    /**
     * Constructor.
     * @param date calculate date.
     */
    public ControlQuality(final Date date, Stock stock) {
        this.date = date;
        this.stock = stock;
    }

    /**
     * Quality check.
     */
    public List<Food> checkQuality() {
        return this.checkQuality(new LinkedList<>());
    }

    /**
     * Quality check.
     */
    public List<Food> checkQuality(List<Food> inputFood) {
        List<Food> notAccept = new LinkedList<>();
        inputFood.addAll(this.stock.removeAll());
        inputFood.forEach(food -> {
            if (this.stock.accept(food, this.date)) {
                this.stock.add(food);
            } else {
                notAccept.add(food);
            }
        });
        if (!notAccept.isEmpty()) {
            this.stock.removeAll(notAccept);
        }
        return notAccept;
    }
}
