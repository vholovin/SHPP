package com.shpp.p2p.cs.vholovin.assignment8;

import java.awt.*;

/**
 * Constant.
 */
public interface ExamConstants {

    /** The default width and height of the window. */
    public static final int     APPLICATION_WIDTH   = 600;
    public static final int     APPLICATION_HEIGHT  = 600;

    /** For FPS and Pause for speed working program. */
    public static final int     FPS                 = 30;
    public static final double  PAUSE               = 1000.0 / FPS;

    /** Number of boxes in row and col in window. */
    public static final int     NUM_ROWS            = 10;
    public static final int     NUM_COLS            = 10;

    /** All boxes, size for array. */
    public static final int     NUM_BOXES = 2 * (NUM_ROWS - 1) + 2 * (NUM_COLS - 1);

    /** Default fill color and border color for boxes. */
    public static final Color   COLOR_FILL_BOXES    = Color.GREEN;
    public static final Color   COLOR_BORDER_BOXES  = Color.BLACK;

    /** Accuracy to trigger a collision with two switchers and one switcher with mouse. */
    public static final int     DIFF_INDEX_COLLISION_BOXES = 2;
    public static final int     DIFF_INDEX_COLLISION_MOUSE = 1;
}
