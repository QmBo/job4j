package ru.job4j.products;

import java.util.Date;
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
    private final Date date;
    /**
     * Warehouse store.
     */
    private final Warehouse warehouse;
    /**
     * Shop store.
     */
    private final Shop shop;
    /**
     * Trash store.
     */
    private final Trash trash;

    /**
     * Constructor.
     * @param date calculate date.
     * @param warehouse warehouse store.
     * @param shop shop store.
     * @param trash trash store.
     */
    public ControlQuality(final Date date, Warehouse warehouse, Shop shop, Trash trash) {
        this.date = date;
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
    }

    /**
     * Quality check.
     */
    public void checkQuality() {
        this.warehouse.sort(this.date, this.shop, this.trash);
        this.trash.sort(this.date, this.warehouse, this.shop);
        this.shop.sort(this.date, this.warehouse, this.trash);
    }
}
