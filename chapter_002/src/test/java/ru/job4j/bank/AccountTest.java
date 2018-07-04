package ru.job4j.bank;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void debit() {
        Account account = new Account(100.12, "test");
        account.debit(12.11);
        assertEquals(account.getValue(), 112.23d, 0.001);
    }

    @Test
    public void credit() {
        Account account = new Account(100.12, "test");
        account.credit(12.11);
        assertEquals(account.getValue(), 88.01, 0.001);
    }
}