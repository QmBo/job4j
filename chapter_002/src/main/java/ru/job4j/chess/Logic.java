package ru.job4j.chess;

import ru.job4j.chess.firuges.*;

import java.util.Optional;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findBy(source);
        try {
            if (index == -1) {
                throw new FigureNotFoundException("Фигура не найдена.");
            }
            Cell[] steps = new Cell[0];
            steps = this.figures[index].way(source, dest);
            for (Cell step : steps) {
                if (this.findBy(step) != -1) {
                    throw new OccupiedWayException("На пути стоит фигура.");
                }
            }
            this.figures[index] = this.figures[index].copy(dest);
            rst = true;
        } catch (FigureNotFoundException | ImposibleMoveException | OccupiedWayException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
