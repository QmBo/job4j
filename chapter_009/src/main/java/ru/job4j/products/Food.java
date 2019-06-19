package ru.job4j.products;

import java.util.Date;
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
     * Constructor.
     * @param name product name.
     * @param expireDate expire date.
     * @param createDate create date.
     * @param price product price.
     */
    public Food(String name, Date expireDate, Date createDate, double price) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
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
    public int compareTo(@SuppressWarnings("NullableProblems") Food o) {
        return this.name.compareTo(o.getName());
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
        if (Double.compare(food.price, price) != 0) {
            return false;
        }
        if (Double.compare(food.discount, discount) != 0) {
            return false;
        }
        if (!name.equals(food.name)) {
            return false;
        }
        if (!expireDate.equals(food.expireDate)) {
            return false;
        }
        return createDate.equals(food.createDate);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + expireDate.hashCode();
        result = 31 * result + createDate.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(discount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Food{" + "name='" + name + '\'' + ", expireDate="
                + expireDate + ", createDate="
                + createDate + ", price="
                + price + ", discount="
                + discount + '}';
    }
}
