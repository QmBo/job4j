package ru.job4j.cache;

import com.google.common.base.Joiner;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LoaderTest {
    private final static String LS = System.lineSeparator();

    @Test
    public void whenNameThenLoad() {
        Loader loader = new Loader();
        String exp = Joiner.on(LS).join("Tim", "Bob");
        assertThat(loader.getCache("./src/test/resources/Names.txt"), is(exp));
    }

    @Test
    public void whenAddressThenReLoad() {
        Loader loader = new Loader();
        String exp = Joiner.on(LS).join("Tim", "Bob");
        String exp2 = Joiner.on(LS).join("Moscow", "London", "LA");
        assertThat(loader.getCache("./src/test/resources/Names.txt"), is(exp));
        assertThat(loader.getCache("./src/test/resources/Address.txt"), is(exp2));
    }

    @Test
    public void whenReloadAddressThenReLoad() {
        Loader loader = new Loader();
        String exp = Joiner.on(LS).join("Tim", "Bob");
        String exp2 = Joiner.on(LS).join("Moscow", "London", "LA");
        assertThat(loader.getCache("./src/test/resources/Names.txt"), is(exp));
        assertThat(loader.getCache("./src/test/resources/Address.txt"), is(exp2));
        assertThat(loader.getCache("./src/test/resources/Address.txt"), is(exp2));
    }

}