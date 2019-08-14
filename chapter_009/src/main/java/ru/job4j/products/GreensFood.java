package ru.job4j.products;

import java.util.Date;
/**
 * GreensFood
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 19.07.2019
 */
public class GreensFood extends Food {
    /**
     * Constructor.
     *
     * @param name         product name.
     * @param expireDate   expire date.
     * @param createDate   create date.
     * @param price        product price.
     * @param canRecycle   is cdn be recycled.
     */
    public GreensFood(String name, Date expireDate, Date createDate, double price, boolean canRecycle) {
        super(name, expireDate, createDate, price, canRecycle);
        this.keepRefrigerated = true;
    }
}
