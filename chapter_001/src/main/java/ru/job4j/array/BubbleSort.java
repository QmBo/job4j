package ru.job4j.array;
/**
 * Bubble Sort.
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 0.1
 */
public class BubbleSort {

    /**
     * Sort array low to high.
     * @param array array.
     * @return sorted array.
     */
    public int[] sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int index = 0; index < array.length - i; index++) {
                if (array[index] > array[index + 1]) {
                    int temp = array[index + 1];
                    array[index + 1] = array[index];
                    array[index] = temp;
                }
            }
        }
        return array;
    }
}
