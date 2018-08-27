package ru.job4j.thread;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.lists.DynamicArrayList;
import ru.job4j.lists.SimpleLinkedList;
/**
 * ThreadSafeList
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 27.08.2018
 */
@ThreadSafe
public class ThreadSafeList<E> {
    /**
     * List capacity.
     */
    @GuardedBy("this")
    private SimpleLinkedList<E> simpleList;
    /**
     * Array capacity.
     */
    @GuardedBy("this")
    private DynamicArrayList<E> arrayList;

    /**
     * Constructor.
     */
    public ThreadSafeList() {
        this.simpleList = new SimpleLinkedList<>();
        this.arrayList = new DynamicArrayList<>(100);
    }

    /**
     * Add element to list.
     * @param e element.
     */
    public synchronized void addToList(E e) {
        this.simpleList.add(e);
    }

    /**
     * Add element to array.
     * @param e element.
     */
    public synchronized void addToArray(E e) {
        this.arrayList.add(e);
    }

    /**
     * Get element from list.
     * @param index index.
     * @return element.
     */
    public synchronized E getFromList(int index) {
        return this.simpleList.get(index);
    }

    /**
     * Get element from array.
     * @param index index.
     * @return element.
     */
    public synchronized E getFromArray(int index) {
        return this.arrayList.get(index);
    }
}
