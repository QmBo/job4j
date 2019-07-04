package ru.job4j.products;

import java.util.Date;
/**
 * Shop
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 19.06.2019
 */
public class Shop extends Stock {
    /**
     * Food validator.
     * @param food food.
     * @param date date of validation.
     * @return is accept.
     */
    @Override
    public boolean accept(Food food, Date date) {
        boolean result = false;
        long wear = food.productWear(date);
        if (wear > 25D && wear < 100D) {
            result = true;
            if (wear >= 75D) {
                food.setDiscount(50D);
            }
        }
        return result;
    }
}
