package ru.job4j.loop;

/**
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 0.1
 */
public class Board {

    /**
     * Draws a chess Board in pseudo-graphics.
     * @param width width of board.
     * @param height height of board.
     * @return chess Board.
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i != height; i++) {
            for (int j = 0; j != width; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}
