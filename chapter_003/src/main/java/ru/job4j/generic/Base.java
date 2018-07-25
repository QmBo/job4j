package ru.job4j.generic;

/**
 * @author Petr Arsentev (mailto:parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public abstract class Base {
    /**
     * Id.
     */
    private final String id;

    /**
     * Constructor.
     * @param id id.
     */
    protected Base(final String id) {
        this.id = id;
    }

    /**
     * Id getter.
     * @return id.
     */
    public String getId() {
        return id;
    }
}