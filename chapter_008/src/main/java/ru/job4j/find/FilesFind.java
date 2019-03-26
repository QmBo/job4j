package ru.job4j.find;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * FilesFind
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.03.2019
 */
public class FilesFind {
    /**
     * File name.
     */
    private final String name;
    /**
     * Is mask.
     */
    private final boolean isMask;
    /**
     * Files to check.
     */
    private final List<File> toCheck;

    /**
     * Constructor.
     * @param name name to find.
     * @param isMask is mask.
     * @param toCheck list to check.
     */
    public FilesFind(final String name, final boolean isMask, final List<File> toCheck) {
        this.name = name;
        this.isMask = isMask;
        this.toCheck = toCheck;
    }

    /**
     * Find files in list for name of mask and return list of files.
     * @return files list.
     */
    public List<File> find() {
        List<File> result = new ArrayList<>(100);
        if (this.isMask) {
            result = this.findByMask();
        } else {
            this.name.replace("*", "");
            for (File file : this.toCheck) {
                if (this.name.equals(file.getName())) {
                    result.add(file);
                }
            }
        }
        return result;
    }

    /**
     * Find helper. Find for mask.
     * @return files list.
     */
    private List<File> findByMask() {
        List<File> result = new ArrayList<>(100);
        String mask = this.name;
        if (mask.startsWith("*")) {
            mask = mask.replace("*", "");
            for (File file : this.toCheck) {
                if (file.getName().endsWith(mask)) {
                    result.add(file);
                }
            }
        } else if (mask.endsWith("*")) {
            mask = mask.replace("*", "");
            for (File file : this.toCheck) {
                if (file.getName().startsWith(mask)) {
                    result.add(file);
                }
            }
        }
        return result;
    }
}
