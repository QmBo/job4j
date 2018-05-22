package ru.job4j.array;
/**
 * Check array.
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 0.1
 */
public class Check {

    /**
     * Check mono array.
     * @param data array.
     * @return is mono?
     */
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (int index = 1; index != data.length; index++) {
            if (data[0] != data[index]) {
                result = false;
                break;
            }
        }
        return result;
    }
}