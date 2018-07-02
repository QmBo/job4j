package ru.job4j.comparator;

import java.util.Comparator;
/**
 * ListCompare
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 02.07.2018
 */
public class ListCompare implements Comparator<String> {
    /**
     * Compares to strings.
     * @param left left string.
     * @param right right string.
     * @return result of compares.
     */
    @Override
    public int compare(String left, String right) {
        int result = 0;
        int index;
        for (index = 0; index < left.length(); index++) {
            if (index == right.length()) {
                result = 1;
                break;
            }
            result = left.charAt(index) - right.charAt(index);
            if (result != 0) {
                break;
            }
        }
        if (result == 0 && index != right.length()) {
            result = -1;
        }
        return result;
    }
}