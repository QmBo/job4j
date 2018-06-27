package ru.job4j.tracker;
/**
 * ValidateInput
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 12.06.2018
 */
public class ValidateInput implements Input {
    /**
     * Input.
     */
    private final Input input;

    /**
     * Constructor.
     * @param input input.
     */
    public ValidateInput(Input input) {
        this.input = input;
    }

    /**
     * Ask question and wait answer.
     * @param question question.
     * @return answer.
     */
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    /**
     * Validate ask.
     * @param question question.
     * @param range valid values.
     * @return answer.
     */
    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int result = -1;
        while (invalid) {
            try {
                result = Integer.valueOf(this.input.ask(question));
                boolean invalidRange = true;
                for (int key : range) {
                    if (result == key) {
                        invalidRange = false;
                        break;
                    }
                }
                if (invalidRange) {
                    throw new MenuOutException("Такого пунктв меню нет!");
                }
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Введите корректные данные.");
            } catch (MenuOutException moe) {
                System.out.println(moe.getMessage());
                System.out.println("Введите корректные данные.");
            }
        }
        return result;
    }
}