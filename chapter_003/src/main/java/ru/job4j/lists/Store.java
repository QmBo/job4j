package ru.job4j.lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/**
 * Store
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 15.08.2018
 */
public class Store {

    /**
     * Check differences in two lists.
     * @param previous previous.
     * @param current current.
     * @return differences.
     */
    public Info diff(List<User> previous, List<User> current) {
        int news = 0;
        int deletes = 0;
        int edits = 0;
        Map<Integer, User> prevMap = new HashMap<>(100);
        Map<Integer, User> curMap = new HashMap<>(100);
        for (User user : previous) {
            prevMap.put(user.id, user);
        }
        for (User user : current) {
            curMap.put(user.id, user);
        }
        for (Integer curKey : curMap.keySet()) {
            if (prevMap.containsKey(curKey)) {
                if (!curMap.get(curKey).name.equals(prevMap.get(curKey).name)) {
                    edits++;
                }
            } else {
                news++;
            }
        }
        for (Integer prevKey : prevMap.keySet()) {
            if (!curMap.containsKey(prevKey)) {
                deletes++;
            }
        }
        return new Info(news, deletes, edits);
    }

    /**
     * User class.
     */
    public static class User {
        /**
         * ID.
         */
        private int id;
        /**
         * Name.
         */
        private String name;

        /**
         * Constructor.
         * @param id id.
         * @param name name.
         */
        public User(int id, String name) {
            this.name = name;
            this.id = id;
        }
    }

    /**
     * Info class.
     */
    public static class Info {
        /**
         * News.
         */
        private final int news;
        /**
         * Deletes.
         */
        private final int deletes;
        /**
         * Edits.
         */
        private final int edits;

        /**
         * Constructor.
         * @param news news.
         * @param deletes deletes.
         * @param edits edits.
         */
        public Info(int news, int deletes, int edits) {
            this.news = news;
            this.deletes = deletes;
            this.edits = edits;
        }

        /**
         * To String.
         * @return string.
         */
        @Override
        public String toString() {
            return String.format("New: %s, Deleted: %s, Edits: %s",
                    this.news, this.deletes, this.edits);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return news == info.news
                    && deletes == info.deletes
                    && edits == info.edits;
        }

        @Override
        public int hashCode() {
            return Objects.hash(news, deletes, edits);
        }
    }
}