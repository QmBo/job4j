package ru.job4j.thread;

import java.util.Objects;
/**
 * Base
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 03.09.2018
 */
public class Base {
    /**
     * ID.
     */
    int id;
    /**
     * Version.
     */
    volatile int version;

    /**
     * Constructor.
     * @param id id.
     * @param version version.
     */
    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return id == base.id && version == base.version;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }
}
