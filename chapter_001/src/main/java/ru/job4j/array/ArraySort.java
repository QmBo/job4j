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
        check(first, second, 0, 0, 0);
        return this.result;
    }

    /**
     * Main sort algorithm.
     * @param first first array.
     * @param second second array.
     * @param firstPos position to check of first array.
     * @param secondPos position to check of second array.
     * @param index position in new array.
     */
    private void check(int[] first, int[] second, int firstPos, int secondPos, int index) {
        if (firstPos < first.length && secondPos < second.length) {
            if (first[firstPos] < second[secondPos]) {
                this.result[index] = first[firstPos];
                check(first, second, firstPos + 1, secondPos, ++index);
            } else {
                this.result[index] = second[secondPos];
                check(first, second, firstPos, secondPos + 1, ++index);
            }
        } else if (secondPos < second.length) {
            close(index, second, secondPos);
        } else if (firstPos < first.length) {
            close(index, first, firstPos);
        }
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
