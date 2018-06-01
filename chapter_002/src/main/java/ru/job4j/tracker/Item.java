package ru.job4j.tracker;
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
}
