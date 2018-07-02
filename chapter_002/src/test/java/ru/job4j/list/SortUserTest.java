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

    @Test
    public void whenListThenReturnListSortedToNameLength() {
        SortUser sorter = new SortUser();
        UserComparable first = new UserComparable("Tim", 33);
        UserComparable second = new UserComparable("Dave", 33);
        UserComparable third = new UserComparable("Stuart", 20);
        List<UserComparable> list = new ArrayList<>(Arrays.asList(second, first, third));
        String[] expect = new String[] {"Tim", "Dave", "Stuart"};
        String[] res = new String[3];
        int pos = 0;
        for (UserComparable user : sorter.sortNameLength(list)) {
            res[pos++] = user.getName();
        }
        assertArrayEquals(res, expect);
    }

    @Test
    public void whenListHasDuplicateNameThenReturnListSortedToNameAndAge() {
        SortUser sorter = new SortUser();
        UserComparable first = new UserComparable("Tim", 25);
        UserComparable second = new UserComparable("Dave", 30);
        UserComparable third = new UserComparable("Tim", 20);
        UserComparable forth = new UserComparable("Dave", 25);
        List<UserComparable> list = new ArrayList<>(Arrays.asList(first, second, third, forth));
        String[] expect = new String[] {"Dave 25", "Dave 30", "Tim 20", "Tim 25"};
        String[] res = new String[4];
        int pos = 0;
        for (UserComparable user : sorter.sortByAllFields(list)) {
            res[pos++] = user.toString();
        }
        assertArrayEquals(res, expect);
    }
}