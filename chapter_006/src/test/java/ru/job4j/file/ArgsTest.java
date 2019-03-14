package ru.job4j.file;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * ArgsTest
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 14.03.2019
 */
public class ArgsTest {

    @Test
    public void whenArgsCorrectThenGetDirExOut() {
        String[] args = {"-d", "c:\\project\\job4j\\", "-e", "*.java", "-o", "project.zip"};
        Args in = new Args(args);
        assertThat(in.directory(), is("c:\\project\\job4j\\"));
        assertThat(in.excule(), is("java"));
        assertThat(in.output(), is("project.zip"));
    }

    @Test(expected = IllegalStateException.class)
    public void whenArgsUnCorrectThenException() {
        String[] args = {"c:\\project\\job4j\\", "-e", "*.java", "-o", "project.zip"};
        new Args(args);
    }

    @Test(expected = IllegalStateException.class)
    public void whenFlagNotFoundException() {
        String[] args = {"-d", "c:\\project\\job4j\\", "-f", "*.java", "-o", "project.zip"};
        new Args(args);
    }
}