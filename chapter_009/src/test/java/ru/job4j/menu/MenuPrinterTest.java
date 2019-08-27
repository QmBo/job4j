package ru.job4j.menu;

import com.google.common.base.Joiner;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MenuPrinterTest {
    private static final String LS = System.lineSeparator();
    private String menu;

    @Before
    public void setUp() {
        this.menu = Joiner.on(LS).join(
                "1 Chang", "1.1 Add", "1.1.1 AddFirst",
                "1.1.2 AddLast", "1.2 Remove", "1.2.1 RemoveFirst",
                "1.2.2 RemoveLast", ""
        );
    }

    @Test
    public void whenPrintThenPrintMenu() {
        MenuPrinter menuPrinter = new MenuPrinter();
        menuPrinter.initMenu();
        assertThat(menuPrinter.showMenu(), is(this.menu));
    }

    @Test
    public void whenPrintWorkThenPrintName() {
        MenuPrinter menuPrinter = new MenuPrinter();
        menuPrinter.initMenu();
        assertThat(menuPrinter.action(3), is("AddLast"));
    }
}
