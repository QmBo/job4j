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
        int xMove = dest.x - source.x;
        int yMove = dest.y - source.y;
        if (xMove == 0 || yMove == 0 || Math.abs(xMove) != Math.abs(yMove)) {
            throw new ImposibleMoveException("Слон должен ходить по диагонали.");
        }
        return super.way(source, dest);
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopWhite(dest);
    }
}
