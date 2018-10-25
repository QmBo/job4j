package ru.job4j.thread;

public class Switcher {

    private String string = "";

    public void add(int number) {
        this.string = this.string + number;
    }

    public String getString() {
        return this.string;
    }
}
