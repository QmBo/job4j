package ru.job4j.tracker;

import java.util.Objects;

/**
 * Item class.
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 01.06.2018
 */
public class Item {
    /**
     * Id.
      */
    private String id;
    /**
     * Name.
      */
    private String name;
    /**
     * Description.
     */
    private String description;
    /**
     * Created.
     */
    private long created;

    /**
     * Constructor.
     */
    public Item() {
        this("no Name", "no Description");
    }

    /**
     * Constructor.
     * @param name name.
     * @param desc description.
     */
    public Item(String name, String desc) {
        this(name, desc, System.currentTimeMillis());
    }

    /**
     * Constructor.
     * @param name name.
     * @param description description.
     * @param created created.
     */
    public Item(String name, String description, long created) {
        this.created = created;
        this.name = name;
        this.description = description;
    }

    /**
     * Set id.
     * @param id id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get id.
     * @return id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get name.
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get description.
     * @return description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get created time.
     * @return time in millis.
     */
    public long getCreated() {
        return this.created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return created == item.created
                && Objects.equals(id, item.id)
                && Objects.equals(name, item.name)
                && Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, created);
    }

    @Override
    public String toString() {
        return String.format(
                "Item{ id='%s' , name='%s' , description='%s' , created='%s'}",
                this.id, this.name, this.description, this.created
        );
    }
}
