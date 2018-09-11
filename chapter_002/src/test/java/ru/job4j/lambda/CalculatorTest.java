package ru.job4j.lambda;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
    @Test
    public void whenAdd1Until3() {
        Calculator calc = new Calculator();
        List<Double> buffer = new ArrayList<>();
        calc.multiple(
                0, 3, 1,
                (value, index) -> (double) value + index,
                result -> buffer.add(result)
        );
        assertThat(buffer, is(Arrays.asList(1D, 2D, 3D)));
    }

    @Test
    public void whenMulti2Then24() {
        Calculator calc = new Calculator();
        List<Double> buffer = new ArrayList<>();
        calc.multiple(
                1, 4, 2,
                (value, index) -> (double) value * index,
                result -> buffer.add(result)
        );
        assertThat(buffer, is(Arrays.asList(2D, 4D, 6D)));
    }

    @Test
    public void whenLineThen468() {
        Calculator calc = new Calculator();
        List<Double> buffer = calc.diapason(
                1, 4,
                x -> x * 2 + 2
        );
        assertThat(buffer, is(Arrays.asList(4D, 6D, 8D)));
    }

    @Test
    public void whenSquareThen468() {
        Calculator calc = new Calculator();
        List<Double> buffer = calc.diapason(
                1, 4,
                x -> x * x - x - 2
        );
        assertThat(buffer, is(Arrays.asList(-2D, 0D, 4D)));
    }

    @Test
    public void whenLogThen468() {
        Calculator calc = new Calculator();
        List<Double> buffer = calc.diapason(
                0, 2,
                x -> 1 / Math.exp(x)
        );
        assertThat(buffer.size(), is(2));
        assertThat(buffer.get(0), is(1D));
        assertThat(buffer.get(1), closeTo(0.367, 0.001));
    }
}