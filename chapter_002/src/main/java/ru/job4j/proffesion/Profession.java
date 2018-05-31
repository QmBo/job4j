package ru.job4j.proffesion;

public class Profession {
    private String name;
    private String profession;

    public Profession(String name, String profession) {
        this.name = name;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public String getProfession() {
        return profession;
    }
}
