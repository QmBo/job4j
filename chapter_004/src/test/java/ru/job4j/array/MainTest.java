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
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void whenAdd1000000ThenWorkNotTooLong() throws IOException, TransformerException {
        long startTime = System.currentTimeMillis();
        try (StoreSQL sql = new StoreSQL(new Config())) {
            sql.generate(1000000);
            String tmpDir = System.getProperty("java.io.tmpdir");
            StoreXML xml = new StoreXML(new File(format("%s/MainTestXML.xml", tmpDir)));
            xml.save(sql.toList());
            ConvertXSQT convert = new ConvertXSQT();
            convert.convert(
                    new File(format("%s/MainTestXML.xml", tmpDir)),
                    new File(format("%s/MainTestNewXML.xml", tmpDir)),
                    new File(
                            Objects.requireNonNull(getClass().getClassLoader()
                                    .getResource("array/test/XSTL.xml")).getFile()
                    )
            );

            long workTime = ((System.currentTimeMillis() - startTime) / 1000);
            System.out.printf("Work Time is %s sec.%n", workTime);
            assertThat(workTime, lessThan(300L));
            Stream<String> stream = Files.lines(Paths.get(format("%s/MainTestNewXML.xml", tmpDir)));
            assertNotNull(stream);
        }
    }
}
