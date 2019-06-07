package ru.job4j.calculator;

/**
 * CalcAction
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 07.06.2019
 */
public interface CalcAction {
    String message();
    void execute();
    int getKay();
}
