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
     * Sort strategy.
     * @param date date to sort.
     * @param firstStock other stock.
     * @param secondStock other stock.
     */
    void sort(Date date, Stock firstStock, Stock secondStock);
}
