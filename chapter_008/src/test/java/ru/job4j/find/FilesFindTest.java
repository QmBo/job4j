package ru.job4j.find;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * FilesFindTest
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.03.2019
 */
public class FilesFindTest {

    private String tmp = format("%s/%s", System.getProperty("java.io.tmpdir"), "FilesFindTest");
    private ArrayList<String> names = new ArrayList<>(Arrays.asList("read.txt", "read.log", "read.rtfm"));
    private List<File> allFiles = new ArrayList<>();
    private List<File> readTxt = new ArrayList<>();

    @Before
    public void setUp() {
        ArrayList<String> dirs = new ArrayList<>(Arrays.asList("1", "prog/var/www/tram.1.2", "1/bin", "TEMP"));
        ArrayList<String> dirsPath = new ArrayList<>();
        for (String dir : dirs) {
            dirsPath.add(format("%s/%s", tmp, dir));
        }
        for (String dir : dirsPath) {
            for (String name : names) {
                File file = new File(dir, name);
                allFiles.add(file);
                if ("read.txt".equals(name)) {
                    readTxt.add(file);
                }
            }
        }
    }

    @Test
    public void whenNoMaskThenFindFoolName() {
        FilesFind find = new FilesFind("read.txt", false, allFiles);
        List<File> res = find.find();
        Collections.sort(readTxt);
        Collections.sort(res);
        assertThat(res, is(readTxt));
    }

    @Test
    public void whenMaskForEndThenFindMaskName() {
        FilesFind find = new FilesFind("rea*", true, allFiles);
        List<File> res = find.find();
        Collections.sort(allFiles);
        Collections.sort(res);
        assertThat(res, is(allFiles));
    }

    @Test
    public void whenStartWithMaskThenFindMaskName() {
        FilesFind find = new FilesFind("*.txt", true, allFiles);
        List<File> res = find.find();
        Collections.sort(readTxt);
        Collections.sort(res);
        assertThat(res, is(readTxt));
    }
}