package ru.job4j.tracker;

import java.util.Date;

/**
 * MenuTracker
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 05.06.2018
 */
public class MenuTracker {
    /**
     * Tracker.
     */
    private Tracker tracker;
    /**
     * Input.
     */
    private Input input;
    /**
     * Actions.
     */
    private UserAction[] actions = new UserAction[7];

    /**
     * Constructor.
     * @param input input.
     * @param tracker tracker.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
        this.fillAction();
    }

    /**
     * Actions configurator.
     */
    private void fillAction() {
        this.actions[0] = this.new AddAction();
        this.actions[1] = this.new ShowAllAction();
        this.actions[2] = new EditAction();
        this.actions[3] = new MenuTracker.DeleteAction();
        this.actions[4] = this.new FindIdAction();
        this.actions[5] = this.new FindNameAction();
        this.actions[6] = this.new ExitAction();
    }

    public int[] getRange() {
        int[] result = new int[this.actions.length];
        int index = 0;
        for (UserAction action : actions) {
            result[index++] = action.key();
        }
        return result;
    }

    /**
     * Show Menu.
     */
    public void show() {
        System.out.println("Меню.");
        for (UserAction action: this.actions) {
            if (action == null) {
                break;
            }
            System.out.println(action.info());
        }
    }

    /**
     * User select.
     * @param key select.
     */
    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * Print Item.
     * @param item
     */
    private void showItem(Item item) {
        System.out.printf("%s %s %s ID: %s%s",
                item.getName(), item.getDescription(), new Date(item.getCreated()),
                item.getId(), System.lineSeparator());
    }

    /**
     * Add Item Action class.
     */
    private class AddAction implements UserAction {
        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ Новая заявка с Id : " + item.getId() + " -----------");
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Добавить навую заявку.");
        }
    }

    /**
     * Show All Item Action class.
     */
    private class ShowAllAction implements UserAction {
        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            Item[] show = tracker.getAll();
            if (show.length == 0) {
                System.out.println("------------ Заявок нет ------------");
            } else {
                System.out.println("------------ Все заявки ------------");
                for (Item item : show) {
                    showItem(item);
                }
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Показать все заяки.");
        }
    }

    /**
     * Find Item by ID Action class.
     */
    private class FindIdAction implements UserAction {
        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String answer = input.ask("Введите ID Заявки : ");
            if (tracker.findById(answer) != null) {
                showItem(tracker.findById(answer));
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Найти заявку по ID.");
        }
    }

    /**
     * Find Item by Name Action class.
     */
    private class FindNameAction implements UserAction {
        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String answer = input.ask("Введите имя Заявки : ");
            if (tracker.findByName(answer).length > 0) {
                for (Item item: tracker.findByName(answer)) {
                    showItem(item);
                }
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Найти заявку по имени.");
        }
    }

    /**
     * Exit Action class.
     */
    private class ExitAction implements UserAction {
        @Override
        public int key() {
            return 6;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            tracker.timeToExit();
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Выход из программы.");
        }
    }

    /**
     * Delete Item Action class.
     */
    private static class DeleteAction implements UserAction {
        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String answer = input.ask("Введите ID Заявки для её удаления : ");
            tracker.delete(answer);
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Удалить заявку.");
        }
    }
}

/**
 * Edit Item Action class.
 */
class EditAction implements UserAction {
    @Override
    public int key() {
        return 2;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String answer = input.ask("Введите ID Заявки для её изменения : ");
        if (tracker.findById(answer) != null) {
            String name = input.ask("Введите новое имя заявки :");
            String desc = input.ask("Введите новое описание заявки :");
            Item item = new Item(name, desc);
            tracker.replace(answer, item);
            System.out.println("------------ Новая заявка с Id : " + item.getId() + " -----------");
        }
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Изменить заявку.");
    }
}