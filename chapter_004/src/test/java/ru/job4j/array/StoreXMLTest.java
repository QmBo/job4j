package ru.job4j.array;

import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class StoreXMLTest {

    @Test
    public void whenListThenListToXML() {
        StoreXML xml = new StoreXML(new File("toTestXML.xml"));
        List<Entry> list = new ArrayList<>();
        list.add(new Entry(1));
        list.add(new Entry(2));
        list.add(new Entry(3));
        list.add(new Entry(4));
        xml.save(list);
        boolean isOk = false;
        try (Stream<String> stream = Files.lines(Paths.get("toTestXML.xml"))) {
            assertNotNull(stream);
            isOk = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(isOk);
    }
}