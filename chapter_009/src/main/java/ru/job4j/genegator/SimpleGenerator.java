package ru.job4j.genegator;

import com.google.common.base.Joiner;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * SimpleGenerator
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 25.07.2019
 */
public class SimpleGenerator {
    /**
     * Mask of keys.
     */
    private final static Pattern KEYS = Pattern.compile("\\$\\{.+?}");
    /**
     * Start mask.
     */
    private final static String START = "\\$\\{";
    /**
     * End mask.
     */
    private final static String END = "}";

    /**
     * Replace keys to strings from map.
     * @param template input line.
     * @param keys new strings.
     * @return string this replaced words.
     */
    public String generate(final String template, final Map<String, String> keys) {
        Matcher matcher = KEYS.matcher(template);
        Set<String> findKeys = new HashSet<>(100);
        while (matcher.find()) {
            String temp = template.substring(matcher.start(), matcher.end());
            findKeys.add(temp.substring(2, temp.length() - 1));
        }
        if (!findKeys.equals(keys.keySet())) {
            throw new IllegalStateException("Keys Exception");
        }
        String result = template;
        for (String s : findKeys) {
            String temp = Joiner.on(s).join(START, END);
            result = result.replaceAll(temp, keys.get(s));
        }
        return result;
    }
}
