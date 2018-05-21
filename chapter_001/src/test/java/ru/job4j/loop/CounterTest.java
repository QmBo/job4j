package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CounterTest {

    @Test
    public void whenStart1AndFinish11ThenReturn30() {
        Counter counter = new Counter();
        int expected = 30;
        assertThat(counter.add(1, 11), is(expected));
    }
}