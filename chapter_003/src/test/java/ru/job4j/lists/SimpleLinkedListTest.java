package ru.job4j.lists;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleLinkedListTest {
    private SimpleLinkedList<String> list;

    @Before
    public void setUp() {
        list = new SimpleLinkedList<>();
        list.add("00");
        list.add("11");
        list.add("22");
    }

    @Test
    public void whenNoModificationThenUseGetOneIs11() {
        Iterator<String> it = list.iterator();
        assertThat(list.get(1), is("11"));
        it.next();
        assertThat(it.next(), is("11"));
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

    @SuppressWarnings("Duplicates")
    @Test(expected = NoSuchElementException.class)
    public void whenThrowIteratorNoNext() {
        Iterator<String> it = list.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
    }
}