package ru.job4j.thread;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SwitcherTest {

    @Test
    public void whenTogetherAddThen1122() {
        Switcher switcher = new Switcher();
        Thread first = new Thread(
                () -> {
                    for (int i = 0; i < 3; i++) {
                        while (switcher.getString().endsWith("1")) {
                            Thread.yield();
                        }
                        synchronized (switcher) {
                            do {
                                switcher.add(1);
                            } while (switcher.getString().length() % 10 != 0);
                        }
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    for (int i = 0; i < 3; i++) {
                        while (switcher.getString().endsWith("2") || switcher.getString().length() == 0) {
                            Thread.yield();
                        }
                        synchronized (switcher) {
                            do {
                                switcher.add(2);
                            } while (switcher.getString().length() % 10 != 0);
                        }
                    }
                }
        );
        first.start();
        second.start();
        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(switcher.getString(),
                is("111111111122222222221111111111222222222211111111112222222222"));
    }
}