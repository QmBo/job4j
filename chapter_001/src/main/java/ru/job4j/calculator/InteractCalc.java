package ru.job4j.calculator;

import ru.job4j.tracker.ConsoleInput;
import ru.job4j.tracker.Input;
import ru.job4j.tracker.ValidateInput;
/**
 * InteractCalc
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 07.06.2019
 */
public class InteractCalc {
    /**
     * Menu question line.
     */
    private static final String FIRST_LINE = "Выберите дейстрвие.";
    /**
     * Input.
     */
    private final Input input;
    /**
     * Calculator menu.
     */
    private final CalcMenu menu;

    /**
     * Constructor, setup input.
     * @param input input.
     * @param calc calculator.
     */
    public InteractCalc(final Input input, final Calculator calc) {
        this.input = input;
        this.menu = new CalcMenu(this.input, calc);
    }

    /**
     * Main, start point console's input.
     * @param args not supported.
     */
    public static void main(String[] args) {
        new InteractCalc(
                new ValidateInput(
                        new ConsoleInput()
                ), new Calculator()
        ).action();
    }

    /**
     * Main Loop.
     * Menu shower.
     */
    public void action() {
        do {
            this.menu.showMenu();
            int[] range = this.menu.getRange();
            this.menu.action(
                    this.input.ask(FIRST_LINE, range)
            );
        } while (!this.menu.isExit());
    }
}
