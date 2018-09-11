package ru.job4j.bomber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Board
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 05.09.2018
 */
public class Board {
    /**
     * Board
     */
    private final ReentrantLock[][] board;
    /**
     * Hero position.
     */
    private Cell heroPos;

    /**
     * Constructor.
     * @param size size of board.
     */
    public Board(int size) {
        this.board = new ReentrantLock[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new ReentrantLock();
            }
        }
        this.heroPos = new Cell(2, 2);
    }

    /**
     * Thread runner.
     */
    public void runHero() {
        new Thread(
                () -> {
                    this.board[this.heroPos.x][this.heroPos.y].tryLock();
                    while (!Thread.currentThread().isInterrupted()) {
                        for (int i = 0; i < this.board.length; i++) {
                            for (int j = 0; j < this.board.length; j++) {
                                if (this.board[i][j].isLocked()) {
                                    System.out.printf("Cell[%s][%s] is lock\n", i, j);
                                }
                            }
                        }
                        List<Cell> validDirection = this.getNewPosition(this.heroPos.x, this.heroPos.y);
                        Collections.shuffle(validDirection);
                        for (Cell cell : validDirection) {
                            if (move(this.heroPos, cell)) {
                                break;
                            }
                        }
                        try {
                            System.out.println("sleep");
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }

    /**
     * Available moves getter.
     * @param x x.
     * @param y y.
     * @return list of available moves.
     */
    private List<Cell> getNewPosition(int x, int y) {
        List<Cell> result = new ArrayList<>();
        if (y != 0) {
            result.add(new Cell(x, y - 1));
        }
        if (x != 0) {
            result.add(new Cell(x - 1, y));
        }
        if (y < this.board.length - 1) {
            result.add(new Cell(x, y + 1));
        }
        if (x < this.board.length - 1) {
            result.add(new Cell(x + 1, y));
        }
        return result;
    }

    /**
     * Hero mover.
     * @param source source.
     * @param dest destination.
     * @return is move.
     */
    public boolean move(Cell source, Cell dest) {
        boolean result = false;
        try {
            if (this.board[dest.x][dest.y].tryLock(500, TimeUnit.MILLISECONDS)) {
                this.heroPos = dest;
                this.board[source.x][source.y].unlock();
                System.out.printf("Lock %s\n", dest.toString());
                result = true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Cell class.
     */
    private class Cell {
        private final int x;
        private final int y;

        private Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Cell{" + "x=" + x + ", y=" + y + '}';
        }
    }
}
