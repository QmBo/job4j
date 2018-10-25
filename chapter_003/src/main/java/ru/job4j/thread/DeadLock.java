package ru.job4j.thread;

import java.util.concurrent.CountDownLatch;

public class DeadLock {
    private static final CountDownLatch LATCH = new CountDownLatch(2);
    public static void main(String[] args) {
        final User tom = new User("Tom");
        final User bob = new User("Bob");
        new Thread(() -> tom.tack(bob)).start();
        new Thread(() -> bob.tack(tom)).start();
    }

    static class User {
        private final String name;

        public User(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public synchronized void tack(User user) {
            System.out.format("%s tack name %s %n", this.name, user.getName());
            LATCH.countDown();
            System.out.printf("%s is count down latch %n", this.name);
            try {
                LATCH.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s try to release %s %n", this.name, user.getName());
            user.release(this);
        }
        public synchronized void release(User user) {
            System.out.format("%s release %s %n", this.name, user.getName());
        }
    }
}