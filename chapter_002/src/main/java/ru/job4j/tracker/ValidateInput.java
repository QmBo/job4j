package ru.job4j.tracker;

public class ValidateInput extends ConsoleInput {

    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int result = -1;
        while (invalid) {
            try {
                result = super.ask(question, range);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Введите корректные данные.");
            } catch (MenuOutException moe) {
                System.out.println("Такого пунктв меню нет!");
                System.out.println("Введите корректные данные.");
            }
        }
        return result;
    }
}
