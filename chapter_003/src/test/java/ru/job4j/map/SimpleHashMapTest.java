package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {
    SimpleHashMap<String, Integer> map;

    @Before
    public void setUp() {
        map = new SimpleHashMap<>(4);
        map.insert("a", 1);
        map.insert("b", 2);
        map.insert("c", 3);
        map.insert("d", 4);
    }

    @Test
    public void whenAddNodeThenSizRise() {
        map.insert(null, null);
        assertThat(map.insert("e", 5), is(true));
    }

    @Test
    public  void whenNodeInMapThenUseGetCReturn2() {
        assertThat(map.get("c"), is(3));
    }

    @Test
    public  void whenNodeDeleteFromMapThenUseGetCReturnNull() {
        assertThat(map.delete("c"), is(true));
        assertNull(map.get("c"));
    }

    @Test
    public void whenIteratorThenAllValues() {
        Iterator<Integer> it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        int result = 0;
        while (it.hasNext()) {
           result += it.next();
        }
        assertThat(result, is(10));
    }
}