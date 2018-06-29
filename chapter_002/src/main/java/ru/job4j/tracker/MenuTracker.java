package ru.job4j.tracker;

import java.util.ArrayList;
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
     * Action position.
     */
    private int position = 0;
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
        this.actions[this.position] = this.new AddAction(this.position, "Добавить навую заявку.");
        this.actions[++this.position] = this.new ShowAllAction(this.position, "Показать все заяки.");
        this.actions[++this.position] = new EditAction(this.position, "Изменить заявку.");
        this.actions[++this.position] = new MenuTracker.DeleteAction(this.position, "Удалить заявку.");
        this.actions[++this.position] = this.new FindIdAction(this.position, "Найти заявку по ID.");
        this.actions[++this.position] = this.new FindNameAction(this.position, "Найти заявку по имени.");
        this.actions[++this.position] = this.new ExitAction(this.position, "Выход из программы.");
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
     * @param item item.
     */
    private void showItem(Item item) {
        System.out.printf("%s %s %s ID: %s%s",
                item.getName(), item.getDescription(), new Date(item.getCreated()),
                item.getId(), System.lineSeparator());
    }

    /**
     * Add Item Action class.
     */
    private class AddAction extends BaseAction {
        protected AddAction(final int key, final String name) {
            super(key, name);
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
    }

    /**
     * Show All Item Action class.
     */
    private class ShowAllAction extends BaseAction {
        protected ShowAllAction(final int key, final String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            ArrayList<Item> show = new ArrayList<>(tracker.getAll());
            if (show.isEmpty()) {
                System.out.println("------------ Заявок нет ------------");
            } else {
                System.out.println("------------ Все заявки ------------");
                for (Item item : show) {
                    showItem(item);
                }
            }
        }
    }

    /**
     * Find Item by ID Action class.
     */
    private class FindIdAction extends BaseAction {
        protected FindIdAction(final int key, final String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String answer = input.ask("Введите ID Заявки : ");
            if (tracker.findById(answer) != null) {
                showItem(tracker.findById(answer));
            }
        }
    }

    /**
     * Find Item by Name Action class.
     */
    private class FindNameAction extends BaseAction {
        protected FindNameAction(final int key, final String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String answer = input.ask("Введите имя Заявки : ");
            if (!tracker.findByName(answer).isEmpty()) {
                for (Item item: tracker.findByName(answer)) {
                    showItem(item);
                }
            }
        }
    }

    /**
     * Exit Action class.
     */
    private class ExitAction extends BaseAction {
        protected ExitAction(final int key, final String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            tracker.timeToExit();
        }
    }

    /**
     * Delete Item Action class.
     */
    private static class DeleteAction extends BaseAction {
        protected DeleteAction(final int key, final String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String answer = input.ask("Введите ID Заявки для её удаления : ");
            tracker.delete(answer);
        }
    }
}

/**
 * Edit Item Action class.
 */
class EditAction extends BaseAction {
    protected EditAction(final int key, final String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String answer = input.ask("Введите ID Заявки для её изменения : ");
        if (tracker.findById(answer) != null) {
            String name = input.ask("Введите новое имя заявки :");
            String desc = input.ask("Введите новое описание заявки :");
            Item item = new Item(name, desc);
            tracker.replace(answer, item);
            System.out.println("------------ Заявка с Id : " + item.getId() + " изменена. -----------");
        }
    }
}