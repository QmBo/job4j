package ru.job4j.thread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CashTest {

    @Test(expected = OptimisticException.class)
    public void whenUpdateThenUpdate() {
        Cash cash = new Cash();
        cash.add(new Base(1, 0));
        cash.update(new Base(1, 1));
    }

    @Test
    public void whenThrowException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread = new Thread(
                () -> {
                    try {
                        throw new RuntimeException("Throw Exception in Thread");
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread.start();
        thread.join();
        assertThat(ex.get().getMessage(), is("Throw Exception in Thread"));
    }

    @SuppressWarnings("Duplicates")
    @Test
    public void whenTwoThreadThenNoException() throws InterruptedException {
        Cash cash = new Cash();
        AtomicReference<Exception> ex = new AtomicReference<>();
        cash.add(new Base(1, 0));
        Thread threadA = new Thread(
                () -> {
                    try {
                        Base model = cash.getModel(1);
                        cash.update(new Base(model.id, model.version));
                    } catch (OptimisticException e) {
                        ex.set(e);
                    }
                }
        );
        Thread threadB = new Thread(
                () -> {
                    try {
                        Base model = cash.getModel(1);
                        cash.update(new Base(model.id, model.version));
                    } catch (OptimisticException e) {
                        ex.set(e);
                    }
                }
        );
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        assertThat(ex.get().getMessage(), is("Optimistic Exception"));
    }
}