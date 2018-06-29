package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;
/**
 * ConvertList
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 29.06.2018
 */
public class ConvertList {
    /**
     * Covert list arrays in integer list.
     * @param list input list.
     * @return list.
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result  = new ArrayList<>();
        for (int[] arr : list) {
            for (int value : arr) {
                result.add(value);
            }
        }
        return result;
    }
}
