package com.shpp.p2p.cs.vholovin.assignment8;

import acm.graphics.GRect;
import java.awt.*;

/**
 * This class create a box of array.
 */
public class ExamBox implements ExamConstants {

    /** GRect box. */
    private final GRect rectBox;

    /**
     * Default construction which create GRect element with input parameters
     * and set him border color.
     *
     * @param posX position in X cord.
     * @param posY position in Y cord.
     * @param sizeBoxX size in X cord.
     * @param sizeBoxY size in Y cord.
     */
    public ExamBox(double posX, double posY, double sizeBoxX, double sizeBoxY) {
        rectBox = new GRect(posX, posY, sizeBoxX, sizeBoxY);
        rectBox.setColor(COLOR_BORDER_BOXES);
        rectBox.setFilled(true);
    }

    /**
     * Set default color and return GRect element.
     *
     * @return GRect element with default color.
     */
    public GRect getRect() {
        rectBox.setFillColor(COLOR_FILL_BOXES);
        return rectBox;
    }

    /**
     * Set custom color and return GRect element.
     *
     * @param newColor set new custom color.
     * @return GRect element with new custom color.
     */
    public GRect getRect(Color newColor) {
        rectBox.setFillColor(newColor);
        return rectBox;
    }
}
