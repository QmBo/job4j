package ru.job4j.products;

import java.util.Date;
/**
 * Trash
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 19.06.2019
 */
public class Trash extends Stock {
    /**
     * Food validator.
     * @param food food.
     * @param date date of validation.
     * @return is accept.
     */
    @Override
    public boolean accept(Food food, Date date) {
        return food.productWear(date) >= 100D;
    }

}
