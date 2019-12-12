package ru.job4j.menu;

import com.google.common.base.Joiner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MenuTest {
    /**
     * Получаем стандаретный вывод на консоль.
     */
    PrintStream stdout = System.out;
    /**
     * Буфер для результата.
     */
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    private static final String LS = System.lineSeparator();
    private String menu;
    private Item chang;
    private int size;


    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(out));
    }
    @After
    public void backOutput() {
        System.setOut(stdout);
        System.out.println("execute after method");
    }

    @Before
    public void setUp() {
        Item add = new Item("Add");
        add.addItem(new Item.AddFirst("AddFirst"));
        add.addItem(new Item.AddLast("AddLast"));
        Item remove = new Item("Remove");
        remove.addItem(new Item.RemoveFirst("RemoveFirst"));
        remove.addItem(new Item.RemoveLast("RemoveLast"));
        this.chang = new Item("Chang");
        this.chang.addItem(add);
        this.chang.addItem(remove);
        this.size = 7;
        this.menu = Joiner.on(LS).join(
                "1 Chang", "1.1 Add", "1.1.1 AddFirst",
                "1.1.2 AddLast", "1.2 Remove", "1.2.1 RemoveFirst",
                "1.2.2 RemoveLast", ""
        );
    }

    @Test
    public void whenInitMenuThenMenuSize() {
        Menu menu = new Menu(this.chang);
        menu.initMenu();
        assertThat(menu.initMenu(), is(this.size));
    }

    @Test
    public void whenPrintWorkThenPrintName() {
        Menu menu = new Menu(chang);
        menu.initMenu();
        assertThat(menu.action(3), is("AddLast"));
    }

    @Test
    public void whenListThenPrintMenu() {
        Item add = new Item("Add");
        add.addItem(new Item.AddFirst("AddFirst"));
        add.addItem(new Item.AddLast("AddLast"));
        Item remove = new Item("Remove");
        remove.addItem(new Item.RemoveFirst("RemoveFirst"));
        remove.addItem(new Item.RemoveLast("RemoveLast"));
        Item chang = new Item("Chang");
        chang.addItem(add);
        chang.addItem(remove);
        Menu menu = new Menu(chang);
        menu.printMenu();
        assertThat(new String(out.toByteArray()), is(this.menu));
    }
}
