package ru.job4j.chess.firuges.white;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.ImposibleMoveException;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PawnWhite extends Figure {

    public PawnWhite(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImposibleMoveException {
        if (source.y == 1) {
            if (source.x != dest.x || dest.y - source.y > 2 || dest.y - source.y < 0) {
                throw new ImposibleMoveException("Пешка должна ходить по прямой на одну или две клетки.");
            }
        } else {
            if (source.x != dest.x || dest.y - source.y > 1 || dest.y - source.y < 0) {
                throw new ImposibleMoveException("Пешка должна ходить по прямой на одну клетки.");
            }
        }
        return super.way(source, dest);
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnWhite(dest);
    }
}
