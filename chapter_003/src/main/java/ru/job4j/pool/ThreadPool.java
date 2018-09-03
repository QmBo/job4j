package ru.job4j.pool;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * ThreadPool
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 03.09.2018
 */
public class ThreadPool {
    /**
     * Threads capacity.
     */
    private final List<Thread> threads;
    /**
     * Tasks capacity.
     */
    private final Queue<Runnable> tasks;

    /**
     * Constructor.
     */
    public ThreadPool() {
        this.threads = new LinkedList<>();
        this.tasks = new LinkedBlockingQueue<>();
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            this.threads.add(new Thread(new TaskWorker()));
        }
        for (Thread t : this.threads) {
            t.start();
        }
    }

    /**
     * Add a task.
     * @param job
     */
    public void work(Runnable job) {
        synchronized (this.tasks) {
            this.tasks.offer(job);
            this.tasks.notify();
        }
    }

    /**
     * Terminated.
     */
    public void shutdown() {
        for (Thread t : this.threads) {
            t.interrupt();
        }
    }

    /**
     * TaskWorker class.
     */
    private final class TaskWorker implements Runnable {

        @Override
        public void run() {
            synchronized (tasks) {
                while (!Thread.currentThread().isInterrupted()) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            System.out.println(e.getMessage());
                            Thread.currentThread().interrupt();
                        }
                    }
                    tasks.poll().run();
                }
            }
        }
    }
}
