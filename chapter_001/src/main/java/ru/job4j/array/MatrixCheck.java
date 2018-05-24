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
                for (int j = 1; j != data.length; j++) {
                    if (data[data.length - 1][0] != data[data.length - 1 - j][j]) {
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }
}