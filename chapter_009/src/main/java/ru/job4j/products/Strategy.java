package ru.job4j.products;

import java.util.Date;
/**
 * Strategy
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 19.06.2019
 */
public interface Strategy {
    /**
     * Food validator.
     * @param food food.
     * @param date date of validation.
     * @return is accept.
     */
    boolean accept(Food food, Date date);
}
