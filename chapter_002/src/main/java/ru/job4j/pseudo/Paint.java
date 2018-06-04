package ru.job4j.pseudo;
/**
 * Paint class.
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 04.06.2018
 */
public class Paint {
    /**
     * Paint shape.
     * @param shape implements of shape.
     */
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        new Paint().draw(new Triangle());
        new Paint().draw(new Square());
    }
}
