package ru.job4j.products;

import java.util.Date;
import java.util.HashSet;
/**
 * Shop
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 19.06.2019
 */
public class Shop extends Stock {
    /**
     * Sort strategy.
     * @param date date to sort.
     * @param firstStock warehouse stock.
     * @param secondStock trash stock.
     */
    @Override
    public void sort(Date date, Stock firstStock, Stock secondStock) {
        HashSet<Food> trash = new HashSet<>(100);
        HashSet<Food> warehouse = new HashSet<>(100);
        this.capacity.forEach(food -> {
            long wear = food.productWear(date);
            if (wear > 100D) {
                trash.add(food);
            } else if (wear >= 75D) {
                food.setDiscount(50D);
            } else if (wear <= 25D) {
                warehouse.add(food);
            }
        });
        firstStock.add(warehouse);
        secondStock.add(trash);
        this.capacity.removeAll(warehouse);
        this.capacity.removeAll(trash);
    }
}
