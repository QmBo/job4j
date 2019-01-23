package ru.job4j.array;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.format;
import static org.junit.Assert.*;

public class StoreXMLTest {
    private static final Logger LOG = LogManager.getLogger(StoreXMLTest.class);
    @Test
    public void whenListThenListToXML() {
        String tmpDir = System.getProperty("java.io.tmpdir");
        StoreXML xml = new StoreXML(new File(format("%s/toTestXML.xml", tmpDir)));
        List<Entry> list = new ArrayList<>();
        list.add(new Entry(1));
        list.add(new Entry(2));
        list.add(new Entry(3));
        list.add(new Entry(4));
        xml.save(list);
        boolean isOk = false;
        try (
                Stream<String> stream = Files.lines(
                        Paths.get(format("%s/toTestXML.xml", tmpDir))
                )
        ) {
            assertNotNull(stream);
            isOk = true;
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        assertTrue(isOk);
    }
}