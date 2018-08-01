package ru.job4j.map;

import java.util.Calendar;
/**
 * User
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 01.08.2018
 */
public class User {
    /**
     * User name.
     */
    private String name;
    /**
     * Number of children.
     */
    private int children;
    /**
     * User birthday.
     */
    private Calendar birthday;

    /**
     * Constructor.
     * @param name user name.
     * @param children number of children.
     * @param birthday user birthday.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * Name getter.
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Number of children getter.
     * @return children
     */
    public int getChildren() {
        return this.children;
    }

    /**
     * User birthday getter.
     * @return birthday.
     */
    public Calendar getBirthday() {
        return this.birthday;
    }
}
