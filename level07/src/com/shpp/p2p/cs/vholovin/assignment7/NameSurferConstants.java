package com.shpp.p2p.cs.vholovin.assignment7;

/*
 * File: NameSurferConstants.java
 * ------------------------------
 * This file declares several constants that are shared by the
 * different modules in the NameSurfer application.  Any class
 * that implements this interface can use these constants.
 */

import java.awt.*;

public interface NameSurferConstants {

    /** The width of the application window. */
    public static final int APPLICATION_WIDTH = 800;

    /** The height of the application window. */
    public static final int APPLICATION_HEIGHT = 600;

    /** The name of the file containing the data. */
    public static final String NAMES_DATA_FILE = "names-data.txt";

    /** The first decade in the database. */
    public static final int START_DECADE = 1900;

    /** The number of decades. */
    public static final int NDECADES = 12;

    /** The number of years in decade. */
    public static final int DECADE_YEARS = 10;

    /** The maximum rank in the database. */
    public static final int MAX_RANK = 1000;

    /** The number of pixels to reserve at the top and bottom. */
    public static final int GRAPH_MARGIN_SIZE = 20;

    /** The number of pixels to offset at the left. */
    public static final int GRAPH_OFFSET_X = 2;

    /** Color of grid. */
    public static final Color GRID_COLOR = Color.BLACK;

    /** Array colors of graph. */
    public static final Color[] GRAPH_COLOR = {Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK};

    /** Size of text field. */
    public static final int NUM_COLUMNS_TEXT_FIELD = 20;

    /** Constants for texts: font type, offset position, and font size for graphs and grid. */
    public static final String FONT = "Montserrat";
    public static final int FONT_OFFSET_X = 2;
    public static final int FONT_OFFSET_Y = -2;
    public static final int FONT_GRAPH_SIZE = 10;
    public static final int FONT_GRID_SIZE = 15;
}