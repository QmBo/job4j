package ru.job4j.find;

import com.google.common.base.Joiner;
import ru.job4j.chat.ChatLogger;
import ru.job4j.file.Search;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class Finder {
    private final String name;
    private final String dir;
    private final boolean isMask;
    private final String output;
    private final static String LS = System.lineSeparator();

    public Finder(final String name, final String dir, final boolean isMask, final String output) {
        this.name = name;
        this.dir = dir;
        this.isMask = isMask;
        this.output = output;
    }

    public static void main(String[] args) {
        Args config = new Args(args);
        new Finder(
                config.name(),
                config.directory(),
                config.isMask(),
                config.output()
        ).start();
    }


    public void start() {
        List<File> files = new FilesFind(
                this.name,
                this.isMask,
                new Search().files(this.dir, Collections.emptyList(), false)
        ).find();
        ChatLogger logger = new ChatLogger(this.dir);
        logger.init(this.output);
        for (File file : files) {
            logger.write(Joiner.on(LS).join(file.toString(), ""));
        }
    }
}
