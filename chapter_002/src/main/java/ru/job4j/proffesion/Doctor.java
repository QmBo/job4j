package ru.job4j.proffesion;

public class Doctor extends Profession {

    public Doctor(String name, String profession) {
        super(name, profession);
    }

    public Diagnose heal(Patient patient) {
        return new Diagnose(patient.getName());
    }
}
