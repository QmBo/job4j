package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleInputReaderTest {
    private static final Logger LOG = LogManager.getLogger(SimpleInputReaderTest.class);

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

    @Test
    public void whenAbusesTheOutHasNotAbuses() {
        String[] abuse = {"be", "to"};
        byte[] exp = "To , or no  ".getBytes();
        OutputStream out = new ByteArrayOutputStream();
        try (ByteArrayInputStream in = new ByteArrayInputStream("To be, or no to be".getBytes(StandardCharsets.UTF_8))) {
            new SimpleInputReader().dropAbuses(in, out, abuse);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        assertThat(((ByteArrayOutputStream) out).toByteArray(), is(exp));
    }

}