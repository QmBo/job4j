package ru.job4j.thread;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Count
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 24.08.2018
 */
@ThreadSafe
public class Count {
    /**
     * Value.
     */
    @GuardedBy("this")
    private int value;

    /**
     * Increment method.
     */
    public synchronized void increment() {
        this.value++;
    }

    /**
     * Value getter.
     * @return value.
     */
    public synchronized int get() {
        return this.value;
    }
}