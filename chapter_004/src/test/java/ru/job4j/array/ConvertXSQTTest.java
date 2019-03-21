package ru.job4j.array;

import org.junit.Test;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

import static java.lang.String.format;
import static org.junit.Assert.assertNotNull;

public class ConvertXSQTTest {

    @Test
    public void whenInputThenConvert() throws IOException, TransformerException {
        ConvertXSQT convert = new ConvertXSQT();
        String tmpDir = System.getProperty("java.io.tmpdir");
        convert.convert(
                new File(Objects.requireNonNull(getClass().getClassLoader()
                        .getResource("array/test/TestXML.xml")).getFile()),
                new File(format("%s/TestNewXML.xml", tmpDir)),
                new File(Objects.requireNonNull(getClass().getClassLoader()
                        .getResource("array/test/XSTL.xml")).getFile())
        );
        Stream<String> stream = Files.lines(Paths.get(format("%s/TestNewXML.xml", tmpDir)));
        assertNotNull(stream);
    }
}