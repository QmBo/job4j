package ru.job4j.garbage;

import java.util.LinkedList;
import java.util.List;

import static java.lang.String.format;
/**
 * GC
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 16.12.2019
 * Программа написана для демонстрации работы сборщика мусора.
 * При 12 000 объектов ~ 1 031 килобайт (х64).
 * при запуске JVM с параметром -Xmx1m (1 мегабайт памяти для виртульной машины)
 * программа будет палать с ошибкой java.lang.OutOfMemoryError: Java heap space,
 * если не складывать объекты в коллекцию программа корректро завершится,
 * переодически вызываюя сборщик мусора.
 * @noinspection MismatchedQueryAndUpdateOfCollection
 */
public class GC {

    public static void main(String[] args) {
        new GC().go();
    }

    /**
     * Метод создаёт и складывает объекты в коллекцию.
     * Раскоментировать стороку new User("test");
     * и закоментировать users.add(new User("test"));
     * для корректной работы сборщика мусора.
     */
    private void go() {
        List<User> users = new LinkedList<>();
        System.out.println(format("All memory: %s", Runtime.getRuntime().totalMemory()));
        System.out.println(
                format(
                        "Used memory: %s",
                        (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())
                )
        );
        for (int i = 0; i < 12000; i++) {
            users.add(new User("test"));
//            new User("test");
        }
    }

    /**
     * 88 байт при имени "test" (x64).
     * @noinspection deprecation
     */
    private class User {
        private final String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("GC");
            super.finalize();
        }

        @Override
        public String toString() {
            return "User{"
                    + "name='" + name + '\''
                    + '}';
        }
    }
}
