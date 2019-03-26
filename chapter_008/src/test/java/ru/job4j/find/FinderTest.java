package ru.job4j.find;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FinderTest {

    private String tmp = format("%s/%s", System.getProperty("java.io.tmpdir"), "FinderTest");
    private ArrayList<String> names = new ArrayList<>(Arrays.asList("read.txt", "read.log", "read.rtfm"));
    private List<File> allFiles = new ArrayList<>();
    private List<File> readTxt = new ArrayList<>();
    private final static String LS = System.lineSeparator();

    @Before
    public void setUp() throws IOException {
        ArrayList<String> dirs = new ArrayList<>(Arrays.asList("1", "prog/var/www/tram.1.2", "1/bin", "TEMP"));
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
                allFiles.add(file);
                if ("read.txt".equals(name)) {
                    readTxt.add(file);
                }
            }
        }
    }

    @SuppressWarnings("Duplicates")
    @After
    public void unSetUp() {
        ArrayList<String> dirs = new ArrayList<>(Arrays.asList("1/bin", "prog/var/www/tram.1.2",
                "prog/var/www", "prog/var", "prog", "1", "TEMP"));
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
        new File(tmp, "test.log").delete();
        new File(tmp).delete();
    }

    @Test
    public void whenFindFoolThenLog() throws IOException {
        new Finder(
                "read.txt",
                tmp,
                false,
                "test.log"
        ).start();
        List<String> res = Files.readAllLines(new File(tmp, "test.log").toPath());
        List<String> exp = new ArrayList<>();
        for (File file : readTxt) {
            exp.add(file.toString());
        }
        Collections.sort(res);
        Collections.sort(exp);
        assertThat(res, is(exp));
    }

}