package ru.job4j.tracker;

/**
 * StartUI class
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 03.06.2018
 */
public class StartUI {
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        while (!this.tracker.isExit()) {
            menu.show();
            int key = Integer.valueOf(this.input.ask(""));
            menu.select(key);
        }
    }

    /**
     * Запускт программы.
     * @param args args.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}