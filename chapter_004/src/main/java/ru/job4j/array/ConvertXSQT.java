package ru.job4j.array;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import static java.nio.file.Files.readAllBytes;
/**
 * ConvertXSQT
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 18.01.2019
 */
public class ConvertXSQT {
    /**
     * Converter of types XML.
     * @param source source file.
     * @param dest file destination.
     * @param scheme scheme of convert.
     * @throws IOException IOException.
     * @throws TransformerException TransformerException.
     */
    public void convert(File source, File dest, File scheme) throws IOException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(
                new StreamSource(
                        new ByteArrayInputStream(
                                readAllBytes(scheme.toPath())
                        )
                )
        );
        transformer.transform(
                new StreamSource(
                        new ByteArrayInputStream(
                                readAllBytes(source.toPath())
                        )
                ),
                new StreamResult(dest)
        );
    }
}
