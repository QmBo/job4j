package ru.job4j.search;

import java.util.LinkedList;
/**
 * PriorityQueue
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 28.06.2018
 */
public class PriorityQueue {
    /**
     * Хранит задачи в порядку значемости.
     */
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * @param task задача.
     */
    public void put(Task task) {
        int size = this.tasks.size();
        if (size == 0) {
            this.tasks.add(size, task);
        } else {
            int priority = task.getPriority();
            boolean complete = false;
            for (int index = 0; index != size; index++) {
                if (priority < this.tasks.get(index).getPriority()) {
                    this.tasks.add(index, task);
                    complete = true;
                    break;
                }
            }
            if (!complete) {
                this.tasks.add(size, task);
            }
        }
    }

    /**
     * Возвращает самую важную задачу.
     * @return задача.
     */
    public Task take() {
        return this.tasks.poll();
    }
}