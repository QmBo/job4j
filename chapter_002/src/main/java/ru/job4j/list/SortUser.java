package ru.job4j.list;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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
}
