package ru.job4j.calculator;

/**
 * BaseCalcAction
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 07.06.2019
 */
public abstract class BaseCalcAction implements CalcAction {
    /**
     * Name of action.
     */
    private final String name;
    /**
     * Kay of action.
     */
    private final int kay;

    /**
     * Constructor.
     * @param name of action.
     * @param kay of action.
     */
    protected BaseCalcAction(final String name, final int kay) {
        this.name = name;
        this.kay = kay;
    }

    /**
     * Kay and name shower.
     * @return name and kay.
     */
    @Override
    public String message() {
        return String.format("%s. %s.", this.kay, this.name);
    }

    /**
     * Kay getter.
     * @return kay.
     */
    public int getKay() {
        return this.kay;
    }
}
