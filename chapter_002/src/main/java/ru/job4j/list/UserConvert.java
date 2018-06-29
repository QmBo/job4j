package ru.job4j.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * UserConvert
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 29.06.2018
 */
public class UserConvert {
    /**
     * Take list users and return users map.
     * @param list input list
     * @return map.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        ArrayList<User> users = new ArrayList<>(list);
        for (User user : users) {
            result.put(user.getId(), user);
        }
        return result;
    }
}
