package com.shpp.p2p.cs.vholovin.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This class draws an array of black boxes of specified sizes with specified offsets.
 * It works with a dynamically resizable of window.
 */
public class Assignment2Part5ext extends WindowProgram  {

    /* The number of rows and columns in the grid, respectively. */
    private static final int        NUM_ROWS        = 5;
    private static final int        NUM_COLS        = 6;

    /* The width and height of each box. */
    private static final double     BOX_SIZE        = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double     BOX_SPACING     = 10;

    /* Constant for box color. */
    private static final Color      BOX_COLOR       = Color.BLACK;

    /* Constant for delay. */
    private static final int        DELAY_MILLIS    = 100;

    /** Data of windows size. */
    protected int                   windowWidth;
    protected int                   windowHeight;

    /** For the eternal cycle. */
    protected boolean               infinity;


    /**
     * Default constructors, which reads the input data : size of window and meaning for the eternal cycle.
     */
    public Assignment2Part5ext() {
        this.windowWidth = 0;
        this.windowHeight = 0;
        this.infinity = true;
    }

    /*
     * This method checks the window sizes and updates the content. It has an eternal cycle.
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
