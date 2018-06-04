package ru.job4j.tracker;

import java.util.Date;

/**
 * StartUI class
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 03.06.2018
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Константа меню для отображения всех заявок.
     */
    private static final String ALL = "1";
    /**
     * Константа меню для изменения заявкию
     */
    private static final String REF = "2";
    /**
     * Константа меню для удаления завки.
     */
    private static final String DEL = "3";
    /**
     * Константа меню для поиска заявки по ID.
     */
    private static final String FID = "4";
    /**
     * Константа меню для поиска заявки по имени.
     */
    private static final String FNAME = "5";
    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
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
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (ALL.equals(answer)) {
                this.showAllItem();
            } else if (REF.equals(answer)) {
                this.changItem();
            } else if (DEL.equals(answer)) {
                this.deleteItem();
            } else if (FID.equals(answer)) {
                this.findForID();
            } else if (FNAME.equals(answer)) {
                this.findForName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Поиск заявки по имени.
     */
    private void findForName() {
        String answer = this.input.ask("Введите имя Заявки : ");
        if (this.tracker.findByName(answer).length > 0) {
            for (Item item: this.tracker.findByName(answer)) {
                this.showItem(item);
            }
        }
    }

    /**
     * Поиск заявки по ID.
     */
    private void findForID() {
        String answer = this.input.ask("Введите ID Заявки : ");
        if (this.tracker.findById(answer) != null) {
            this.showItem(this.tracker.findById(answer));
        }
    }

    /**
     * Изменение заявки.
     */
    private void changItem() {
        String answer = this.input.ask("Введите ID Заявки для её изменения : ");
        if (this.tracker.findById(answer) != null) {
            String name = this.input.ask("Введите новое имя заявки :");
            String desc = this.input.ask("Введите новое описание заявки :");
            Item item = new Item(name, desc);
            this.tracker.replace(answer, item);
            item.setId(answer);
            System.out.println("------------ Новая заявка с Id : " + item.getId() + " -----------");
        }
    }

    /**
     * Удаление заявки.
     */
    private void deleteItem() {
        String answer = this.input.ask("Введите ID Заявки для её удаления : ");
        this.tracker.delete(answer);
    }

    /**
     * отображение всех заявок.
     */
    private void showAllItem() {
        Item[] show = this.tracker.getAll();
        if (show.length == 0) {
            System.out.println("------------ Заявок нет ------------");
        } else {
            System.out.println("------------ Все заявки ------------");
            for (Item item : show) {
                this.showItem(item);
            }
        }
    }

    /**
     * Печать заявки.
     * @param item заявка.
     */
    private void showItem(Item item) {
        System.out.printf("%s %s %s ID: %s%s",
                item.getName(), item.getDescription(), new Date(item.getCreated()),
                item.getId(), System.lineSeparator());
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с Id : " + item.getId() + " -----------");
    }

    /**
     * Печать меню.
     */
    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Добавить навую заявку.");
        System.out.println("1. Показать все задачи.");
        System.out.println("2. Изменить заявку.");
        System.out.println("3. Удалить заявку.");
        System.out.println("4. Найти заявку по ID.");
        System.out.println("5. Найти заявку по имени.");
        System.out.println("6. Выход из программы.");
    }

    /**
     * Запускт программы.
     * @param args args.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}