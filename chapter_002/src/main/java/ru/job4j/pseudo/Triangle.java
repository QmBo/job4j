package ru.job4j.pseudo;
/**
 * Triangle class.
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 04.06.2018
 */
public class Triangle implements Shape {
    /**
     * Return Triangle pseudo-graphic.
     * @return Triangle pseudo-graphic.
     */
    @Override
    public String draw() {
        StringBuilder result = new StringBuilder();
        result.append("    $     ");
        result.append(System.lineSeparator());
        result.append("   $  $   ");
        result.append(System.lineSeparator());
        result.append("  $    $  ");
        result.append(System.lineSeparator());
        result.append(" $      $ ");
        result.append(System.lineSeparator());
        result.append("$$$$$$$$$$");
        return result.toString();
    }
}
