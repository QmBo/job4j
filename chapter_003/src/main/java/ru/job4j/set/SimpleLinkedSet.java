package ru.job4j.set;

import ru.job4j.lists.SimpleLinkedList;
import java.util.Iterator;
/**
 * SimpleLinkedSet
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 30.07.2018
 */
public class SimpleLinkedSet<E> implements Iterable<E> {
    /**
     * Capacity.
     */
    private SimpleLinkedList<E> set = new SimpleLinkedList<>();

    /**
     * Add element to capacity if not duplicate.
     * @param e element.
     */
    public void add(E e) {
        Iterator<E> it = this.set.iterator();
        boolean duplicate = false;
        while (it.hasNext()) {
            E value = it.next();
            if (value.equals(e)) {
                duplicate = true;
                break;
            }
        }
        if (!duplicate) {
            this.set.add(e);
        }
    }

    /**
     * Iterator getter.
     * @return iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return this.set.iterator();
    }
}
