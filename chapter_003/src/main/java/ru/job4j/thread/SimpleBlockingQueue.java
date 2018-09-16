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
    @GuardedBy("this.queue")
    private final Queue<T> queue = new LinkedList<>();
    /**
     * Queue limit.
     */
    private final static int QUEUE_LIMIT = 3;

    /**
     * Add element to capacity and wake thread.
     * @param value value.
     */
    public void offer(T value) {
        synchronized (this.queue) {
            while (this.queue.size() == QUEUE_LIMIT) {
                System.out.println("offer wait");
                try {
                    this.queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.queue.add(value);
            this.queue.notify();
        }
    }

    /**
     * Check available element and wait fo notification.
     * @return value.
     * @throws InterruptedException InterruptedException.
     */
    public T poll() throws InterruptedException {
        synchronized (this.queue) {
            while (this.queue.isEmpty()) {
                System.out.println("poll wait");
                this.queue.wait();
            }
            this.queue.notify();
            return this.queue.poll();
        }
    }

    /**
     * Is Queue empty.
     * @return empty.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public synchronized boolean isEmpty() {
        boolean result;
        synchronized (this.queue) {
            result = this.queue.isEmpty();
        }
        return result;
    }
}