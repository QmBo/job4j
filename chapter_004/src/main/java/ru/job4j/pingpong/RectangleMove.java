package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;
/**
 * RectangleMove
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 21.08.2018
 */
public class RectangleMove implements Runnable {
    /**
     * Rectangle.
     */
    private final Rectangle rect;
    /**
     * Direction step.
     */
    private final int move;
    /**
     * Direction.
     */
    private final boolean moveRight;
    /**
     * Edge.
     */
    private final int limit;

    /**
     * Constructor.
     * @param rect rectangle.
     */
    public RectangleMove(Rectangle rect) {
        this(rect, true);
    }

    /**
     * Constructor.
     * @param rect rectangle.
     * @param moveRight move direction.
     */
    public RectangleMove(Rectangle rect, final boolean moveRight) {
        this.rect = rect;
        this.moveRight = moveRight;
        if (moveRight) {
            this.move = 1;
            this.limit = 300;
        } else {
            this.move = -1;
            this.limit = 0;
        }
    }

    /**
     * Action.
     */
    @Override
    public void run() {
        while (this.rect.getX() != limit) {
            this.rect.setX(this.rect.getX() + this.move);
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new Thread(new RectangleMove(this.rect, !this.moveRight)).start();
    }
}