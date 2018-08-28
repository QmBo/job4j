package ru.job4j.thread;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {

    private SimpleBlockingQueue<Integer> bQ;
    private Thread producer;
    private Thread consumer;
    List<Integer> list;

    @Before
    public void setUp() {
        bQ = new SimpleBlockingQueue<>();
        list = new ArrayList<>();
        producer = new Thread() {
            @Override
            public void run() {
                try {
                    bQ.offer(10);
                    System.out.println("offer");
                    sleep(10);
                    bQ.offer(20);
                    System.out.println("offer");
                    sleep(10);
                    bQ.offer(30);
                    System.out.println("offer");
                    sleep(10);
                    bQ.offer(40);
                    System.out.println("offer");
                    sleep(10);
                    bQ.offer(50);
                    System.out.println("offer");
                    sleep(10);
                    bQ.offer(60);
                    System.out.println("offer");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        consumer = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 6; i++) {
                    try {
                        list.add(bQ.poll());
                        System.out.println("pool");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    @Test
    public void whenTwoTreadsThenNoExceptions() throws InterruptedException {
        consumer.start();
        producer.start();
        producer.join();
        consumer.join();
        assertThat(list, is(Arrays.asList(10, 20, 30, 40, 50, 60)));
    }
}