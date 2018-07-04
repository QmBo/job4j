package ru.job4j.bank;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProcessingTest {

    @Test
    public void whenAddUserThenUserHasInMap() {
        Processing processing = new Processing();
        processing.addUser(new User("Petrovich", "4508 123456"));
        Account account = new Account(0d, "102030");
        processing.addAccountToUser("4508 123456", account);
        ArrayList<Account> exp = new ArrayList<>();
        exp.add(account);
        assertThat(processing.getUserAccounts("4508 123456"), is(exp));
    }

    @Test
    public void whenAddDuplicateUserThenMapHas1User() {
        Processing processing = new Processing();
        User user = new User("Kuzmich", "4508 123478");
        processing.addUser(user);
        Account account = new Account(0d, "102030");
        processing.addAccountToUser("4508 123478", account);
        processing.addUser(user);
        List<User> exp = new ArrayList<>();
        exp.add(user);
        assertThat(processing.showAllUsers(), is(exp));
    }

    @Test
    public void whenDeleteUserThenUserHasNotInMap() {
        Processing processing = new Processing();
        processing.addUser(new User("Petrovich", "4508 123456"));
        User secondUser = new User("Kuzmich", "4508 123478");
        Account account = new Account(0d, "102030");
        processing.addAccountToUser("4508 123456", account);
        processing.addUser(secondUser);
        processing.deleteUser(new User("Petrovich", "4508 123456"));
        processing.deleteUser(secondUser);
        assertThat(processing.showAllUsers(), is(new ArrayList<>()));
    }

    @Test
    public void whenAddAccountToUserThenUserHasAccount() {
        Processing processing = new Processing();
        processing.addUser(new User("Petrovich", "4508 123456"));
        Account firstAccount = new Account(3000d, "102030");
        Account secondAccount = new Account(700.30d, "102030");
        processing.addAccountToUser("4508 123456", firstAccount);
        processing.addAccountToUser("4508 123456", secondAccount);
        ArrayList<Account> exp = new ArrayList<>();
        exp.add(firstAccount);
        exp.add(secondAccount);
        assertThat(processing.getUserAccounts("4508 123456"), is(exp));
    }

    @Test
    public void whenUserHasNotAccountThenEmptyList() {
        Processing processing = new Processing();
        processing.addUser(new User("Petrovich", "4508 123456"));
        Account firstAccount = new Account(3000d, "102030");
        Account secondAccount = new Account(700.30d, "102030");
        processing.addAccountToUser("4508 123456", firstAccount);
        processing.addAccountToUser("4508 123456", secondAccount);
        ArrayList<Account> exp = new ArrayList<>();
        processing.deleteAccountFromUser("4508 123456", firstAccount);
        processing.deleteAccountFromUser("4508 123456", secondAccount);
        assertThat(processing.getUserAccounts("4508 123456"), is(exp));
    }

    @Test
    public void whenDeleteAccountFromUserThenUserHasNotAccount() {
        Processing processing = new Processing();
        processing.addUser(new User("Petrovich", "4508 123456"));
        Account firstAccount = new Account(3000d, "102030");
        Account secondAccount = new Account(700.30d, "102030");
        processing.addAccountToUser("4508 123456", firstAccount);
        processing.addAccountToUser("4508 123456", secondAccount);
        processing.deleteAccountFromUser("4508 123456", firstAccount);
        ArrayList<Account> exp = new ArrayList<>();
        exp.add(secondAccount);
        assertThat(processing.getUserAccounts("4508 123456"), is(exp));
    }

    @Test
    public void whenTransferMoneyIsPossibleThenTrue() {
        Processing processing = new Processing();
        processing.addUser(new User("Petrovich", "4508 123456"));
        processing.addUser(new User("Kuzmich", "4508 123478"));
        processing.addUser(new User("Mihalich", "4508 123490"));
        Account firstAcc = new Account(3000d, "102030");
        Account secondAcc = new Account(100.30d, "202030");
        Account thirdAcc = new Account(200.20d, "102033");
        Account fourAcc = new Account(300.30d, "102034");
        processing.addAccountToUser("4508 123456", firstAcc);
        processing.addAccountToUser("4508 123456", secondAcc);
        processing.addAccountToUser("4508 123478", thirdAcc);
        processing.addAccountToUser("4508 123490", fourAcc);
        assertTrue(
                processing.transferMoney(
                        "4508 123456", "102030",
                        "4508 123490", "102034",
                        3000d
                )
        );
    }

    @Test
    public void whenTransferMoneyIsNotPossibleThenFalse() {
        Processing processing = new Processing();
        processing.addUser(new User("Petrovich", "4508 123456"));
        processing.addUser(new User("Kuzmich", "4508 123478"));
        processing.addUser(new User("Mihalich", "4508 123490"));
        Account firstAcc = new Account(3000d, "102030");
        Account secondAcc = new Account(100.30d, "202030");
        Account thirdAcc = new Account(200.20d, "102033");
        Account fourAcc = new Account(300.30d, "102034");
        processing.addAccountToUser("4508 123456", firstAcc);
        processing.addAccountToUser("4508 123456", secondAcc);
        processing.addAccountToUser("4508 123478", thirdAcc);
        processing.addAccountToUser("4508 123490", fourAcc);
        assertFalse(
                processing.transferMoney(
                        "4508 123456", "202030",
                        "4508 123490", "102034",
                        100.3001d
                )
        );
    }

    @Test
    public void whenRequisitesNotFoundThenFalse() {
        Processing processing = new Processing();
        processing.addUser(new User("Petrovich", "4508 123456"));
        processing.addUser(new User("Kuzmich", "4508 123478"));
        Account firstAcc = new Account(3000d, "102030");
        Account secondAcc = new Account(100.30d, "202030");
        Account thirdAcc = new Account(200.20d, "102033");
        processing.addAccountToUser("4508 123456", firstAcc);
        processing.addAccountToUser("4508 123456", secondAcc);
        processing.addAccountToUser("4508 123478", thirdAcc);
        assertFalse(
                processing.transferMoney(
                        "4508 123456", "202033",
                        "4508 123490", "102034",
                        0.001d
                )
        );
    }
}