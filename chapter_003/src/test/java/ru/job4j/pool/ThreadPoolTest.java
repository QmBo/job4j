package ru.job4j.pool;

import org.junit.Test;

public class ThreadPoolTest {

    @Test
    public void whenAddTaskThenWork() {
        ThreadPool pool = new ThreadPool();
        Thread thread = new Thread(() -> System.out.printf("%s\n", Thread.currentThread().getName()));
        for (int i = 0; i < 5; i++) {
            pool.work(thread);
        }
    }
}