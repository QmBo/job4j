package ru.job4j.calculator;

/**
 * Calculator.
 * @author Victor Egorov (qrioflat@gmail.com).
 */
public class Calculator {
    /**
     * Result of operation.
     */
    protected double result;
    /**
     * Has result.
     */
    protected boolean hasResult = false;

    private boolean hasResult = false;

    /**
     * Addition two numbers.
     * @param first first number.
     * @param second second number.
     */
    public void add(double first, double second) {
        this.result = first + second;
        this.hasResult = true;
    }

    /**
     * Subtraction two numbers.
     * @param first first number.
     * @param second second number.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
        this.hasResult = true;
    }

    /**
     * Division two numbers.
     * @param first first number.
     * @param second second number.
     */
    public void div(double first, double second) {
        this.result = first / second;
        this.hasResult = true;
    }

    /**
     * Multiplication two numbers.
     * @param first first number.
     * @param second second number.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
        this.hasResult = true;
    }

    /**
     * Return the result last operation.
     * @return result last operation.
     */
    public double getResult() {
        return this.result;
    }

    public boolean isHasResult() {
        return this.hasResult;
    }
}