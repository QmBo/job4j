package ru.job4j.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserConvertTest {

    @Test
    public void when3UsersThenMap3Users() {
        UserConvert converter = new UserConvert();
        HashMap<Integer, User> expect = new HashMap<>();
        User first = new User(5, "Tim", "Moscow");
        User second = new User(235, "Tom", "Perm");
        User third = new User(-21, "Tod", "Orel");
        expect.put(5, first);
        expect.put(235, second);
        expect.put(-21, third);
        assertThat(converter.process(Arrays.asList(first, second, third)), is(expect));
    }
}