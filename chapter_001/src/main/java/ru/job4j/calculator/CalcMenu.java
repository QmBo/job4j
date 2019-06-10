package ru.job4j.calculator;

import ru.job4j.tracker.Input;
import java.util.ArrayList;

import static java.lang.String.format;
/**
 * CalcMenu
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 07.06.2019
 */
public class CalcMenu {
    /**
     * Uncorrected message answer line.
     */
    private static final String UNCORRECTED_MESSAGE = "Введите корректный данные.";
    /**
     * Input.
     */
    private final Input input;
    /**
     * Calculator.
     */
    private final Calculator calc;
    /**
     * Exit flag.
     */
    private boolean exit;
    /**
     * Actions capacity.
     */
    private ArrayList<CalcAction> actions = new ArrayList<>(100);

    /**
     * Constructor.
     * @param input input.
     * @param calc calculator.
     */
    public CalcMenu(final Input input, final Calculator calc) {
        this.calc = calc;
        this.input = input;
        this.initMenu();
    }

    /**
     * Exit flag getter.
     * @return is time to exit.
     */
    public boolean isExit() {
        return this.exit;
    }

    /**
     * Menu constructor.
     */
    private void initMenu() {
        int pos = -1;
        this.actions.add(new ExitAction("Выход", ++pos));
        this.actions.add(new AddAction("Сложение", ++pos));
        this.actions.add(new SubtractAction("Вычитание", ++pos));
        this.actions.add(new MultipleAction("Умножение", ++pos));
        this.actions.add(new DivAction("Деление", ++pos));
    }

    /**
     * Menu shower.
     */
    public void showMenu() {
        for (CalcAction action : this.actions) {
            System.out.println(action.message());
        }
    }

    /**
     * Answer analyser.
     * @param message question
     * @return checked answer.
     */
    private double ask(String message) {
        Double result = null;
        boolean hasAnswer = false;
        while (!hasAnswer) {
            String line = this.input.ask(message);
            if ("M".equals(line) || "m".equals(line)) {
                if (this.calc.isHasResult()) {
                    result = this.calc.getResult();
                    hasAnswer = true;
                }
            } else {
                try {
                    result = Double.valueOf(line);
                    hasAnswer = true;
                } catch (NumberFormatException e) {
                    System.out.println(UNCORRECTED_MESSAGE);
                }
            }
        }
        return result;
    }

    /**
     * Action starter.
     * @param key action kay.
     */
    public void action(int key) {
        this.actions.get(key).execute();
    }

    /**
     * Array supported keys of actions getter.
     * @return array supported keys.
     */
    public int[] getRange() {
        int[] result = new int[this.actions.size()];
        int i = 0;
        for (CalcAction action : actions) {
            result[i++] = action.getKay();
        }
        return result;
    }

    /**
     * Exit.
     */
    private class ExitAction extends BaseCalcAction {
        protected ExitAction(final String name, final int kay) {
            super(name, kay);
        }

        @Override
        public void execute() {
            exit = true;
        }
    }

    /**
     * Add.
     */
    private class AddAction extends BaseCalcAction {
        protected AddAction(final String name, final int kay) {
            super(name, kay);
        }

        @Override
        public void execute() {
            double first = ask("Введите первое слагаемое.");
            double second = ask("Введите второе слагаемое.");
            calc.add(first, second);
            System.out.println(format("%s + %s = %s", first, second, calc.getResult()));
        }
    }

    /**
     * Subtract.
     */
    private class SubtractAction extends BaseCalcAction {
        protected SubtractAction(final String name, final int kay) {
            super(name, kay);
        }

        @Override
        public void execute() {
            double first = ask("Введите уменбшаемое.");
            double second = ask("Введите вычитаемое.");
            calc.subtract(first, second);
            System.out.println(format("%s - %s = %s", first, second, calc.getResult()));
        }
    }

    /**
     * Multiple.
     */
    private class MultipleAction extends BaseCalcAction {
        protected MultipleAction(final String name, final int kay) {
            super(name, kay);
        }

        @Override
        public void execute() {
            double first = ask("Введите первый множитель.");
            double second = ask("Введите второй множитель.");
            calc.multiple(first, second);
            System.out.println(format("%s * %s = %s", first, second, calc.getResult()));
        }
    }

    /**
     * Div.
     */
    private class DivAction extends BaseCalcAction {
        protected DivAction(final String name, final int kay) {
            super(name, kay);
        }

        @Override
        public void execute() {
            double first = ask("Введите делимое.");
            double second = ask("Введите делитель.");
            calc.div(first, second);
            System.out.println(format("%s / %s = %s", first, second, calc.getResult()));
        }
    }
}
