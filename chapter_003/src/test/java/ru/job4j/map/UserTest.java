package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void whenTowDuplicateUserAddToMap() {
        Map<User, String> map = new HashMap<>();
        Calendar birthday = new GregorianCalendar(1987, 10, 24);
        User first = new User("Tim", 2, birthday);
        User second = new User("Tim", 2, birthday);
        map.put(first, "first");
        map.put(second, "second");
        System.out.println(map);
        System.out.println(first.hashCode());
        System.out.println(second.hashCode());
        System.out.println(first.equals(second));
        System.out.println(map.size());
    }
}