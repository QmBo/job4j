package ru.job4j.array;

import org.junit.Test;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void whenAdd1000000ThenWorkNotTooLong() {
        long startTime = System.currentTimeMillis();
        try (StoreSQL sql = new StoreSQL(new Config())) {
            sql.generate(1000000);
            StoreXML xml = new StoreXML(new File("MainTestXML.xml"));
            xml.save(sql.toList());
            ConvertXSQT convert = new ConvertXSQT();
            try {
                convert.convert(
                        new File("MainTestXML.xml"),
                        new File("MainTestNewXML.xml"),
                        new File(getClass().getClassLoader().getResource("array/test/XSTL.xml").getFile())
                );
            } catch (IOException | TransformerException e) {
                e.printStackTrace();
            }
            long workTime = ((System.currentTimeMillis() - startTime) / 1000);
            System.out.printf("Work Time is %s sec.", workTime);
            assertThat(workTime, lessThan(300L));
        }
    }
}
