package ru.job4j.deportament;

import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentSortTest {
    @Test
    public void whenArrayStringsThenSortedUp() {
        DepartmentSort sorter = new DepartmentSort();
        String[] input = new String[] {
                "K1\\SK1\\SSK2", "K2", "K1\\SK1", "K1\\SK2",
                "K1\\SK1\\SSK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        assertArrayEquals(new String[] {
                        "K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2",
                        "K1\\SK2", "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"},
                sorter.sortUp(input)
        );
    }

    @Test
    public void whenArrayStringsShortThenSortedUp() {
        DepartmentSort sorter = new DepartmentSort();
        String[] input = new String[] {
                "K1\\SK1\\SSK2", "K1\\SK2",
                "K1\\SK1\\SSK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        assertArrayEquals(new String[] {
                        "K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2",
                        "K1\\SK2", "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"},
                sorter.sortUp(input)
        );
    }

    @Test
    public void whenArrayStringsThenSortedDown() {
        DepartmentSort sorter = new DepartmentSort();
        String[] input = new String[] {
                "K1\\SK1\\SSK2", "K2", "K1\\SK1", "K1\\SK2",
                "K1\\SK1\\SSK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        assertArrayEquals(new String[] {
                        "K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1",
                        "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"},
                sorter.sortDown(input)
        );
    }

    @Test
    public void whenArrayStringsShortThenSortedDown() {
        DepartmentSort sorter = new DepartmentSort();
        String[] input = new String[] {
                "K1\\SK1\\SSK2", "K1\\SK2",
                "K1\\SK1\\SSK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        assertArrayEquals(new String[] {
                        "K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1",
                        "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"},
                sorter.sortDown(input)
        );
    }
}