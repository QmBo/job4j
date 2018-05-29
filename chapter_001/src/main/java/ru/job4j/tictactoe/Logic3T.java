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
        boolean result = true;
        for (Figure3T[] aTable : this.table) {
            boolean[] temp = new boolean[3];
            for (int index = 0; index < this.table.length; index++) {
                temp[index] = aTable[index].hasMarkX();
            }
            result = this.checkWinner(temp);
            if (result) {
                break;
            }
        }
        if (!result) {
            for (int i = 0; i < this.table.length; i++) {
                boolean[] temp = new boolean[3];
                for (int j = 0; j < this.table.length; j++) {
                    temp[j] = this.table[j][i].hasMarkX();
                }
                result = this.checkWinner(temp);
                if (result) {
                    break;
                }
            }
        }
        if (!result) {
            boolean[] temp = new boolean[3];
            for (int i = 0; i < this.table.length; i++) {
                temp[i] = this.table[i][i].hasMarkX();
            }
            result = this.checkWinner(temp);
        }
        if (!result) {
            boolean[] temp = new boolean[3];
            for (int i = 0; i < this.table.length; i++) {
                temp[i] = this.table[i][this.table.length - i - 1].hasMarkX();
            }
            result = this.checkWinner(temp);
        }
        return result;
    }

    /**
     * Check win combination for O.
     * @return win.
     */
    public boolean isWinnerO() {
        boolean result = true;
        for (Figure3T[] aTable : this.table) {
            boolean[] temp = new boolean[3];
            for (int index = 0; index < this.table.length; index++) {
                temp[index] = aTable[index].hasMarkO();
            }
            result = this.checkWinner(temp);
            if (result) {
                break;
            }
        }
        if (!result) {
            for (int i = 0; i < this.table.length; i++) {
                boolean[] temp = new boolean[3];
                for (int j = 0; j < this.table.length; j++) {
                    temp[j] = this.table[j][i].hasMarkO();
                }
                result = this.checkWinner(temp);
                if (result) {
                    break;
                }
            }
        }
        if (!result) {
            boolean[] temp = new boolean[3];
            for (int i = 0; i < this.table.length; i++) {
                temp[i] = this.table[i][i].hasMarkO();
            }
            result = this.checkWinner(temp);
        }
        if (!result) {
            boolean[] temp = new boolean[3];
            for (int i = 0; i < this.table.length; i++) {
                temp[i] = this.table[i][this.table.length - i - 1].hasMarkO();
            }
            result = this.checkWinner(temp);
        }
        return result;
    }

    /**
     * Helper for checker.
     * @param toCheck line.
     * @return win.
     */
    private boolean checkWinner(boolean[] toCheck) {
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