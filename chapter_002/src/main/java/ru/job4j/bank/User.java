package ru.job4j.bank;

import java.util.Objects;
/**
 * User
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 04.07.2018
 */
public class User {
    private String name;
    private String passport;

    /**
     * Constructor.
     * @param name name.
     * @param passport passport.
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Name getter.
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Passport getter.
     * @return passport.
     */
    public String getPassport() {
        return this.passport;
    }

    /**
     * Equals this and another object.
     * @param o another object
     * @return equals.
     */
    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            User user = (User) o;
            result = Objects.equals(this.name, user.getName())
                    && Objects.equals(this.passport, user.getPassport());
        }
        return result;
    }

    /**
     * Return hash code.
     * @return hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.passport);
    }
}
