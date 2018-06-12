package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ValidateInputTest {
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"invalid", "1"})
        );
        input.ask("Enter", new int[] {1});
        assertThat(
                this.mem.toString(),
                is(
                        String.format("Введите корректные данные.%n")
                )
        );
    }

    @Test
    public void whenInvalidRange() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[]{"3", "1"})
        );
        input.ask("Enter", new int[]{1});
        assertThat(
                this.mem.toString(),
                is(
                        new StringBuilder()
                                .append("Такого пунктв меню нет!")
                                .append(System.lineSeparator())
                                .append("Введите корректные данные.")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenValidInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[]{"1"})
        );
        input.ask("Enter", new int[]{1});
        assertThat(
                this.mem.toString(),
                is(
                        String.format("")
                )
        );
    }
}
