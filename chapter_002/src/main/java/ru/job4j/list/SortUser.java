package ru.job4j.list;

import java.util.*;

/**
 * SortUser
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 01.07.2018
 */
public class SortUser {
    /**
     * Return sorted set of users.
     * @param list users to sort.
     * @return sorted users.
     */
    public Set<UserComparable> sort(List<UserComparable> list) {
        return new TreeSet<>(list);
    }

    /**
     * Return sorted set of users at name length.
     * @param users users to sort.
     * @return sorted users.
     */
    public List<UserComparable> sortNameLength(List<UserComparable> users) {
        users.sort(
                new Comparator<UserComparable>() {
                    @Override
                    public int compare(UserComparable o1, UserComparable o2) {
                        int result = 0;
                        if (o1.getName().length() != o2.getName().length()) {
                            result = o1.getName().length() > o2.getName().length() ? 1 : -1;
                        }
                        return result;
                    }
                }
        );
        return users;
    }

    /**
     * Return sorted set of users at name and age.
     * @param users users to sort.
     * @return sorted users.
     */
    public List<UserComparable> sortByAllFields(List<UserComparable> users) {
        users.sort(
                new Comparator<UserComparable>() {
                    @Override
                    public int compare(UserComparable o1, UserComparable o2) {
                        int result = 0;
                        if (o1.getName().compareTo(o2.getName()) == 0) {
                            if (o1.getAge() != o2.getAge()) {
                                result = o1.getAge() > o2.getAge() ? 1 : -1;
                            }
                        } else {
                            result = o1.getName().compareTo(o2.getName());
                        }
                        return result;
                    }
                }
        );
        return users;
    }
}
