package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UserConvert {
    public List<User> convert(List<String> names, Function<String, User> op) {
        List<User> result = new ArrayList<>();
        names.forEach(
                s -> result.add(op.apply(s))
        );
        return result;
    }

    public static class User {
        private final String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" + "name='" + name + '\'' + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return name != null ? name.equals(user.name) : user.name == null;
        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }
    }
}
