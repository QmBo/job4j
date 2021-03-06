package ru.job4j.lists;

public class SimpleQueue<T> {
    private SimpleLinkedList<T> list = new SimpleLinkedList<>(true);

    public T poll() {
        return this.list.deleteLast();
    }

    public void push(T value) {
        list.add(value);
    }
}
