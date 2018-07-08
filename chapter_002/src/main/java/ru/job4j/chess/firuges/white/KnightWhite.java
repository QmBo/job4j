package ru.job4j.chess.firuges.white;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.ImposibleMoveException;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class KnightWhite extends Figure {

    public KnightWhite(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImposibleMoveException {
        int xMove = dest.x - source.x;
        int yMove = dest.y - source.y;
        if (Math.abs(xMove) != 2 || Math.abs(yMove) != 1) {
            if (Math.abs(yMove) != 2 || Math.abs(xMove) != 1) {
                throw new ImposibleMoveException("Конь Должен зодить буквой Г");
            }
        }
        return new Cell[] {dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new KnightWhite(dest);
    }
}
