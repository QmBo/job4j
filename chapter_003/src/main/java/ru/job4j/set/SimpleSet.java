package ru.job4j.set;

import ru.job4j.collections.SimpleArray;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private SimpleArray<E> set;

    public SimpleSet() {
        this.set = new SimpleArray<>(100);
    }

    public void add(E e) {
        if (this.set.indexOf(e) < 0) {
            this.set.add(e);
        }
    }


    @Override
    public Iterator<E> iterator() {
        return this.set.iterator();
    }
}
