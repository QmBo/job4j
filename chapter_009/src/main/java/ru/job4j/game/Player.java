package ru.job4j.game;

public interface Player {
    void move(int addPosition);
    int getPosition();
    int getRestrictions();
    int setRestrictions();
}
