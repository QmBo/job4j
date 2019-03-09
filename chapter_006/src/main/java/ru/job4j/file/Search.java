package ru.job4j.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * Search
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 11.03.2019
 */
public class Search {
    /**
     * File capacity.
     */
    private List<File> fileList = new ArrayList<>(100);

    /**
     * Find files in dir/under dir.
     * @param parent main dir to searching.
     * @param exts extends.
     * @return list files.
     */
    public List<File> files(String parent, List<String> exts) {
        File file = new File(parent);
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                this.files(f.getPath(), exts);
            }
        } else {
            this.checkFile(file, exts);
        }
        return this.fileList;
    }

    /**
     * Searcher helper.
     * @param file file to check extends.
     * @param exts extends.
     */
    private void checkFile(File file, List<String> exts) {
        String name = file.getName();
        String[] splits = name.split("[.]");
        int last = splits.length - 1;
        for (String ext : exts) {
            if (ext.equals(splits[last])) {
                this.fileList.add(file);
                break;
            }
        }
    }
}
