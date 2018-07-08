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
public class KingWhite extends Figure {

    public KingWhite(final Cell position) {
        super(position);
    }


    @Override
    public Cell[] way(Cell source, Cell dest)throws ImposibleMoveException {
        int xMove = dest.x - source.x;
        int yMove = dest.y - source.y;
        if (Math.abs(xMove) != 1) {
            if (Math.abs(yMove) != 1) {
                throw new ImposibleMoveException(
                        "Король должен ходить горизонтально, вертикально или по диагонали на одну клетку."
                );
            }
        }
        if (xMove == 0 || yMove == 0 || Math.abs(xMove) != Math.abs(yMove)) {
            if (xMove != 0) {
                if (yMove != 0)  {
                    throw new ImposibleMoveException(
                            "Король должен ходить горизонтально, вертикально или по диагонали на одну клетку."
                    );
                }
            }
        }
        return new Cell[] {dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingWhite(dest);
    }
}
