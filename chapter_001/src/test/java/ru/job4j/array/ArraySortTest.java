package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ArraySortTest {

    @Test
    public void whenTwoSortedArraysThenOneSortedArray() {
        ArraySort sorter = new ArraySort();
        int[] first = new int[] {1, 4, 8, 12, 44, 56};
        int[] second = new int[] {3, 6, 10, 11, 13, 14};
        int[] res = sorter.sort(first, second);
        int[] expect = new int[] {1, 3, 4, 6, 8, 10, 11, 12, 13, 14, 44, 56};
        assertThat(res, is(expect));
    }

    @Test
    public void whenTwoSortedArraysSeriesThenOneSortedArray() {
        ArraySort sorter = new ArraySort();
        int[] first = new int[] {2, 4, 6, 8, 10, 12};
        int[] second = new int[] {1, 3, 5, 7, 9, 11};
        int[] res = sorter.sort(first, second);
        int[] expect = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        assertThat(res, is(expect));
    }

    @Test
    public void whenOneSortedArraysThenOneSortedArray() {
        ArraySort sorter = new ArraySort();
        int[] first = new int[] {2, 4, 6, 8, 10, 12};
        int[] second = new int[0];
        int[] res = sorter.sort(first, second);
        int[] expect = new int[] {2, 4, 6, 8, 10, 12};
        assertThat(res, is(expect));
    }

    @Test
    public void whenOneOfTwoArraysShortThenOneSortedArray() {
        ArraySort sorter = new ArraySort();
        int[] first = new int[] {2, 4, 6, 8, 10, 12};
        int[] second = new int[] {77};
        int[] res = sorter.sort(first, second);
        int[] expect = new int[] {2, 4, 6, 8, 10, 12, 77};
        assertThat(res, is(expect));
    }

    @Test
    public void whenOneOfTwoArraysShortMinusThenOneSortedArray() {
        ArraySort sorter = new ArraySort();
        int[] first = new int[] {2, 4, 6, 8, 10, 12};
        int[] second = new int[] {-77};
        int[] res = sorter.sort(first, second);
        int[] expect = new int[] {-77, 2, 4, 6, 8, 10, 12};
        assertThat(res, is(expect));
    }

    @Test
    public void whenArraysEmptyThenEmptyArray() {
        ArraySort sorter = new ArraySort();
        int[] first = new int[0];
        int[] second = new int[0];
        int[] res = sorter.sort(first, second);
        int[] expect = new int[] {};
        assertThat(res, is(expect));
    }
}