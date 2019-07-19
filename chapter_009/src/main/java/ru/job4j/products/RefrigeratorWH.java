package ru.job4j.products;

import java.util.Date;
/**
 * RefrigeratorWH
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 19.07.2019
 */
public class RefrigeratorWH extends Stock {
    /**
     * Food validator.
     * @param food food.
     * @param date date of validation.
     * @return is accept.
     */
    @Override
    public boolean accept(Food food, Date date) {
        boolean result = false;
        if (food.productWear(date) <= 25D && food.keepRefrigerated) {
            result = this.havePlace();
        }
        return result;
    }
}
