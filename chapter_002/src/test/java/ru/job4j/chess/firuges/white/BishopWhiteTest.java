package ru.job4j.chess.firuges.white;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.ImposibleMoveException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static ru.job4j.chess.firuges.Cell.*;

public class BishopWhiteTest {

    @Test
    public void whenMoveCorrectToRightAndDownThenReturnWay() {
        BishopWhite bishopMove = new BishopWhite(C3);
        Cell[] exp = new Cell[] {D4, E5, F6, G7};
        try {
            Cell[] res = bishopMove.way(bishopMove.position(), G7);
            assertThat(res, is(exp));
        } catch (ImposibleMoveException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenMoveCorrectToRightAndUpThenReturnWay() {
        BishopWhite bishopMove = new BishopWhite(C7);
        Cell[] exp = new Cell[] {D6, E5, F4, G3};
        try {
            Cell[] res = bishopMove.way(bishopMove.position(), G3);
            assertThat(res, is(exp));
        } catch (ImposibleMoveException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenMoveCorrectToLeftAndDownThenReturnWay() {
        BishopWhite bishopMove = new BishopWhite(G3);
        Cell[] exp = new Cell[] {F4, E5, D6};
        try {
            Cell[] res = bishopMove.way(bishopMove.position(), D6);
            assertThat(res, is(exp));
        } catch (ImposibleMoveException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenMoveCorrectToLeftAndUpThenReturnWay() {
        BishopWhite bishopMove = new BishopWhite(G7);
        Cell[] exp = new Cell[] {F6, E5, D4, C3, B2, A1};
        try {
            Cell[] res = bishopMove.way(bishopMove.position(), A1);
            assertThat(res, is(exp));
        } catch (ImposibleMoveException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenLineMoveIncorrectThenReturnException() {
        BishopWhite bishopMove = new BishopWhite(G7);
        try {
            Cell[] res = bishopMove.way(bishopMove.position(), G2);
        } catch (ImposibleMoveException ime) {
         assertThat("Слон должен ходить по диагонали.", is(ime.getMessage()));
        }
    }

    @Test
    public void whenMoveIncorrectThenReturnException() {
        BishopWhite bishopMove = new BishopWhite(G7);
        try {
            Cell[] res = bishopMove.way(bishopMove.position(), C2);
        } catch (ImposibleMoveException ime) {
         assertThat("Слон должен ходить по диагонали.", is(ime.getMessage()));
        }
    }

    @Test
    public void whenNoMoveThenReturnException() {
        BishopWhite bishopMove = new BishopWhite(G7);
        try {
            Cell[] res = bishopMove.way(bishopMove.position(), G7);
        } catch (ImposibleMoveException ime) {
         assertThat("Слон должен ходить по диагонали.", is(ime.getMessage()));
        }
    }
}