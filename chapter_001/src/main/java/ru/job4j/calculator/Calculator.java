package ru.job4j.calculator;

/**
 * Calculator.
 * @author Victor Egorov (qrioflat@gmail.com).
 */
public class Calculator {
    /**
     * Result of operation.
     */
    private double result;

    /**
     * Addition two numbers.
     * @param first first number.
     * @param second second number.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Subtraction two numbers.
     * @param first first number.
     * @param second second number.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Division two numbers.
     * @param first first number.
     * @param second second number.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Multiplication two numbers.
     * @param first first number.
     * @param second second number.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * Return the result last operation.
     * @return result last operation.
     */
    public double getResult() {
        return this.result;
    }
}