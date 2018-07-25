package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    RoleStore<Role> roleStore;
    Role first;
    @Before
    public void setUp() {
        roleStore = new RoleStore<>(10);
        first = new Role("11223");
        roleStore.add(first);
        roleStore.add(new Role("22331"));
        roleStore.add(new Role("test"));
    }
    @Test
    public void whenAddRoleThenRoleInStore() {
        assertThat(roleStore.findById("11223"), is(first));
    }

    @Test
    public void whenReplaceRoleThenRoleReplaced() {
        Role replace = new Role("replace");
        assertThat(roleStore.replace("11223", replace), is(true));
        assertThat(roleStore.findById("replace"), is(replace));
    }

    @Test
    public void whenRoleDeleteThenHasntRole() {
        assertThat(roleStore.delete("11223"), is(true));
        assertNull(roleStore.findById("11223"));
    }

    @Test
    public void whenRoleNotFound() {
        assertThat(roleStore.delete("test1"), is(false));
        assertThat(roleStore.replace("test1", first), is(false));
        assertNull(roleStore.findById("test1"));
    }
}