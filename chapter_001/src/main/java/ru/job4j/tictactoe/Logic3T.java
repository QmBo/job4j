package ru.job4j.tictactoe;

/**
 * Logic.
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 0.1
 */
public class Logic3T {
    /**
     * Desk status.
     */
    private final Figure3T[][] table;

    /**
     * Constructor.
     * @param table desk.
     */
    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    /**
     * Check win combination for X.
     * @return win.
     */
    public boolean isWinnerX() {
        boolean[][] status = new boolean[3][3];
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                status[i][j] = this.table[i][j].hasMarkX();
            }
        }
        return this.winnerCheck(status);
    }

    /**
     * Check win combination for O.
     * @return win.
     */
    public boolean isWinnerO() {
        boolean[][] status = new boolean[3][3];
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                status[i][j] = this.table[i][j].hasMarkO();
            }
        }
        return this.winnerCheck(status);
    }

    /**
     * Check win combination in matrix.
     * @param status matrix wis game status.
     * @return hav a winner.
     */
    private boolean winnerCheck(boolean[][] status) {
        boolean result = true;
        for (boolean[] array : status) {
            result = this.arrayCheck(array);
            if (result) {
                break;
            }
        }
        if (!result) {
            for (int i = 0; i < status.length; i++) {
                boolean[] temp = new boolean[3];
                for (int j = 0; j < status.length; j++) {
                    temp[j] = status[j][i];
                }
                result = this.arrayCheck(temp);
                if (result) {
                    break;
                }
            }
        }
        if (!result) {
            boolean[] temp = new boolean[3];
            for (int i = 0; i < status.length; i++) {
                temp[i] = status[i][i];
            }
            result = this.arrayCheck(temp);
        }
        if (!result) {
            boolean[] temp = new boolean[3];
            for (int i = 0; i < status.length; i++) {
                temp[i] = status[i][status.length - i - 1];
            }
            result = this.arrayCheck(temp);
        }
        return result;
    }

    /**
     * Helper for checker.
     * @param toCheck line.
     * @return win.
     */
    private boolean arrayCheck(boolean[] toCheck) {
        boolean result = true;
        for (int index = 0; index < toCheck.length; index++) {
            if (!toCheck[index]) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Check available moves.
     * @return available.
     */
    public boolean hasGap() {
        boolean result = false;
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                if (!(this.table[i][j].hasMarkO() || this.table[i][j].hasMarkX())) {
                    result = true;
                    break;
                }
                if (result) {
                    break;
                }
            }
        }
        return result;
    }
}