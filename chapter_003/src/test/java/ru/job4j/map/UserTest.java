package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void whenNewUserThenNewUser() {
        Calendar birthday = new GregorianCalendar(1987, 10, 24);
        User newUser = new User("Tim", 2, birthday);
        assertThat(newUser.getName(), is("Tim"));
        assertThat(newUser.getChildren(), is(2));
        assertThat(newUser.getBirthday(), is(birthday));
    }
}