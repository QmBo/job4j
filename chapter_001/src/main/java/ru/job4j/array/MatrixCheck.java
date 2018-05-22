package ru.job4j.array;
/**
 * Matrix Check.
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 0.1
 */
public class MatrixCheck {

    /**
     * Check diagonal on mono.
     * @param data matrix.
     * @return is mono?
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 1; i != data.length; i++) {
            if (data[0][0] != data[i][i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}