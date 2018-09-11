package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class UserConvertTest {
    @Test
    public void whenListOfNamesThenListOfUsers() {
        UserConvert userConvert = new UserConvert();
        List<String> names = Arrays.asList("Tom", "Tim", "Bob");
        List<UserConvert.User> users = userConvert.convert(names, UserConvert.User::new);
        assertThat(
                users, is(
                        Arrays.asList(
                                new UserConvert.User("Tom"),
                                new UserConvert.User("Tim"),
                                new UserConvert.User("Bob")
                        )
                )
        );
    }
}