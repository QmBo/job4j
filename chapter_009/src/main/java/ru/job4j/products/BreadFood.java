package ru.job4j.products;

import java.util.Date;
/**
 * BreadFood
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 19.06.2019
 */
public class BreadFood extends Food {
    /**
     * Constructor.
     * @param name product name.
     * @param expireDate expire date.
     * @param createDate create date.
     * @param price product price.
     * @param canRecycle is cdn be recycled.
     */
    public BreadFood(String name, Date expireDate, Date createDate, double price, boolean canRecycle) {
        super(name, expireDate, createDate, price, canRecycle);
    }
}
