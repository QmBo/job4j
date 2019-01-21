package ru.job4j.array;

import org.junit.Test;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ConvertXSQTTest {

    @Test
    public void whenInputThenConvert() {
        ConvertXSQT convert = new ConvertXSQT();
        try {
            convert.convert(
                    new File(Objects.requireNonNull(getClass().getClassLoader()
                            .getResource("array/test/TestXML.xml")).getFile()),
                    new File("TestNewXML.xml"),
                    new File(Objects.requireNonNull(getClass().getClassLoader()
                            .getResource("array/test/XSTL.xml")).getFile())
            );
        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
        boolean isOk = false;
        try (Stream<String> stream = Files.lines(Paths.get("TestNewXML.xml"))) {
            assertNotNull(stream);
            isOk = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(isOk);
    }
}