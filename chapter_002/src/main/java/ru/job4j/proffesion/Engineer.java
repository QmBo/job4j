package ru.job4j.proffesion;

public class Engineer extends Profession {

    public Engineer(String name, String profession) {
        super(name, profession);
    }

    public House build(Building building) {
        return new House(this.getName());
    }
}
