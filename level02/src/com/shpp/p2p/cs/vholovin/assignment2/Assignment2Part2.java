package com.shpp.p2p.cs.vholovin.assignment2;

import acm.graphics.GFillable;
import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This class draws 4 black circles at the corners of the window and one white rectangle in the center of the window.
 * The size of the circles given by the constant diameter.
 * The size of the rectangle determined by the center points of the circles.
 */
public class Assignment2Part2 extends WindowProgram {

    /* The default width and height of the window. */
    public static final int     APPLICATION_WIDTH   = 300;
    public static final int     APPLICATION_HEIGHT  = 300;

    /* Constant for size of circle. */
    public static final double  DIAMETER            = 100;

    /* Constant for offset position compensation.
     * Which arises as a result of drawing from the starting coordinate 1 instead of 0.
     */
    public static final double  BORDER_SIZE         = 1;

    /* Constant for oval color. */
    public static final Color   OVAL_COLOR          = Color.BLACK;

    /* Constant for rectangle color. */
    public static final Color   RECT_COLOR          = Color.WHITE;

    /* This method draws ovals and the rectangle.
     */
    public void run() {
        drawOval();
        drawRect();
    }

    /* This method draws ovals.
     */
    void drawOval() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                GOval o = new GOval((getWidth() - DIAMETER - BORDER_SIZE) * i,
                                    (getHeight() - DIAMETER - BORDER_SIZE) * j,
                                    DIAMETER, DIAMETER);
                setBodyColor(o, OVAL_COLOR);
                add(o);
            }
        }
    }

    /* This method draws the rectangle.
     */
    void drawRect() {
        GRect r = new GRect(DIAMETER / 2.0,
                DIAMETER / 2.0,
                getWidth() - DIAMETER - BORDER_SIZE,
                getHeight() - DIAMETER - BORDER_SIZE);
        r.setColor(RECT_COLOR);                             //set border color
        setBodyColor(r, RECT_COLOR);
        add(r);
    }

    /* This method fills body of the object with the specified color.
     */
    void setBodyColor(GFillable obj, Color color) {
        obj.setFilled(true);
        obj.setFillColor(color);
    }
}