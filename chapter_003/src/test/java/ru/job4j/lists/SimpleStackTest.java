package ru.job4j.lists;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleStackTest {
    private SimpleStack<String> stack;

    @Before
    public void setUp() {
        stack = new SimpleStack<>();
        stack.push("00");
        stack.push("11");
        stack.push("22");
    }

    @Test(expected = NoSuchElementException.class)
    public void whenUsePullThenGetLastAndDelete() {
        assertThat(stack.poll(), is("22"));
        stack.push("test");
        assertThat(stack.poll(), is("test"));
        assertThat(stack.poll(), is("11"));
        assertThat(stack.poll(), is("00"));
        stack.poll();
    }
}