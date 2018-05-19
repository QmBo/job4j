package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

/**
 * Tests.
 * @author Victor Egorov (qrioflat@gmail.com).
 */
public class PointTest {
    /**
     * Test distance between points.
     */
    @Test
    public void distanceTo() {
        Point a = new Point(0, 1);
        Point b = new Point(2, 5);
        double result = a.distanceTo(b);
        assertThat(result, closeTo(4.472, 0.001));
    }
}