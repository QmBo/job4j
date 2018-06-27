package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.white.BishopWhite;
import ru.job4j.chess.firuges.white.PawnWhite;

import static org.junit.Assert.*;

public class LogicTest {

    @Test
    public void whenHasFigureOnWayThenFalse() {
        Logic logic = new Logic();
        logic.add(new BishopWhite(Cell.C3));
        logic.add(new PawnWhite(Cell.E5));
    }
}