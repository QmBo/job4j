package ru.job4j.find;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * ArgsTest
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 25.03.2019
 */
public class ArgsTest {

    @Test
    public void whenArgsCorrectMaskThenGetDirNameOutMask() {
        String[] args = {"-d", "c:/", "-n", "*.txt", "-m", "-o", "log.txt"};
        Args in = new Args(args);
        assertThat(in.directory(), is("c:/"));
        assertThat(in.name(), is("*.txt"));
        assertThat(in.output(), is("log.txt"));
        assertThat(in.isMask(), is(true));
    }

    @Test
    public void whenArgsCorrectFoolThenGetDirNameOutMask() {
        String[] args = {"-d", "c:/", "-n", "test.txt", "-f", "-o", "log.txt"};
        Args in = new Args(args);
        assertThat(in.directory(), is("c:/"));
        assertThat(in.name(), is("test.txt"));
        assertThat(in.output(), is("log.txt"));
        assertThat(in.isMask(), is(false));
    }

    @Test(expected = IllegalStateException.class)
    public void whenFlagNotFoundException() {
        String[] args = {"-d", "c:/", "-n", "*.txt", "-e", "-o", "log.txt"};
        new Args(args);
    }


    @Test(expected = IllegalStateException.class)
    public void whenArgsUnCorrectThenException() {
        String[] args = {"-d", "c:/", "-n", "*.txt", "-o", "log.txt"};
        new Args(args);
    }
}