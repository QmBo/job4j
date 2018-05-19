package ru.job4j.condition;

/**
 * Point in coordinate system.
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version $Id$
 * @since 0.1
 */
public class Point {
    /**
     * Position on X.
     */
    private int x;
    /**
     * Position on X.
     */
    private int y;

    /**
     * Constructor.
     * @param x x.
     * @param y y.
     */
    public  Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance to another point.
     * @param that another point.
     * @return distance.
     */
    public double distanceTo(Point that) {
        return Math.sqrt(
                Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2)
        );
    }
}