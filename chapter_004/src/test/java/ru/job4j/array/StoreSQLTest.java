package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StoreSQLTest {

    @Test
    public void whenNoDBThenCreate() {
        try (StoreSQL sql = new StoreSQL(new Config())) {
            assertThat(sql.init(), is(true));
        }
    }

    @Test
    public void whenGenerateThen100() {
        try (StoreSQL sql = new StoreSQL(new Config())) {
            sql.generate(10000);
            sql.generate(100);
            assertThat(sql.count("entry"), is(100));
        }
    }

}