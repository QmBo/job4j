package ru.job4j.chess.firuges.white;

import ru.job4j.chess.firuges.ImposibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopWhite extends Figure {

    public BishopWhite(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImposibleMoveException {
        int position = 0;
        int xMove = dest.x - source.x;
        int yMove = dest.y - source.y;
        if (xMove == 0 || yMove == 0 || Math.abs(xMove) != Math.abs(yMove)) {
            throw new ImposibleMoveException("Слон должен ходить по диагонали.");
        }
        int steps = Math.abs(xMove);
        int xDirection = xMove > 0 ? 1 : -1;
        int yDirection = yMove > 0 ? 1 : -1;
        int x = source.x + xDirection;
        int y = source.y + yDirection;
        int[][] temp = new int[steps][2];
        while (position != steps) {
            temp[position][0] = x;
            temp[position++][1] = y;
            x += xDirection;
            y += yDirection;
        }
        Cell[] result = new Cell[steps];
        for (int index = 0; index < steps; index++) {
            for (Cell cell : Cell.values()) {
                if (cell.x == temp[index][0] && cell.y == temp[index][1]) {
                    result[index] = cell;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopWhite(dest);
    }
}
