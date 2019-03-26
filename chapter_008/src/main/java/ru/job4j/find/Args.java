package ru.job4j.find;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
/**
 * Args
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 25.03.2019
 */
public class Args {
    /**
     * Arguments capacity.
     */
    private String directory;
    private String output;
    private String name;
    private boolean mask = false;

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
        this.check(args);
        ArrayList<String> argsList = Arrays.stream(args).collect(Collectors.toCollection(ArrayList::new));
        this.directory = argsList.get(argsList.indexOf("-d") + 1);
        this.name = argsList.get(argsList.indexOf("-n") + 1);
        this.output = argsList.get(argsList.indexOf("-o") + 1);
        if (argsList.contains("-m")) {
            this.mask = true;
        }
    }

    /**
     * Check arguments.
     * @param args arguments
     */
    private void check(String[] args) {
        if (args.length != 7) {
            throw new IllegalStateException(String.format("Non correct numbers of arguments. %s", args.length));
        }
        if (Arrays.stream(args).noneMatch("-d"::equals) || Arrays.stream(args).noneMatch("-n"::equals)
                || Arrays.stream(args).noneMatch("-o"::equals)) {
            throw new IllegalStateException("Non correct arguments.");
        }
        if (Arrays.stream(args).noneMatch("-m"::equals)) {
            if (Arrays.stream(args).noneMatch("-f"::equals)) {
                throw new IllegalStateException("Mask of fool incorrect.");
            }
        }
    }

    /**
     * Directory path getter.
     * @return path.
     */
    public String directory() {
        return this.directory;
    }

    /**
     * Name getter.
     * @return extend.
     */
    public String name() {
        return this.name;
    }

    /**
     * Output fail getter.
     * @return file name.
     */
    public String output() {
        return this.output;
    }

    /**
     * Mask getter.
     * @return is it mask.
     */
    public boolean isMask() {
        return this.mask;
    }
}
