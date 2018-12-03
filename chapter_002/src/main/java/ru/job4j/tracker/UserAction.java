package ru.job4j.tracker;
/**
 * UserAction
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 05.06.2018
 */
public interface UserAction {
    /**
     * Key of Actiom.
     * @return kye.
     */
    int key();

    /**
     * Main Action.
     * @param input input.
     * @param tracker tracker.
     */
    void execute(Input input, ITracker tracker);

    /**
     * Menu information of Action.
     * @return menu string.
     */
    String info();
}
