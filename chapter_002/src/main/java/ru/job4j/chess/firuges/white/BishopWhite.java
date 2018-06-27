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
        Cell[] result = new Cell[0];
        int[][] temp = new int[8][2];
        int position = 0;
        if (dest.x > source.x && dest.y > source.y) {
            int x = source.x + 1;
            int y = source.y + 1;
            while (x < 8 && y < 8) {
                temp[position][0] = x++;
                temp[position++][1] = y++;
            }
            int[][] validPos = Arrays.copyOf(temp, position);
            result = buildWay(dest, validPos);
        } else if (dest.x > source.x && dest.y < source.y) {
            int x = source.x + 1;
            int y = source.y - 1;
            while (x < 8 && y > -1) {
                temp[position][0] = x++;
                temp[position++][1] = y--;
            }
            int[][] validPos = Arrays.copyOf(temp, position);
            result = buildWay(dest, validPos);
        } else if (dest.x < source.x && dest.y > source.y) {
            int x = source.x - 1;
            int y = source.y + 1;
            while (x > -1 && y < 8) {
                temp[position][0] = x--;
                temp[position++][1] = y++;
            }
            int[][] validPos = Arrays.copyOf(temp, position);
            result = buildWay(dest, validPos);
        } else if (dest.x < source.x && dest.y < source.y) {
            int x = source.x - 1;
            int y = source.y - 1;
            while (x > -1 && y > -1) {
                temp[position][0] = x--;
                temp[position++][1] = y--;
            }
            int[][] validPos = Arrays.copyOf(temp, position);
            result = buildWay(dest, validPos);
        } else {
            throw new ImposibleMoveException("Слон должен ходить по диагонали.");
        }
        return result;
    }

    private Cell[] buildWay(Cell dest, int[][] validPos) throws ImposibleMoveException {
        Cell[] result = new Cell[0];
        for (int step = 0; step != validPos.length; step++) {
            if (validPos[step][0] == dest.x && validPos[step][1] == dest.y) {
                result = new Cell[++step];
                for (int index = 0; index < step; index++) {
                    for (Cell cell : Cell.values()) {
                        if (cell.x == validPos[index][0] && cell.y == validPos[index][1]) {
                            result[index] = cell;
                            break;
                        }
                    }
                }
                break;
            }
        }
        if (result.length == 0) {
            throw new ImposibleMoveException("Слон должен ходить по диагонали.");
        }
        return result;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopWhite(dest);
    }
}
