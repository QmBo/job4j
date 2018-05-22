package ru.job4j.array;
/**
 * Turn array.
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 0.1
 */
public class Turn {

    /**
     * Turn array backwards.
     * @param array array.
     * @return array backwards.
     */
    public int[] turn(int[] array) {
        for (int index = 0; index < array.length - index - 1; index++) {
            int temp = array[index];
            array[index] = array[array.length - index - 1];
            array[array.length - index - 1] = temp;
        }
        return array;
    }
}
