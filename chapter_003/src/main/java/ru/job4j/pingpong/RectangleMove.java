package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;
/**
 * RectangleMove
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2018
 */
public class RectangleMove extends Thread {
    /**
     * Rectangle.
     */
    private final Rectangle rect;
    /**
     * Direction step.
     */
    private int move = 1;
    /**
     * Left edge.
     */
    private final int leftEdge;
    /**
     * Right edge.
     */
    private final int rightEdge;
    /**
     * Move direction.
     */
    private boolean moveRight = true;
    /**
     * Focus edge.
     */
    private int limit;

    /**
     * Constructor.
     * @param rect rectangle.
     */
    public RectangleMove(Rectangle rect) {
        this(rect, 0, 300);
    }

    /**
     * Constructor.
     * @param rect rectangle.
     * @param leftEdge left edge.
     * @param rightEdge right edge.
     */
    public RectangleMove(Rectangle rect, final int leftEdge, final int rightEdge) {
        this.rect = rect;
        this.leftEdge = leftEdge;
        this.rightEdge = rightEdge;
        this.limit = rightEdge;
    }

    /**
     * Action.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            this.rect.setX(this.rect.getX() + this.move);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
            if (this.rect.getX() == this.limit) {
                if (this.moveRight) {
                    this.limit = this.leftEdge;
                } else {
                    this.limit = this.rightEdge;
                }
                this.move = -this.move;
                this.moveRight = !this.moveRight;
            }
        }
    }
}