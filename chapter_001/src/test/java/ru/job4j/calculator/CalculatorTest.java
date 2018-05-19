package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Victor Egorov (qrioflat@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class CalculatorTest {

    /**
     * Test Addition.
     */
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * Test Subtraction.
     */
    @Test
    public void whenSubtractTenMinusSevenThenThree() {
        Calculator calc = new Calculator();
        calc.subtract(10D, 7D);
        double result = calc.getResult();
        double expected = 3D;
        assertThat(result, is(expected));
    }

    /**
     * Test Division.
     */
    @Test
    public void whenDivTenOnTwoThenFive() {
        Calculator calc = new Calculator();
        calc.div(10D, 2D);
        double result = calc.getResult();
        double expected = 5D;
        assertThat(result, is(expected));
    }

    /**
     * Test Multiplication.
     */
    @Test
    public void whenMultipleTwoAtFourThenEight() {
        Calculator calc = new Calculator();
        calc.multiple(2D, 4D);
        double result = calc.getResult();
        double expected = 8D;
        assertThat(result, is(expected));
    }
}