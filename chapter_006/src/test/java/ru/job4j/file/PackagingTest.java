package ru.job4j.file;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * PackagingTest
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 14.03.2019
 */
public class PackagingTest {

    private String tmp = format("%s/%s", System.getProperty("java.io.tmpdir"), "PackagingTest");
    private ArrayList<String> names = new ArrayList<>(Arrays.asList("read.txt", "read.log", "read.rtfm"));
    private List<File> exp = new ArrayList<>();

    @Before
    public void setUp() throws IOException {
        ArrayList<String> dirs = new ArrayList<>(Collections.singletonList("1"));
        ArrayList<String> dirsPath = new ArrayList<>();
        for (String dir : dirs) {
            dirsPath.add(format("%s/%s", tmp, dir));
        }
        new File(tmp).mkdir();
        for (String dir : dirsPath) {
            File dirName = new File(dir);
            dirName.mkdirs();
            for (String name : names) {
                File file = new File(dir, name);
                file.createNewFile();
                if (!name.endsWith(".rtfm")) {
                    exp.add(file);
                }
            }
        }
    }

    @After
    public void unSetUp() {
        ArrayList<String> dirs = new ArrayList<>(Collections.singletonList("1"));
        ArrayList<String> dirsPath = new ArrayList<>();
        for (String dir : dirs) {
            dirsPath.add(format("%s/%s", tmp, dir));
        }
        for (String dir : dirsPath) {
            for (String name : names) {
                new File(dir, name).delete();
            }
            new File(dir).delete();
        }
        new File(tmp, "Test.zip").delete();
        new File(tmp).delete();
    }

    @Test
    public void whenFilesThenAddToZip() throws Exception {
        Packaging packaging = new Packaging();
        packaging.pack(Collections.singletonList(exp.get(0)), tmp, "Test.zip");
        assertThat(new File(tmp, "Test.zip").delete(), is(true));
    }
}