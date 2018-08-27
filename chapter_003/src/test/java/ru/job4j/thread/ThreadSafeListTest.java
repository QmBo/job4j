package ru.job4j.thread;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ThreadSafeListTest {

    private ThreadSafeList<String> list = new ThreadSafeList<>();

    public class SafeListThreads extends Thread {
        private final int[] fromA;
        private final int[] fromB;

        public SafeListThreads(final int[] fromA, final int[] fromB) {
            this.fromA = fromA;
            this.fromB = fromB;
        }

        @Override
        public void run() {
            for (int index: fromA) {
                list.addToArray(list.getFromList(index));
            }
            for (int index : fromB) {
                list.addToList(list.getFromArray(index));
            }
        }
    }

    @Before
    public void setUp() {
        list.addToList("1");
        list.addToList("1");
        list.addToList("1");
        list.addToList("1");
        list.addToList("1");
        list.addToArray("0");
        list.addToArray("0");
        list.addToArray("0");
        list.addToArray("0");
        list.addToArray("0");
    }

    @Test
    public void whenTransferAllThenAllTransferred() throws InterruptedException {
        Thread threadA = new Thread(new SafeListThreads(new int[] {0, 1, 2, 3, 4}, new int[0]));
        Thread threadB = new Thread(new SafeListThreads(new int[0], new int[] {0, 1, 2, 3, 4}));
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        StringBuilder first =  new StringBuilder();
        StringBuilder second =  new StringBuilder();
        for (int i = 0; i < 10; i++) {
            first.append(list.getFromList(i));
            second.append(list.getFromArray(i));
        }
        assertThat(first.toString(), is("1111100000"));
        assertThat(second.toString(), is("0000011111"));
    }
}