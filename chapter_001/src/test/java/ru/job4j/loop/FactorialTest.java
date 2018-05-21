package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FactorialTest {

    @Test
    public void when5ThenFactorialIs120() {
        Factorial factorial = new Factorial();
        int expected = 120;
        assertThat(expected, is(factorial.calc(5)));
    }

    @Test
    public void when0ThenFactorialIs1() {
        Factorial factorial = new Factorial();
        int expected = 1;
        assertThat(expected, is(factorial.calc(0)));
    }
}