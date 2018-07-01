package ru.job4j.list;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
/**
 * SortUserTest
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 01.07.2018
 */
public class SortUserTest {

    @Test
    public void whenListThenReturnSortedList() {
        SortUser sorter = new SortUser();
        UserComparable first = new UserComparable("Tim", 40);
        UserComparable second = new UserComparable("Dave", 30);
        UserComparable third = new UserComparable("Stuart", 33);
        List<UserComparable> list = new ArrayList<>(Arrays.asList(first, second, third));
        int[] expect = new int[] {30, 33, 40};
        int[] res = new int[3];
        int pos = 0;
        for (UserComparable user : sorter.sort(list)) {
            res[pos++] = user.getAge();
        }
        assertArrayEquals(res, expect);
    }

    @Test
    public void whenListTwoDuplicateAgeThenReturnSortedList() {
        SortUser sorter = new SortUser();
        UserComparable first = new UserComparable("Tim", 33);
        UserComparable second = new UserComparable("Dave", 33);
        UserComparable third = new UserComparable("Stuart", 20);
        List<UserComparable> list = new ArrayList<>(Arrays.asList(first, second, third));
        String[] expect = new String[] {"Stuart", "Dave", "Tim"};
        String[] res = new String[3];
        int pos = 0;
        for (UserComparable user : sorter.sort(list)) {
            res[pos++] = user.getName();
        }
        assertArrayEquals(res, expect);
    }
}