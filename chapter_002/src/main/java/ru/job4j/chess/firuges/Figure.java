package ru.job4j.chess.firuges;

public abstract class Figure {
    final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    public Cell position() {
        return this.position;
    }

    public Cell[] way(Cell source, Cell dest) throws ImposibleMoveException {
        int destX = dest.x;
        int destY = dest.y;
        int posX = source.x;
        int posY = source.y;
        int count = 0;
        int xMove = Integer.compare(destX, posX);
        int yMove = Integer.compare(destY, posY);
        int counterX = Math.abs(destX - posX);
        int counterY = Math.abs(destY - posY);
        int arrayLength = counterX > counterY ? counterX : counterY;
        Cell[] moves = new Cell[arrayLength];
        posX += xMove;
        posY += yMove;
        for (int i = 0; i != arrayLength; i++) {
            for (Cell cell : Cell.values()) {
                if (posX == cell.x && posY == cell.y) {
                    moves[count++] = cell;
                    posX += xMove;
                    posY += yMove;
                    break;
                }
            }
        }
        return moves;
    }

    public String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );
    }

    public abstract Figure copy(Cell dest);
}
