package ru.job4j;

import org.junit.Test;
import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleInputReaderTest {

    @Test
    public void whenEvenInStreamThenTrue() {
        SimpleInputReader sir = new SimpleInputReader();
        assertThat(sir.isNumber(new ByteArrayInputStream(new byte[] {Byte.parseByte("100")})), is(true));
    }

    @Test
    public void whenNotEvenInStreamThenFalse() {
        SimpleInputReader sir = new SimpleInputReader();
        assertThat(sir.isNumber(new ByteArrayInputStream(new byte[] {Byte.parseByte("99")})), is(false));
    }

}