package ru.job4j.thread;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;
/**
 * UserStorage
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 27.08.2018
 */
@ThreadSafe
public class UserStorage {
    /**
     * Capacity.
     */
    @GuardedBy("this")
    private Map<Integer, User> users;

    /**
     * Constructor.
     */
    public UserStorage() {
        this.users = new HashMap<>(100);
    }

    /**
     * Add new User.
     * @param user user.
     * @return is adding.
     */
    public synchronized boolean add(User user) {
        return this.users.putIfAbsent(user.getId(), user) == null;
    }

    /**
     * Update User amount.
     * @param user user.
     * @return is updated.
     */
    public synchronized boolean update(User user) {
        boolean isUpdated = false;
        if (this.users.containsKey(user.getId())) {
            this.users.put(user.getId(), user);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * Delete user.
     * @param user user.
     * @return is deleted.
     */
    public synchronized boolean delete(User user) {
        return this.users.remove(user.getId(), user);
    }

    /**
     * Transfer amount.
     * @param fromId from id.
     * @param toId to id
     * @param amount amount.
     * @return is transferred.
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean isTransferred = false;
        if (this.users.containsKey(fromId)
                && this.users.containsKey(toId)
                && this.users.get(fromId).getAmount() >= amount) {
            User from = this.users.get(fromId);
            User to = this.users.get(toId);
            from.setAmount(from.getAmount() - amount);
            to.setAmount(to.getAmount() + amount);
            isTransferred = true;
        }
        return isTransferred;
    }
}
