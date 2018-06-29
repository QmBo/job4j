package ru.job4j.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConvertListTest {
    @Test
    public void when2ArraysThen10() {
        ConvertList converter = new ConvertList();
        List<Integer> expect = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> result = converter.convert(Arrays.asList(
                new int[]{1, 2, 3, 4},
                new int[]{5, 6, 7, 8, 9, 10}
        ));
        assertThat(result, is(expect));
    }
}