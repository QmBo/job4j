package ru.job4j.array;

import java.util.Arrays;
/**
 * Array duplicate finder.
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 0.1
 */
public class ArrayDuplicate {

    /**
     * Remove duplicate from array.
     * @param array array.
     * @return array without duplicate.
     */
    public String[] remove(String[] array) {
        int remove = 0;
        for (int step = 0; step < array.length - remove; step++) {
            for (int index = step + 1; index < array.length - remove; index++) {
                if (array[step] == array[index]) {
                    String temp = array[index];
                    array[index] = array[array.length - 1 - remove];
                    array[array.length - 1 - remove] = temp;
                    remove++;
                    index--;
                }
            }
        }
        return Arrays.copyOf(array, array.length - remove);
    }
}
