package ru.job4j.lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class Calculator {

    public void multiple(int start, int finish, int value,
                         BiFunction<Integer, Integer, Double> op,
                         Consumer<Double> media) {
        for (int index = start; index != finish; index++) {
            media.accept(op.apply(value, index));
        }
    }
}