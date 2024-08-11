package com.shpp.p2p.cs.vholovin.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This class draws a pyramid with a specified number of bricks at the base.
 * It works with a dynamically resizable of window.
 */
public class Assignment3Part4 extends WindowProgram {

    /* The width and height of each brick. */
    private static final double     BRICK_HEIGHT        = 40;
    private static final double     BRICK_WIDTH         = 60;

    /* The number of elements in the lower base of the pyramid. */
    private static final double     BRICK_IN_BASE       = 15;

    /* The number of spacing the distance between the bricks */
    private static final double     BRICK_SPACING       = 10;

    /* Constant for brick color. */
    private static final Color      BRICK_BODY_COLOR    = Color.ORANGE;
    private static final Color      BRICK_BORDER_COLOR  = Color.RED;

    /* Constant for delay. */
    private static final int        DELAY_MILLIS        = 100;

    /**
     * Data of windows size.
     */
    protected int windowWidth;
    protected int windowHeight;

    /**
     * For the eternal cycle.
     */
    protected boolean infinity;

    /**
     * Default constructors, which reads the input data : size of window and meaning for the eternal cycle.
     */
    public Assignment3Part4() {
        this.windowWidth = 0;
        this.windowHeight = 0;
        this.infinity = true;
    }

    /* This method checks the window sizes and updates the content. It has an eternal cycle.
     * The pause function is needed so that the cycle does not clog all processor resources.
     */
    public void run() {
        while (infinity) {
            if (checkWindowSize()) {
                removeAll();
                displayAllComponents();
            }
            pause(DELAY_MILLIS);
        }
    }

    /* This method checks size of window with the recorded screen size data.
     * If they different, method overwrites data and returns true.
     * Otherwise, it returns false.
     */
    boolean checkWindowSize() {
        if ((windowWidth != getWidth()) || (windowHeight != getHeight())) {
            windowWidth = getWidth();
            windowHeight = getHeight();
            return (true);
        }
        return (false);
    }

    /* This method draws pyramid from bricks. */
    void displayAllComponents() {
        for (int j = 0; j < BRICK_IN_BASE; j++) {

            double sizeArrayX = BRICK_WIDTH * (BRICK_IN_BASE - j) + BRICK_SPACING * (BRICK_IN_BASE - j - 1);
            double sizeArrayY = BRICK_HEIGHT * (1 + j) + BRICK_SPACING * j;

            for (int i = 0; i < BRICK_IN_BASE - j; i++) {

                double posX = getWidth() / 2.0 - sizeArrayX / 2 + (BRICK_WIDTH + BRICK_SPACING) * i;
                double posY = getHeight() - sizeArrayY;

                GRect r = new GRect(posX, posY, BRICK_WIDTH, BRICK_HEIGHT);
                setRectColor(r);
                add(r);
            }
        }
    }

    /* This method fills body and border of the object with the specified color.
     */
    void setRectColor(GRect element) {
        element.setColor(BRICK_BORDER_COLOR);
        element.setFilled(true);
        element.setFillColor(BRICK_BODY_COLOR);
    }
}