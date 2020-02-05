package ru.job4j.servlets.userservlet;

import java.util.Set;
/**
 * Store
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 05.02.2020
 */
public interface Store {
    User add(User user);
    User update(User user);
    User delete(User user);
    Set<User> findAll();
    User findById(User user);
}
