package com.shpp.p2p.cs.vholovin.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This class builds a caterpillar from a specified number of segments and their offsets.
 */
public class Assignment2Part6 extends WindowProgram {

    /* The count and size of circles. */
    private static final int        DIAMETER            = 200;
    private static final int        OVAL_COUNT          = 6;

    /* The number of offset for emulation caterpillars. */
    private static final int        OFFSET_X            = 70;
    private static final int        OFFSET_Y            = 100;

    /* This constant specifies from which circles the vertical displacement will be start. */
    private static final boolean    IS_FIRST_OFFSET     = true;

    /* Constant for body and border color. */
    private static final Color      OVAL_BODY_COLOR     = Color.GREEN;
    private static final Color      OVAL_BORDER_COLOR   = Color.RED;

    /* This method builds a caterpillar. */
    public void run() {
        for (int i = 0; i < OVAL_COUNT; i++) {
            GOval o = new GOval((DIAMETER - OFFSET_X) * i,
                                ((i % 2) == 0) == IS_FIRST_OFFSET ? OFFSET_Y : 0,
                                DIAMETER, DIAMETER);
            setColor(o);
            add(o);
        }
    }

    /* This method fills body and border of the object with the specified color.
     */
    void setColor(GOval element) {
        element.setColor(OVAL_BORDER_COLOR);
        element.setFilled(true);
        element.setFillColor(OVAL_BODY_COLOR);
    }
}