package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests.
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version $Id$
 * @since 0.1
 */
public class MaxTest {

    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenSecondLessFirst() {
        Max maxim = new Max();
        int result = maxim.max(4, 2);
        assertThat(result, is(4));
    }

    @Test
    public void whenSecondEqualsFirst() {
        Max maxim = new Max();
        int result = maxim.max(2, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenThirdHighFirstAndSecondThenReturnThird() {
        Max maxim = new Max();
        int result = maxim.max(4, 2, 7);
        assertThat(result, is(7));
    }

    @Test
    public void whenSecondHighFirstAndThirdThenReturnSecond() {
        Max maxim = new Max();
        int result = maxim.max(4, 15, 7);
        assertThat(result, is(15));
    }

    @Test
    public void whenSecondEqualsFirstAndThird() {
        Max maxim = new Max();
        int result = maxim.max(2, 2, 2);
        assertThat(result, is(2));
    }
}