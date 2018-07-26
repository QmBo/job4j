package ru.job4j.lists;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DynamicArrayListTest {
    private DynamicArrayList<String> list;

    @Before
    public void setUp() {
        list = new DynamicArrayList<>(4);
        list.add("11");
        list.add("22");
        list.add("33");
    }

    @Test
    public void whenNoModificationThenUseGetOneIs22() {
        Iterator<String> it = list.iterator();
        assertThat(list.get(1), is("22"));
        it.next();
        assertThat(it.next(), is("22"));
    }

    @Test
    public void whenAddElementThenContainerRise() {
        list.add("Test");
        list.add("Test2");
        list.add("Test3");
        assertThat(list.get(5), is("Test3"));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenThrowIterator() {
        Iterator<String> it = list.iterator();
        list.add("test");
        assertThat(list.get(3), is("test"));
        it.next();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenThrowGet() {
        list.get(3);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenThrowIteratorNoNext() {
        Iterator<String> it = list.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
    }
}