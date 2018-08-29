package ru.job4j.thread;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;
/**
 * SimpleBlockingQueue
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 28.08.2018
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    /**
     * Capacity.
     */
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    /**
     * Add element to capacity and wake thread.
     * @param value value.
     */
    public void offer(T value) {
        synchronized (this.queue) {
            this.queue.add(value);
            this.queue.notify();
        }
    }

    /**
     * Check available element and wait fo notification.
     * @return value.
     * @throws InterruptedException
     */
    public T poll() throws InterruptedException {
        T result;
        synchronized (this.queue) {
            while (this.queue.isEmpty()) {
                System.out.println("wait");
                this.queue.wait();
            }
            result = this.queue.poll();
        }
        return result;
    }
}