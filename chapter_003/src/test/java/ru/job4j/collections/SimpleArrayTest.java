package ru.job4j.collections;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * SimpleArrayTest
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 20.07.2018
 */
public class SimpleArrayTest {
    private SimpleArray<String> sa;

    @Before
    public void setUp() {
        sa = new SimpleArray<>(10);
        sa.add("test");
        sa.add("1");
        sa.add("TestTest");
    }

    @Test
    public void whenElementAddToArrayThenArrayHasElement() {
        assertThat(sa.get(2), is("TestTest"));
    }

    @Test
    public void whenElementDeletedThenArrayHasNotElement() {
        sa.delete(1);
        boolean find = false;
        for (Iterator<String> it = sa.iterator(); it.hasNext();) {
            String value = it.next();
            if (value.equals("1")) {
                find = true;
                break;
            }
        }
        assertThat(find, is(false));
        assertThat(sa.get(1), is("TestTest"));
    }

    @Test
    public void whenIteratorThenShowAllElements() {
        Iterator<String> it = sa.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("1"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("TestTest"));
        assertThat(it.hasNext(), is(false));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenSetElementThenNewElement() {
        sa.set(0, "TTT");
        assertThat(sa.get(0), is("TTT"));
    }

    @Test
    public void whenAddNullThenNull() {
        sa.add(null);
        assertNull(sa.get(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void mastThrowNoSuchElementException() {
        Iterator<String> it = new SimpleArray<String>(10).iterator();
        it.next();
    }

    @Test(expected = IllegalStateException.class)
    public void mastThrowIllegalStateException() {
        sa.add("");
        sa.add("");
        sa.add("");
        sa.add("");
        sa.add("");
        sa.add("");
        sa.add("");
        sa.add("");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void mastThrowIndexOutOfBoundsException() {
        sa.get(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void deleteMastThrowIndexOutOfBoundsException() {
        sa.delete(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setMastThrowIndexOutOfBoundsException() {
        sa.set(3, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorMastThrowIllegalArgumentException() {
        sa = new SimpleArray<>(-1);
    }
}