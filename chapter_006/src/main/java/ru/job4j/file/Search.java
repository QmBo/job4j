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
     * @param include include extends.
     * @return list files.
     */
    public List<File> files(String parent, List<String> exts, boolean include) {
        File file = new File(parent);
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                this.files(f.getPath(), exts, include);
            }
        } else {
            this.checkFile(file, exts, include);
        }
        return this.fileList;
    }

    /**
     * Find files in dir/under dir.
     * @param parent main dir to searching.
     * @param exts extends.
     * @return list files.
     */
    public List<File> files(String parent, List<String> exts) {
        return this.files(parent, exts, true);
    }

    /**
     * Searcher helper.
     * @param file file to check extends.
     * @param exts extends.
     * @param include include extends.
     */
    private void checkFile(File file, List<String> exts, boolean include) {
        String name = file.getName();
        String[] splits = name.split("[.]");
        int last = splits.length - 1;
        boolean found = false;
        for (String ext : exts) {
            if (ext.equals(splits[last])) {
                if (include) {
                    this.fileList.add(file);
                }
                found = true;
                break;
            }
        }
        if (!include && !found) {
            this.fileList.add(file);
        }
    }
}
