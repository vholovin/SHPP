package com.shpp.p2p.cs.vholovin.assignment4;

import acm.graphics.GObject;
import acm.graphics.GRect;

import java.awt.*;

/**
 * This class defines the behavior of one game element - the paddle.
 * It catches and reflects the ball.
 * Its position determines the position of the mouse.
 */
public class PaddleElement {

    /* Constant for color of paddle. */
    private static final Color PADDLE_COLOR = Color.BLACK;

    /* Mouse position on the game window for the position of paddle. */
    private int mouseX;

    /* Parameter of window size for limits the range of movements for paddle. */
    private final int windowWidth;

    /* Size of paddle. */
    private final int width, height;

    /* Graphical class GRect. */
    private final GRect element;

    /**
     * Default constructor for writing default values, and create graphic paddle.
     *
     * @param width - width size of paddle;
     * @param height - height size of paddle;
     * @param windowWidth - width of window;
     */
    public PaddleElement(int width, int height, int windowWidth) {
        element = new GRect(width, height);
        this.width = width;
        this.height = height;
        this.windowWidth = windowWidth;
    }

    /**
     * Set middle position of paddle.
     *
     * @param posX - position in X cord;
     * @param posY - position in Y cord;
     * @param offsetY - offset in Y cord for position from downside;
     */
    public void setPosition(int posX, int posY, int offsetY) {
        element.setLocation(posX / 2.0 - width / 2.0, posY - height - offsetY);
    }

    /**
     * Set mouse position for to move the paddle.
     *
     * @param newMouseX - position in X cord of mouse;
     */
    public void setMouseX(int newMouseX) {
        mouseX = newMouseX;
    }

    /**
     * Set color and fill for paddle.
     *
     * @return GObject for method ADD to display in window.
     */
    public GObject draw() {
        element.setColor(PADDLE_COLOR);
        element.setFilled(true);
        return element;
    }

    /**
     * Moves the paddle within the set window size limits.
     * If the size of the paddle is larger than the size of the window - movement does not take place.
     */
    public void move() {
        if (width < windowWidth) {
            if (mouseX - width / 2.0 > 0 && mouseX + width / 2.0 < windowWidth)
                element.setLocation(mouseX - width / 2.0, element.getY());
        }
    }

    /**
     * This method returns the value of GRect class paddle.
     *
     * @return paddle graphic class GRect.
     */
    public GRect getElement() {
        return element;
    }
}