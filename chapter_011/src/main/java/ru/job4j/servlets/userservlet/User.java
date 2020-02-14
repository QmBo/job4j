package ru.job4j.servlets.userservlet;

import java.util.Date;
/**
 * User
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 05.02.2020
 */
public class User implements Comparable<User> {
    private static final String DEF_NAME = "Default";
    private static final String DEF_EMAIL = "Default";
    private static final String DEF_LOGIN = "Default";
    private static final Date DATE = new Date(System.currentTimeMillis());
    private String id;
    private String name;
    private String eMail;
    private String login;
    private Date createDate;

    /**
     * Constructor.
     */
    public User() {
        this(DEF_NAME, DEF_EMAIL, DEF_LOGIN, DATE);
    }

    /**
     * Constructor.
     * @param name User name
     * @param eMail User email
     * @param login User login
     */
    public User(final String name, final String eMail, final String login) {
        this(name, eMail, login, DATE);
    }

    /**
     * Constructor.
     * @param name User name
     * @param eMail User email
     * @param login User login
     * @param createDate User create date
     */
    public User(final String name, final String eMail, final String login, final Date createDate) {
        this.name = name;
        this.eMail = eMail;
        this.login = login;
        this.createDate = createDate;
    }

    /**
     * Id user setter.
     * @param id new id
     * @return User with new id
     */
    public User setId(final String id) {
        this.id = id;
        return this;
    }

    /**
     * Id getter.
     * @return Users id
     */
    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" + "id='" + id
                + '\'' + ", name='" + name
                + '\'' + ", eMail='" + eMail
                + '\'' + ", login='" + login
                + '\'' + ", createDate=" + createDate
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        if (id != null ? !id.equals(user.id) : user.id != null) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        if (eMail != null ? !eMail.equals(user.eMail) : user.eMail != null) {
            return false;
        }
        if (login != null ? !login.equals(user.login) : user.login != null) {
            return false;
        }
        return createDate != null ? createDate.equals(user.createDate) : user.createDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }

    /**
     * To sorted Users at id/
     * @param o other User
     * @return compare
     * @noinspection NullableProblems
     */
    @Override
    public int compareTo(User o) {
        int result = 1;
        if (o != null) {
            int fist = Integer.valueOf(this.id);
            int second = Integer.valueOf(o.id);
            result = fist - second;
        }
        return result;
    }

    /**
     * Email getter.
     * @return users email
     */
    public String getEmail() {
        return this.eMail;
    }

    /**
     * Login getter.
     * @return users getter
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Name getter.
     * @return users name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Create date getter.
     * @return user create date
     */
    public Date getCreateDate() {
        return this.createDate;
    }
}
