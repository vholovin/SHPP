package com.shpp.p2p.cs.vholovin.assignment8;

import java.awt.*;

/**
 * This class causes the switch to run at a different index.
 */
public class ExamSwitch implements ExamConstants {

    /** Position of switcher. */
    private int index;

    /** Direction to move. */
    private boolean directionUp;

    /** Custom color for switcher. */
    private final Color customColor;

    /**
     * Default construction for switcher
     *
     * @param newIndex input start position;
     * @param newDirection input start direction;
     * @param newColor input custom color;
     */
    public ExamSwitch(int newIndex, boolean newDirection, Color newColor) {
        index = newIndex;
        directionUp = newDirection;
        customColor = newColor;
    }

    /**
     * This method increment or decrement index depending on direction to move.
     * Limited to the size of array.
     *
     */
    public void updateIndex() {
        if (directionUp) {
            index++;
            if (index > NUM_BOXES - 1) {
                index = 0;
            }
        } else {
            index--;
            if (index < 0) {
                index = NUM_BOXES - 1;
            }
        }
    }

    /**
     * Return index for array list of boxes.
     *
     * @return index in boxes array.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Return custom color of box.
     *
     * @return custom color.
     */
    public Color getColor() {
        return customColor;
    }

    /**
     * Return true when direction to decrement in index
     * and return false when direction to increment in index.
     *
     * @return boolean direction Up.
     */
    public boolean getDirectionUp() {return directionUp;}

    /**
     * Change direction of move another box.
     */
    public void changeDirection() {
        directionUp = !directionUp;
    }
}
