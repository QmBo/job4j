package ru.job4j.calculator;

import static java.lang.Math.*;
/**
 * TriangleCalculator
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 10.06.2019
 */
public class TriangleCalculator extends Calculator {
    /**
     * Cos.
     * @param alpha angle.
     */
    public void cosines(double alpha) {
        this.result = cos(toRadians(alpha));
        this.hasResult = true;
    }

    /**
     * Sin.
     * @param alpha angle.
     */
    public void sinus(double alpha) {
        this.result = sin(toRadians(alpha));
        this.hasResult = true;
    }

    /**
     * Tang.
     * @param alpha angle.
     */
    public void tang(double alpha) {
        this.result = tan(toRadians(alpha));
        this.hasResult = true;
    }

    /**
     * Ctn.
     * @param alpha angle.
     */
    public void cotangent(double alpha) {
        this.result = 1D / tan(toRadians(alpha));
        this.hasResult = true;
    }
}
