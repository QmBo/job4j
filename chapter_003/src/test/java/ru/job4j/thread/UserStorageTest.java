package ru.job4j.thread;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    public UserStorage users = new UserStorage();

    public class UserThreads extends Thread {
        private final int from;
        private final int too;

        public UserThreads(final int from, final int too) {
            this.from = from;
            this.too = too;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                users.transfer(from, too, 10);
            }
        }
    }
    @Before
    public void setUp() {
        users.add(new User(1, 1000));
        users.add(new User(4, 500));
    }

    @Test
    public void whenDeleteThenTrue() {
        assertThat(users.add(new User(3, 300)), is(true));
        assertThat(users.transfer(1, 3, 10), is(true));
        assertThat(users.delete(new User(3, 310)), is(true));
        assertThat(users.transfer(1, 3, 10), is(false));
    }

    @Test
    public void whenUpdateThenUpdated() {
        assertThat(users.update(new User(1, 100)), is(true));
        assertThat(users.delete(new User(1, 100)), is(true));
    }

    @Test
    public void whenTransferInThreadsThenAmountAsStart() throws InterruptedException {
        Thread threadA = new Thread(new UserThreads(1, 4));
        Thread threadB = new Thread(new UserThreads(4, 1));
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        assertThat(users.delete(new User(1, 1000)), is(true));
        assertThat(users.delete(new User(4, 500)), is(true));
    }
}