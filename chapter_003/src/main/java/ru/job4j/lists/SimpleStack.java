package ru.job4j.lists;

public class SimpleStack<T> {
    private SimpleLinkedList<T> stack = new SimpleLinkedList<>();

    public T poll() {
        return this.stack.deleteLast();
    }

    public void push(T value) {
        this.stack.add(value);
    }
}