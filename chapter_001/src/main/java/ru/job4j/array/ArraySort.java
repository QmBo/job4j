package ru.job4j.array;
/**
 * Array Sorter
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 0.1
 */
public class ArraySort {
    /**
     * Array to return.
     */
    private int[] result;

    /**
     * Join two sort arrays in one sort array.
     * @param first first array.
     * @param second second array.
     * @return sort array.
     */
    public int[] sort(int[] first, int[] second) {
        result = new int[first.length + second.length];
        int firstPos = 0;
        int secondPos = 0;
        int index = 0;
        while (firstPos < first.length && secondPos < second.length) {
            if (first[firstPos] < second[secondPos]) {
                this.result[index++] = first[firstPos++];
            } else {
                this.result[index++] = second[secondPos++];
            }
        }
        if (secondPos < second.length) {
            close(index, second, secondPos);
        } else if (firstPos < first.length) {
            close(index, first, firstPos);
        }
        return this.result;
    }

    /**
     * Helper. Close new array when one of arrays wos over.
     * @param index position in new array.
     * @param array not over array.
     * @param position start position in not over array,.
     */
    private void close(int index, int[] array, int position) {
        for (int i = position; i < array.length; i++) {
            this.result[index++] = array[i];
        }
    }
}
