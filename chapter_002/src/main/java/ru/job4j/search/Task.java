package ru.job4j.search;
/**
 * Task
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 28.06.2018
 */
public class Task {
    private String desc;
    private int priority;

    public Task(String desc, int priority) {
        this.desc = desc;
        this.priority = priority;
    }

    public String getDesc() {
        return desc;
    }

    public int getPriority() {
        return priority;
    }
}
