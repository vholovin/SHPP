package com.shpp.p2p.cs.vholovin.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This class draws 2 dog paws on the windows screen.
 */
public class Assignment2Part3 extends WindowProgram {
    /* Constants controlling the relative positions of the
     * three toes to the upper-left corner of the paw print.
     *
     * (Yes, I know that actual paw prints have four toes.
     * Just pretend it's a cartoon animal. ^_^)
     */
    private static final double     FIRST_TOE_OFFSET_X      = 0;
    private static final double     FIRST_TOE_OFFSET_Y      = 20;
    private static final double     SECOND_TOE_OFFSET_X     = 30;
    private static final double     SECOND_TOE_OFFSET_Y     = 0;
    private static final double     THIRD_TOE_OFFSET_X      = 60;
    private static final double     THIRD_TOE_OFFSET_Y      = 20;

    /* The position of the heel relative to the upper-left
     * corner of the paw print.
     */
    private static final double     HEEL_OFFSET_X           = 20;
    private static final double     HEEL_OFFSET_Y           = 40;

    /* Each toe is an oval with this width and height. */
    private static final double     TOE_WIDTH               = 20;
    private static final double     TOE_HEIGHT              = 30;

    /* The heel is an oval with this width and height. */
    private static final double     HEEL_WIDTH              = 40;
    private static final double     HEEL_HEIGHT             = 60;

    /* The default width and height of the window. These constants will tell Java to
     * create a window whose size is *approximately* given by these dimensions. You should
     * not directly use these constants in your program; instead, use getWidth() and
     * getHeight(), which return the *exact* width and height of the window.
     */
    public static final int         APPLICATION_WIDTH       = 270;
    public static final int         APPLICATION_HEIGHT      = 220;

    /* Constant for oval color. */
    public static final Color       OVAL_COLOR              = Color.BLACK;

    /* This method draws two paw print.
     */
    public void run() {
        drawPawprint(20, 20);
        drawPawprint(180, 70);
    }

    /**
     * Draws a paw print. The parameters should specify the upper-left corner of the
     * bounding box containing that paw print.
     *
     * @param x The x coordinate of the upper-left corner of the bounding box for the paw print.
     * @param y The y coordinate of the upper-left corner of the bounding box for the paw print.
     */
    private void drawPawprint(double x, double y) {

        GOval firstToe = new GOval(x + FIRST_TOE_OFFSET_X, y + FIRST_TOE_OFFSET_Y, TOE_WIDTH, TOE_HEIGHT);
        setOval(firstToe);

        GOval secondToe = new GOval(x + SECOND_TOE_OFFSET_X, y + SECOND_TOE_OFFSET_Y, TOE_WIDTH, TOE_HEIGHT);
        setOval(secondToe);

        GOval thirdToe = new GOval(x + THIRD_TOE_OFFSET_X, y + THIRD_TOE_OFFSET_Y, TOE_WIDTH, TOE_HEIGHT);
        setOval(thirdToe);

        GOval heel = new GOval(x + HEEL_OFFSET_X, y + HEEL_OFFSET_Y, HEEL_WIDTH, HEEL_HEIGHT);
        setOval(heel);
    }

    /* This method fills body and border of the object with the constant color.
     * And add object to window.
     */
    void setOval(GOval element) {
        element.setColor(OVAL_COLOR);
        element.setFilled(true);
        element.setFillColor(OVAL_COLOR);
        add(element);
    }
}