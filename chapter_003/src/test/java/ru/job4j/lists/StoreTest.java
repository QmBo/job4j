package ru.job4j.lists;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * StoreTest
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 15.08.2018
 */
public class StoreTest {
    private List<Store.User> prev = new ArrayList<>();
    private List<Store.User> cur = new ArrayList<>();

    @Test
    public void whenHasNewUserThenNew2() {
        Store store = new Store();
        prev.add(new Store.User(12, "12"));
        cur.add(new Store.User(11, "new"));
        cur.add(new Store.User(12, "12"));
        cur.add(new Store.User(112, "new"));
        assertThat(store.diff(prev, cur), is(new Store.Info(2, 0, 0)));
    }

    @Test
    public void whenHasEditUserThenEdit2() {
        Store store = new Store();
        prev.add(new Store.User(11, "11"));
        prev.add(new Store.User(12, "12"));
        prev.add(new Store.User(13, "13"));
        cur.add(new Store.User(11, "edit"));
        cur.add(new Store.User(12, "12"));
        cur.add(new Store.User(13, "edit"));
        assertThat(store.diff(prev, cur), is(new Store.Info(0, 0, 2)));
    }

    @Test
    public void whenHasDeletedUserThenDeleted2() {
        Store store = new Store();
        prev.add(new Store.User(11, "11"));
        prev.add(new Store.User(12, "12"));
        prev.add(new Store.User(13, "13"));
        prev.add(new Store.User(15, "deleted"));
        prev.add(new Store.User(16, "deleted"));
        cur.add(new Store.User(11, "11"));
        cur.add(new Store.User(12, "12"));
        cur.add(new Store.User(13, "13"));
        assertThat(store.diff(prev, cur), is(new Store.Info(0, 2, 0)));
    }

    @Test
    public void whenHasNotPrevUserThenNew3() {
        Store store = new Store();
        cur.add(new Store.User(11, "new"));
        cur.add(new Store.User(12, "new"));
        cur.add(new Store.User(13, "new"));
        assertThat(store.diff(prev, cur), is(new Store.Info(3, 0, 0)));
    }

    @Test
    public void whenHasDeletedAllUserThenDeleted5() {
        Store store = new Store();
        prev.add(new Store.User(11, "deleted"));
        prev.add(new Store.User(12, "deleted"));
        prev.add(new Store.User(13, "deleted"));
        prev.add(new Store.User(15, "deleted"));
        prev.add(new Store.User(16, "deleted"));
        assertThat(store.diff(prev, cur), is(new Store.Info(0, 5, 0)));
    }

    @Test
    public void whenEmptyListsUserThen000() {
        Store store = new Store();
        assertThat(store.diff(prev, cur), is(new Store.Info(0, 0, 0)));
    }
}