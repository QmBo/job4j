package ru.job4j.products;

import java.util.Date;
import java.util.HashSet;
/**
 * Warehouse
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 19.06.2019
 */
public class Warehouse extends Stock {
    /**
     * Sort strategy.
     * @param date date to sort.
     * @param firstStock shop stock.
     * @param secondStock trash stock.
     */
    @Override
    public void sort(Date date, Stock firstStock, Stock secondStock) {
        HashSet<Food> trash = new HashSet<>(100);
        HashSet<Food> shop = new HashSet<>(100);
        this.capacity.forEach(food -> {
            long wear = food.productWear(date);
            if (wear > 100D) {
                trash.add(food);
            } else if (wear >= 75D) {
                shop.add(food);
            } else if (wear > 25D) {
                shop.add(food);
            }
        });
        firstStock.add(shop);
        secondStock.add(trash);
        this.capacity.removeAll(shop);
        this.capacity.removeAll(trash);
    }
}
