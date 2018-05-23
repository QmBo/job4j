package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
       ArrayDuplicate duplicate = new ArrayDuplicate();
       String[] result = duplicate.remove(new String[] {"Привет", "Мир", "Привет", "Супер", "Мир"});
       assertThat(result, is(new String[]{"Привет", "Мир", "Супер"}));
    }

    @Test
    public void whenArrayWithoutDuplicateThenArrayWithoutDuplicate() {
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] result = duplicate.remove(new String[] {"Привет", "Мир", "Кот", "Супер", "Час"});
        assertThat(result, is(new String[]{"Привет", "Мир", "Кот", "Супер", "Час"}));
    }

    @Test
    public void whenArrayHavManiDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] result = duplicate.remove(new String[] {"Привет", "Мир", "Мир", "Супер", "Мир", "Супер", "Час", "Супер", "Супер", "Мир"});
        assertThat(result, is(new String[]{"Привет", "Мир", "Супер", "Час"}));
    }
}