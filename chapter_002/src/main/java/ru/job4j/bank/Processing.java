package ru.job4j.bank;

import java.util.*;

/**
 * Processing
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 04.07.2018
 */
public class Processing {
    /**
     * Accounts.
     */
    private Map<User, List<Account>> users = new HashMap<>();

    /**
     * Add user.
     * @param user user.
     */
    public void addUser(User user) {
        this.users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Delete user.
     * @param user user.
     */
    public void deleteUser(User user) {
        this.users.remove(user);
    }

    /**
     * Add account to user.
     * @param passport passport user.
     * @param account account.
     */
    public void addAccountToUser(String passport, Account account) {
        for (User activeUser : this.users.keySet()) {
            if (activeUser.getPassport().equals(passport)) {
                this.users.get(activeUser).add(account);
                break;
            }
        }
    }

    /**
     * Delete account of user.
     * @param passport passport user.
     * @param account account.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        for (User activeUser : this.users.keySet()) {
            if (activeUser.getPassport().equals(passport)) {
                this.users.get(activeUser).remove(account);
                break;
            }
        }
    }

    /**
     * Return all accounts of user.
     * @param passport passport user.
     * @return account list.
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> result = new ArrayList<>();
        for (User activeUser : this.users.keySet()) {
            if (activeUser.getPassport().equals(passport)) {
                result = this.users.get(activeUser);
                break;
            }
        }
        return result;
    }

    /**
     * Transfer money.
     * @param srcPassport sours passport.
     * @param srcRequisite sours requisite.
     * @param destPassport destination passport.
     * @param destRequisite destination requisite.
     * @param amount transfer sum.
     * @return is transferred.
     */
    public boolean transferMoney(
            String srcPassport, String srcRequisite,
            String destPassport, String destRequisite,
            double amount
    ) {
        boolean result = false;
        Account src = findAccount(srcPassport, srcRequisite);
        Account dest = findAccount(destPassport, destRequisite);
        if (src != null && dest != null && src.getValue() >= amount) {
            src.credit(amount);
            dest.debit(amount);
            result = true;
        }
        return result;
    }

    /**
     * Transfer helper. Find and return account.
     * @param passport passport.
     * @param requisite requisite.
     * @return account.
     */
    private Account findAccount(String passport, String requisite) {
        Account result = null;
        for (User user : this.users.keySet()) {
            if (user.getPassport().equals(passport)) {
                for (Account account : this.users.get(user)) {
                    if (account.getRequisites().equals(requisite)) {
                        result = account;
                        break;
                    }
                }
                break;
            }
        }
        return result;
    }

    /**
     * Return all added users.
     * @return users.
     */
    public List<User> showAllUsers() {
        return new ArrayList<>(users.keySet());
    }
}
