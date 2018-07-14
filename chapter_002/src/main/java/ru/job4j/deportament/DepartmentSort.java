package ru.job4j.deportament;

import java.util.Arrays;
import java.util.Comparator;

import java.util.Set;
import java.util.TreeSet;
/**
 * DepartmentSort
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 13.07.2018
 */
public class DepartmentSort {

    /**
     * Sort input strings array low departments to high.
     * @param input array of departments names.
     * @return completed and sorted array of departments names.
     */
    public String[] sortUp(String[] input) {
        TreeSet<String> depSet = new TreeSet<>();
        depSet.addAll(Arrays.asList(input));
        return  this.checkDep(depSet);
    }

    /**
     * Sort input strings array high departments to low.
     * @param input array of departments names.
     * @return completed and sorted array of departments names.
     */
    public String[] sortDown(String[] input) {
        TreeSet<String> depSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int len1 = o1.length();
                int len2 = o2.length();
                int lim = Math.min(len1, len2);
                char[] v1 = o1.toCharArray();
                char[] v2 = o2.toCharArray();
                int k = 0;
                while (k < lim) {
                    char c1 = v1[k];
                    char c2 = v2[k];
                    if (c1 != c2) {
                        return c2 - c1;
                    }
                    k++;
                }
                return len1 - len2;
            }
        });
        depSet.addAll(Arrays.asList(input));
        return  this.checkDep(depSet);
    }

    /**
     * Helper. Complete array of departments names.
     * @param depSet departments names set.
     * @return completed and sorted array of departments names.
     */
    private String[] checkDep(TreeSet<String> depSet) {
        Set<String> tmp  = new TreeSet<>();
        for (String name : depSet) {
            if (name.contains("\\")) {
                String[] result = name.split("\\\\");
                tmp.add(result[0]);
                tmp.add(String.format("%s\\%s", result[0], result[1]));
            }
        }
        depSet.addAll(tmp);
        return depSet.toArray(new String[0]);
    }
}
