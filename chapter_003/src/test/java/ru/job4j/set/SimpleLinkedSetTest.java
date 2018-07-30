package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@SuppressWarnings("Duplicates")
public class SimpleLinkedSetTest {
    SimpleLinkedSet<String> set;
    @Before
    public void setUp() {
        set = new SimpleLinkedSet<>();
        set.add("00");
        set.add("11");
        set.add("22");
    }

    @Test
    public void whenAddElementThenElementAdd() {
        Iterator<String> it = set.iterator();
        String[] result = new String[3];
        int index = 0;
        while (it.hasNext()) {
            result[index++] = it.next();
        }
        assertThat(result, is(new String[] {"00", "11", "22"}));
    }

    @Test
    public void whenAddDuplicateElementThenElementNotAdd() {
        Iterator<String> it = set.iterator();
        set.add("11");
        String[] result = new String[3];
        int index = 0;
        while (it.hasNext()) {
            result[index++] = it.next();
        }
        assertThat(result, is(new String[] {"00", "11", "22"}));
    }
}