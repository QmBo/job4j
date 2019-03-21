package ru.job4j.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.lang.String.format;
/**
 * Packaging
 * run example:
 * java -jar pack.jar -d c:\project\job4j\ -e *.java -o project.zip
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 14.03.2019
 */
public class Packaging {
    private static final Logger LOG = LogManager.getLogger(Packaging.class);

    /**
     * Start point.
     * Arguments example: -d c:\project\job4j\ -e *.java -o project.zip .
     * @param args arguments -d -o -e  !!!
     */
    public static void main(String[] args) {
        new Packaging().start(args);
    }

    /**
     * Init method.
     * @param args arguments
     */
    public void start(String[] args) {
        Args in = new Args(args);
        try {
            this.pack(
                    new Search().files(
                            in.directory(), Collections.singletonList(in.excule()), false
                    ), in.directory(), in.output()
            );
        } catch (Exception e) {
            LOG.error("message", e);
        }
    }

    /**
     * Packaging method.
     * @param files files to zip.
     * @param dirPath root directory of files.
     * @param output name output file.
     * @return zip file.
     * @throws Exception if something wrong.
     */
    public File pack(List<File> files, String dirPath, String output) throws Exception {
        File result = new File(dirPath, output);
        if (!result.createNewFile()) {
            throw new IllegalStateException(format("File %s not create.", output));
        }
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(result))) {
            Collections.sort(files);
            for (File file : files) {
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    String name = new File(dirPath).toURI().relativize(file.toURI()).toString();
                    ZipEntry zipEntry = new ZipEntry(name);
                    zip.putNextEntry(zipEntry);
                    zip.write(fileInputStream.read());
                    zip.closeEntry();
                }
            }
        }
        return result;
    }
}
