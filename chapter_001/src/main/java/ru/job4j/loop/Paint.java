package ru.job4j.loop;

/**
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 0.1
 */
public class Paint {

    /**
     * Draws a pyramid in pseudo-graphics.
     * @param height height of pyramid.
     * @return pyramid.
     */
    public String pyramid(int height) {
        StringBuilder screen = new StringBuilder();
        int weight = 2 * height - 1;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (row >= height - column - 1 && row + height - 1 >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}