package ru.job4j.coffe;

import java.util.Arrays;
/**
 * Change
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 28.06.2018
 */
public class Change {
    /**
     * Наминалы разменных монет.
     */
    private int[] coins;

    /**
     * Конструктор.
     * @param coins наминалы разменных монет.
     */
    public Change(int[] coins) {
        this.coins = coins;
    }

    /**
     * Подсчёт сдачи.
     * @param value внесено средств.
     * @param price цена.
     * @return сдача.
     */
    public int[] changes(int value, int price) {
        int[] result = new int[0];
        int chang = value - price;
        if (chang > 0) {
            int[] temp = new  int[chang];
            int position = 0;
            for (int index = 0; index != this.coins.length; index++) {
                while (chang >= coins[index]) {
                    temp[position++] = coins[index];
                    chang -= coins[index];
                }
            }
            result = Arrays.copyOf(temp, position);
        }
        return result;
    }
}
