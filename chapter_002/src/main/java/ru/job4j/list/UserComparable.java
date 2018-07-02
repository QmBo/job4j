package ru.job4j.list;
/**
 * UserComparable
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 01.07.2018
 */
public class UserComparable implements Comparable<UserComparable> {
    private String name;
    private int age;

    /**
     * Constructor.
     * @param name user name.
     * @param age age of user.
     */
    public UserComparable(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Name getter.
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Age getter.
     * @return age.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Comparator.
     * @param o user to compare.
     * @return compare.
     */
    @Override
    public int compareTo(UserComparable o) {
        int result = this.age - o.getAge();
        if (result == 0) {
            result = this.name.compareTo(o.getName());
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.name, this.age);
    }
}
