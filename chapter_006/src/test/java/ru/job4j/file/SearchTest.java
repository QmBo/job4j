package ru.job4j.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class SearchTest {
    private static final Logger LOG = LogManager.getLogger(SearchTest.class);

    private String tmp = format("%s/%s", System.getProperty("java.io.tmpdir"), "SearchTest");
    private ArrayList<String> names = new ArrayList<>(Arrays.asList("read.txt", "read.log", "read.rtfm"));
    private List<File> exp = new ArrayList<>();

    @Before
    public void setUp() {
        ArrayList<String> dirs = new ArrayList<>(Arrays.asList("1", "prog/var/www/tram.1.2", "1/bin", "TEMP"));
        ArrayList<String> dirsPath = new ArrayList<>();
        for (String dir : dirs) {
            dirsPath.add(format("%s/%s", tmp, dir));
        }
        try {
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
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

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
        new File(tmp).delete();
    }

    @Test
    public void whenSearchThen8Found() {
        Search search = new Search();
        List<File> res = search.files(tmp, Arrays.asList("txt", "log"));
        Collections.sort(res);
        Collections.sort(exp);
        assertThat(res.size(), is(8));
        assertThat(res, is(exp));
    }
}