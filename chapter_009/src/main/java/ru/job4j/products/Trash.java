package ru.job4j.products;

import java.util.Date;
import java.util.HashSet;
/**
 * Trash
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 19.06.2019
 */
public class Trash extends Stock {
    /**
     * Sort strategy.
     * @param date date to sort.
     * @param firstStock warehouse stock.
     * @param secondStock shop stock.
     */
    @Override
    public void sort(Date date, Stock firstStock, Stock secondStock) {
        HashSet<Food> shop = new HashSet<>(100);
        HashSet<Food> warehouse = new HashSet<>(100);
        this.capacity.forEach(food -> {
            long wear = food.productWear(date);
            if (wear <= 25D) {
                warehouse.add(food);
            } else if (wear < 75D) {
                shop.add(food);
            } else if (wear < 100D) {
                food.setDiscount(50D);
                shop.add(food);
            }
        });
        firstStock.add(warehouse);
        secondStock.add(shop);
        this.capacity.removeAll(warehouse);
        this.capacity.removeAll(shop);
    }
}
