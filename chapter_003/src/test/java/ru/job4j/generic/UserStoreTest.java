package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {
    UserStore<User> userStore;
    User first;
    @Before
    public void setUp() {
        userStore = new UserStore<>(10);
        first = new User("11223");
        userStore.add(first);
        userStore.add(new User("22331"));
        userStore.add(new User("test"));
    }
    @Test
    public void whenAddUserThenUserInStore() {
        assertThat(userStore.findById("11223"), is(first));
    }

    @Test
    public void whenReplaceUserThenUserReplaced() {
        User replace = new User("replace");
        assertThat(userStore.replace("11223", replace), is(true));
        assertThat(userStore.findById("replace"), is(replace));
    }

    @Test
    public void whenUserDeleteThenHasntUser() {
        assertThat(userStore.delete("11223"), is(true));
        assertNull(userStore.findById("11223"));
    }

    @Test
    public void whenUserNotFound() {
        assertThat(userStore.delete("test1"), is(false));
        assertThat(userStore.replace("test1", first), is(false));
        assertNull(userStore.findById("test1"));
    }
}