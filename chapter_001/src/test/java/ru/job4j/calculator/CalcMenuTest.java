package ru.job4j.calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.Input;
import ru.job4j.tracker.StubInput;
import ru.job4j.tracker.ValidateInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalcMenuTest {
    private static final String LS = System.lineSeparator();
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
            .append("0. Выход.")
            .append(LS)
            .append("1. Сложение.")
            .append(LS)
            .append("2. Вычитание.")
            .append(LS)
            .append("3. Умножение.")
            .append(LS)
            .append("4. Деление.")
            .append(LS)
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
    public void whenFirstAskMThenAnswerWrong() {
        Input input = new StubInput(new String[]{"1", "m", "2", "3", "0"});
        new InteractCalc(
                new ValidateInput(input), new Calculator()
        ).action();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(this.menu)
                                .append("2.0 + 3.0 = 5.0")
                                .append(LS)
                                .append(this.menu)
                                .toString()
                )
        );
}

    @Test
    public void whenNotFirstAskMThenAnswer45() {
        Input input = new StubInput(new String[]{"1", "40", "5", "1", "m", "5", "0"});
        new InteractCalc(
                new ValidateInput(input), new Calculator()
        ).action();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(this.menu)
                                .append("40.0 + 5.0 = 45.0")
                                .append(LS)
                                .append(this.menu)
                                .append("45.0 + 5.0 = 50.0")
                                .append(LS)
                                .append(this.menu)
                                .toString()
                )
        );
    }

    @Test
    public void whenSubtractThenAnswer5() {
        Input input = new StubInput(new String[]{"2", "10", "5", "0"});
        new InteractCalc(
                new ValidateInput(input), new Calculator()
        ).action();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(this.menu)
                                .append("10.0 - 5.0 = 5.0")
                                .append(LS)
                                .append(this.menu)
                                .toString()
                )
        );
    }

    @Test
    public void whenMultipleThenAnswer5() {
        Input input = new StubInput(new String[]{"3", "2.5", "2", "0"});
        new InteractCalc(
                new ValidateInput(input), new Calculator()
        ).action();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(this.menu)
                                .append("2.5 * 2.0 = 5.0")
                                .append(LS)
                                .append(this.menu)
                                .toString()
                )
        );
    }

    @Test
    public void whenDivThenAnswer2() {
        Input input = new StubInput(new String[]{"4", "6", "3", "0"});
        new InteractCalc(
                new ValidateInput(input), new Calculator()
        ).action();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(this.menu)
                                .append("6.0 / 3.0 = 2.0")
                                .append(LS)
                                .append(this.menu)
                                .toString()
                )
        );
    }
}