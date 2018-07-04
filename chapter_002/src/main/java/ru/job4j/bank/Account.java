package ru.job4j.bank;
/**
 * Account
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 04.07.2018
 */
public class Account {
    private double value;
    private String requisites;

    /**
     * Constructor.
     * @param value value.
     * @param requisites requisites.
     */
    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * Value getter.
     * @return value.
     */
    public double getValue() {
        return value;
    }

    /**
     * Requisites getter.
     * @return requisites.
     */
    public String getRequisites() {
        return requisites;
    }

    /**
     * Replenishment of amount.
     * @param amount amount.
     */
    public void debit(double amount) {
        this.value += amount;
    }

    /**
     * Reduces value.
     * @param amount amount.
     */
    public void credit(double amount) {
        this.value -= amount;
    }
}
