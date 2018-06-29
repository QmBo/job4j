package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {
    /**
     * Получаем стандаретный вывод на консоль.
     */
    PrintStream stdout = System.out;
    /**
     * Буфер для результата.
     */
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    /**
     * Стандартный вывод меню.
     */
    String menu = new StringBuilder()
            .append("Меню.")
            .append(System.lineSeparator())
            .append("0. Добавить навую заявку.")
            .append(System.lineSeparator())
            .append("1. Показать все заяки.")
            .append(System.lineSeparator())
            .append("2. Изменить заявку.")
            .append(System.lineSeparator())
            .append("3. Удалить заявку.")
            .append(System.lineSeparator())
            .append("4. Найти заявку по ID.")
            .append(System.lineSeparator())
            .append("5. Найти заявку по имени.")
            .append(System.lineSeparator())
            .append("6. Выход из программы.")
            .append(System.lineSeparator())
            .toString();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.getAll().get(0).getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item());
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    @Test
    public void whenDeleteItemThenTrackerHasNotItem() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        tracker.add(item);
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertNull(tracker.findById(item.getId()));
    }

    @Test
    public void whenUserPrintAllItemsThenAllItemsPrinted() {
        Tracker tracker = new Tracker();
        Item firstItem = tracker.add(new Item("1", "desc test"));
        Item secondItem = tracker.add(new Item("2", "desc test2"));
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(this.menu)
                                .append("------------ Все заявки ------------")
                                .append(System.lineSeparator())
                                .append(firstItem.getName()).append(" ")
                                .append(firstItem.getDescription()).append(" ")
                                .append(new Date(firstItem.getCreated())).append(" ")
                                .append("ID: ")
                                .append(firstItem.getId())
                                .append(System.lineSeparator())
                                .append(secondItem.getName()).append(" ")
                                .append(secondItem.getDescription()).append(" ")
                                .append(new Date(secondItem.getCreated())).append(" ")
                                .append("ID: ")
                                .append(secondItem.getId())
                                .append(System.lineSeparator())
                                .append(this.menu)
                                .toString()
                )
        );
    }

    @Test
    public void whenUserPrintItemsByNameThenItemsPrinted() {
        Tracker tracker = new Tracker();
        Item firstItem = tracker.add(new Item("test", "desc test"));
        Item secondItem = tracker.add(new Item("test", "desc test2"));
        tracker.add(new Item("2", "desc test2"));
        Input input = new StubInput(new String[]{"5", "test", "6"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(this.menu)
                                .append(firstItem.getName()).append(" ")
                                .append(firstItem.getDescription()).append(" ")
                                .append(new Date(firstItem.getCreated())).append(" ")
                                .append("ID: ")
                                .append(firstItem.getId())
                                .append(System.lineSeparator())
                                .append(secondItem.getName()).append(" ")
                                .append(secondItem.getDescription()).append(" ")
                                .append(new Date(secondItem.getCreated())).append(" ")
                                .append("ID: ")
                                .append(secondItem.getId())
                                .append(System.lineSeparator())
                                .append(this.menu)
                                .toString()
                )
        );
    }

    @Test
    public void whenUserPrintItemsByIDThenItemsPrinted() {
        Tracker tracker = new Tracker();
        Item firstItem = tracker.add(new Item("test", "desc test"));
        tracker.add(new Item("2", "desc test2"));
        Input input = new StubInput(new String[]{"4", firstItem.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(this.menu)
                                .append(firstItem.getName()).append(" ")
                                .append(firstItem.getDescription()).append(" ")
                                .append(new Date(firstItem.getCreated())).append(" ")
                                .append("ID: ")
                                .append(firstItem.getId())
                                .append(System.lineSeparator())
                                .append(this.menu)
                                .toString()
                )
        );
    }

    @Test
    public void whenItemNotFoundThenPrintItemNotFound() {
        Tracker tracker = new Tracker();
        Item firstItem = tracker.add(new Item("test", "desc test"));
        String wrongId = firstItem.getId() + 1;
        Input input = new StubInput(new String[]{"2", wrongId, "3", wrongId, "4", wrongId, "5", "Test", "6"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(this.menu)
                                .append("Item not found.")
                                .append(System.lineSeparator())
                                .append(this.menu)
                                .append("Item not found.")
                                .append(System.lineSeparator())
                                .append(this.menu)
                                .append("Item not found.")
                                .append(System.lineSeparator())
                                .append(this.menu)
                                .append("Item not found.")
                                .append(System.lineSeparator())
                                .append(this.menu)
                                .toString()
                )
        );
    }
}