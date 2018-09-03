package ru.job4j.email;

import org.junit.Test;

public class EmailNotificationTest {

    @Test
    public void whenSendEmailThenSanded() {
        EmailNotification email = new EmailNotification();
        User spam = new User("Spam", "spam@ya.ru");
        for (int i = 0; i < 10; i++) {
            email.emailTo(spam);
        }
    }
}