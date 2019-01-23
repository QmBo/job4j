package ru.job4j.array;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import static org.junit.Assert.assertTrue;

public class ConvertXSQTTest {
    private static final Logger LOG = LogManager.getLogger(ConvertXSQTTest.class);

    @Test
    public void whenInputThenConvert() {
        ConvertXSQT convert = new ConvertXSQT();
        String tmpDir = System.getProperty("java.io.tmpdir");
        try {
            convert.convert(
                    new File(Objects.requireNonNull(getClass().getClassLoader()
                            .getResource("array/test/TestXML.xml")).getFile()),
                    new File(format("%s/TestNewXML.xml", tmpDir)),
                    new File(Objects.requireNonNull(getClass().getClassLoader()
                            .getResource("array/test/XSTL.xml")).getFile())
            );
        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
        boolean isOk = false;
        try (
                Stream<String> stream = Files.lines(
                        Paths.get(format("%s/TestNewXML.xml", tmpDir))
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