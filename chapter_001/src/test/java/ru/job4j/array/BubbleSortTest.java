package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * Tests.
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 0.1
 */
public class BubbleSortTest {

    @Test
    public void whenInEvenArray87654321Then12345678() {
        BubbleSort bubble = new BubbleSort();
        int[] result = bubble.sort(new int[] {8, 7, 6, 5, 4, 3, 2, 1});
        int[] expect = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
        assertThat(result, is(expect));
    }

    @Test
    public void whenInOddArray87654321Then12345678() {
        BubbleSort bubble = new BubbleSort();
        int[] result = bubble.sort(new int[] {8, 7, 6, 5, 9, 4, 3, 2, 1});
        int[] expect = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(result, is(expect));
    }
}