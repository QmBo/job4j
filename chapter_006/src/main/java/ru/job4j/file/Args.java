package ru.job4j.file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
/**
 * Args
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 14.03.2019
 */
public class Args {
    /**
     * Arguments capacity.
     */
    private String directory;
    private String output;
    private String excule;

    /**
     * Constructor.
     * @param args input arguments.
     */
    public Args(String[] args) {
        this.validate(args);
    }

    /**
     * Validator.
     * @param args input arguments.
     * @throws IllegalStateException if non correct numbers of arguments or non correct arguments.
     */
    private void validate(String[] args) throws IllegalStateException {
        if (args.length != 6) {
            throw new IllegalStateException(String.format("Non correct numbers of arguments. %s", args.length));
        }
        if (Arrays.stream(args).noneMatch("-d"::equals) || Arrays.stream(args).noneMatch("-e"::equals)
                || Arrays.stream(args).noneMatch("-o"::equals)) {
            throw new IllegalStateException("Non correct arguments. %s");
        }
        ArrayList<String> argsList = Arrays.stream(args).collect(Collectors.toCollection(ArrayList::new));
        this.directory = argsList.get(argsList.indexOf("-d") + 1);
        this.excule = argsList.get(argsList.indexOf("-e") + 1);
        this.output = argsList.get(argsList.indexOf("-o") + 1);
    }

    /**
     * Directory path getter.
     * @return path.
     */
    public String directory() {
        return this.directory;
    }

    /**
     * Extend getter.
     * @return extend.
     */
    public String excule() {
        String[] splits = this.excule.split("[.]");
        return splits[splits.length - 1];
    }

    /**
     * Output fail getter.
     * @return file name.
     */
    public String output() {
        return this.output;
    }
}
