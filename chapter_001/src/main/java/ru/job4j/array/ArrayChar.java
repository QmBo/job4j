package ru.job4j.array;
/**
 * Обертка над строкой.
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 0.1
 */
public class ArrayChar {
    /**
     * Массив символов для проверки.
     */
    private char[] data;

    /**
     * Конструктор.
     * @param line строка для проверки.
     */
    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет. что слово начинается с префикса.
     * @param prefix префикс.
     * @return если слово начинаеться с префикса
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        for (int index = 0; index < value.length; index++) {
            if (value[index] != this.data[index]) {
                result = false;
                break;
            }
        }
        return result;
    }
}