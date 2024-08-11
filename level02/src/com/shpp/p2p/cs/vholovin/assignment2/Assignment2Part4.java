package com.shpp.p2p.cs.vholovin.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This class draws a flag and signs it.
 * The counts, positions, and colors of the stripes are specified as constants.
 */
public class Assignment2Part4 extends WindowProgram
{
    /* The default width and height of the window. */
    public static final int     APPLICATION_WIDTH   = 500;
    public static final int     APPLICATION_HEIGHT  = 400;

    /* Constants for type of flag. */
    public static final int     NUMBER_OF_RECT      = 3;
    public static final Color[] RECT_COLOR_ARRAY    = {Color.BLACK, Color.YELLOW, Color.RED};
    public static final boolean IS_VERTICAL_RECT    = true;

    /* Constants for size of flag and border color. */
    public static final double  SIZE_X              = 300;
    public static final double  SIZE_Y              = 250;
    public static final Color   RECT_BORDER_COLOR   = Color.WHITE;

    /* Constants for text. */
    public static final String  NAME                = "Belgium";
    public static final String  FONT                = "Verdana";
    public static final int     FONT_SIZE           = 30;
    public static final Color   FONT_COLOR          = Color.BLACK;

    /* Constants for offset position compensation from the edge of the window for text in left and up. */
    public static final double  OFFSET_X         = 5;
    public static final double  OFFSET_Y         = 0;

    /* This method draws Flag and Text.
     */
    public void run() {
        setRect();
        setText();
    }

    /* This method draws rectangles - their numbers and orientation.
     */
    void setRect() {
        for (int i = 0; i < NUMBER_OF_RECT; i++) {

            if (IS_VERTICAL_RECT) {
                verticalRect(i);
            } else {
                horizontalRect(i);
            }
        }
    }

    /* This method draws vertical rectangle - size, position and color.
     */
    void verticalRect(int i)
    {
        GRect r = new GRect(getWidth() / 2.0 - SIZE_X / 2.0 + (SIZE_X / NUMBER_OF_RECT) * i,
                getHeight() / 2.0 - SIZE_Y / 2.0,
                SIZE_X / NUMBER_OF_RECT,
                SIZE_Y);

        setRectColor(r, RECT_COLOR_ARRAY[i]);
        add(r);
    }

    /* This method draws horizontal rectangle - size, position and color.
     */
    void horizontalRect(int i)
    {
        GRect r = new GRect(getWidth() / 2.0 - SIZE_X / 2.0,
                getHeight() / 2.0 - SIZE_Y / 2.0 + (SIZE_Y / NUMBER_OF_RECT) * i,
                SIZE_X,
                SIZE_Y / NUMBER_OF_RECT);

        setRectColor(r, RECT_COLOR_ARRAY[i]);
        add(r);
    }

    /* This method fills body of the object with the specified color.
     */
    void setRectColor(GRect element, Color bodyColor) {
        element.setColor(RECT_BORDER_COLOR);
        element.setFilled(true);
        element.setFillColor(bodyColor);
    }

    /* This method draws text with some name, font type and size in lower left corner.
     */
    void setText()
    {
        GLabel l = new GLabel("Flag of " + NAME);
        l.setFont(FONT + "-" + FONT_SIZE);
        l.setColor(FONT_COLOR);
        l.setLocation(getWidth() - l.getWidth() - OFFSET_X, getHeight() - l.getDescent() - OFFSET_Y);
        add(l);
    }
}