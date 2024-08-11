package com.shpp.p2p.cs.vholovin.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This class draws an array of black boxes of specified sizes with specified offsets.
 */
public class Assignment2Part5 extends WindowProgram {

    /* The number of rows and columns in the grid, respectively. */
    private static final int        NUM_ROWS        = 5;
    private static final int        NUM_COLS        = 6;

    /* The width and height of each box. */
    private static final double     BOX_SIZE        = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double     BOX_SPACING     = 10;

    /* Constant for box color. */
    private static final Color      BOX_COLOR       = Color.BLACK;

    /* This method calls the method of draw array of boxes. */
    public void run() {
        displayAllComponents();
    }

    /* This method draws array of boxes. */
    void displayAllComponents() {
        double sizeArrayX = (BOX_SIZE * NUM_COLS + BOX_SPACING * (NUM_COLS - 1));
        double sizeArrayY = (BOX_SIZE * NUM_ROWS + BOX_SPACING * (NUM_ROWS - 1));

        for (int i = 0; i < NUM_COLS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                GRect r = new GRect(getWidth() / 2.0 - sizeArrayX / 2.0 + (BOX_SIZE + BOX_SPACING) * i,
                                    getHeight() / 2.0 - sizeArrayY / 2.0 + (BOX_SIZE + BOX_SPACING) * j,
                                    BOX_SIZE, BOX_SIZE);
                setRectColor(r);
                add(r);
            }
        }
    }

    /* This method fills body and border of the object with the specified color.
     */
    void setRectColor(GRect element) {
        element.setColor(BOX_COLOR);
        element.setFilled(true);
        element.setFillColor(BOX_COLOR);
    }
}