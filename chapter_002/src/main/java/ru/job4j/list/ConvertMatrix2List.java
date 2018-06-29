package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;
/**
 * ConvertMatrix2List
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 29.06.2018
 */
public class ConvertMatrix2List {
    /**
     * Array to List.
     * @param array input array.
     * @return list.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] arr : array) {
            for (int value : arr) {
                list.add(value);
            }
        }
        return list;
    }
}
