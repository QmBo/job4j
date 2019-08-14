package ru.job4j.products;

import java.util.Date;
import java.util.Objects;

/**
 * Food
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 19.06.2019
 */
public class Food implements Comparable<Food> {
    /**
     * Product name.
     */
    private String name;
    /**
     * Expire date.
     */
    private Date expireDate;
    /**
     * Create date.
     */
    private Date createDate;
    /**
     * Product price.
     */
    private double price;
    /**
     * Product discount.
     */
    private double discount;
    /**
     * Can recycled.
     */
    private final boolean canRecycle;
    /**
     * Keep refrigerated.
     */
    protected boolean keepRefrigerated = false;
    /**
     * Constructor.
     * @param name product name.
     * @param expireDate expire date.
     * @param createDate create date.
     * @param price product price.
     * @param canRecycle is cdn be recycled.
     */
    public Food(String name, Date expireDate, Date createDate, double price, boolean canRecycle) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.canRecycle = canRecycle;
        this.discount = 0D;
    }

    /**
     * Return wear of product for date in percent.
     * @param date date to calculate.
     * @return wear of product for date in percent.
     */
    public long productWear(Date date) {
        return (date.getTime() - this.createDate.getTime())
                / ((this.expireDate.getTime() - this.createDate.getTime()) /  100L);
    }

    /**
     * Set discount to product.
     * @param discount discount.
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * Name of product getter.
     * @return product name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Compare products for name.
     * @param o other product.
     * @return compare result.
     */
    @Override
    public int compareTo(Food o) {
        return this.name.compareTo(o.getName());
    }

    /**
     * Recycle getter.
     * @return is cdn be recycled.
     */
    public boolean isCanRecycle() {
        return this.canRecycle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(food.price, price) == 0
                && Double.compare(food.discount, discount) == 0
                && canRecycle == food.canRecycle
                && name.equals(food.name)
                && expireDate.equals(food.expireDate)
                && createDate.equals(food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expireDate, createDate, price, discount, canRecycle);
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", expireDate=" + expireDate
                + ", createDate=" + createDate
                + ", price=" + price
                + ", discount=" + discount
                + ", canRecycle=" + canRecycle
                + '}';
    }
}
