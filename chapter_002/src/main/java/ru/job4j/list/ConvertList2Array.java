package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;
/**
 * ConvertList2Array
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 29.06.2018
 */
public class ConvertList2Array {
    /**
     * List to array.
     * @param list input list.
     * @param rows number of rows.
     * @return array.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        List<Integer> temp = new ArrayList<>(list);
        int cells = temp.size() / rows;
        if (temp.size() % rows != 0) {
            cells += 1;
        }
        int[][] array = new int[rows][cells];
        for (int row = 0; row < rows; row++) {
            for (int cell = 0; cell < cells; cell++) {
                if (!temp.isEmpty()) {
                    array[row][cell]  = temp.remove(0);
                } else {
                    array[row][cell] = 0;
                }
            }
        }
        return array;
    }
}