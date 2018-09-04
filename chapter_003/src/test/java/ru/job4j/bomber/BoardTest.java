package ru.job4j.bomber;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void whenStartThenMove() throws InterruptedException {
         Board board = new Board(5);
         board.runHero();
         Thread.sleep(5000);
    }
}