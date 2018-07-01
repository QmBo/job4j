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
//        int result;
//        if (this.age != o.getAge()) {
//            result = this.age > o.getAge() ? 1 : -1;
//        } else {
//            result = this.name.compareTo(o.getName());
//        }
//        return result;
        return this.age != o.getAge()
                ? this.age > o.getAge() ? 1 : -1
                : this.name.compareTo(o.getName());
    }
}
