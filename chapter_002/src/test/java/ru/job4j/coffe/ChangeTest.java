package ru.job4j.coffe;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ChangeTest {

    @Test
    public void whenPris35AndInsert50Then15() {
        Change changer = new Change(new int[] {10, 5, 2, 1});
        int[] exp = new int[] {10, 5};
        assertThat(changer.changes(50, 35), is(exp));
    }

    @Test
    public void whenPris12AndInsert50Then38() {
        Change changer = new Change(new int[] {10, 5, 2, 1});
        int[] exp = new int[] {10, 10, 10, 5, 2, 1};
        assertThat(changer.changes(50, 12), is(exp));
    }

    @Test
    public void whenPris50AndInsert50ThenNoChange() {
        Change changer = new Change(new int[] {10, 5, 2, 1});
        int[] exp = new int[0];
        assertThat(changer.changes(50, 50), is(exp));
    }
}