package com.shpp.p2p.cs.vholovin.assignment4;

import acm.graphics.GObject;
import acm.graphics.GRect;

import java.awt.*;

/**
 * This class defines the behavior of one game element - ball.
 * The positions of bricks determine by the constant on main class Breakout.
 */
public class BrickArrayElement {
    /* Defined array of colors. */
    public static final Color[] BRICK_COLOR_ARRAY =
            {Color.RED, Color.RED,
                    Color.ORANGE, Color.ORANGE,
                    Color.YELLOW, Color.YELLOW,
                    Color.GREEN, Color.GREEN,
                    Color.CYAN, Color.CYAN,};
    /* Default color for bricks which outside the array of colors. */
    public static final Color BRICK_BASIC_COLOR = Color.BLACK;

    /* Array size in width and height. */
    private final int countX, countY;

    /* Array graphical class GRect. */
    private final GRect[][] element;

    /**
     * Default constructor for writing default values, and set array graphic object - brick.
     *
     * @param rows - height array size;
     * @param perRow - width array size;
     */
    public BrickArrayElement(int rows, int perRow) {
        countY = rows;
        countX = perRow;

        element = new GRect[countY][countX];
    }

    /**
     * This method calculates the position of the brick and creates it.
     *
     * @param boardWidth - width of window;
     * @param brickWidth - width size of bricks;
     * @param brickHeight - height size of bricks;
     * @param brickSpacing - spacing between of bricks;
     * @param brickOffsetY - offset in Y cord for position array of bricks from topside;
     */
    public void setPosition(int boardWidth, int brickWidth, int brickHeight, int brickSpacing, int brickOffsetY) {

        int sizeArrayX = brickWidth * countX + brickSpacing * (countX - 1);

        for (int j = 0; j < countY; j++) {
            for (int i = 0; i < countX; i++) {

                double posX = boardWidth / 2.0 - sizeArrayX / 2.0 + (brickWidth + brickSpacing) * i;
                double posY = brickOffsetY + (brickHeight + brickSpacing) * j;

                element[j][i] = new GRect(posX, posY, brickWidth, brickHeight);
            }
        }
    }

    /**
     * Sets the color and fill of a specific brick.
     * If the input indexes beyond the size of the array for array defined colors,
     * then setting the base color for these indexes.
     *
     * @param indexY - index of brick in perRow;
     * @param indexX - index of brick in Row;
     * @return GObject for method ADD to display in window.
     */
    public GObject draw(int indexY, int indexX) {
        if (indexY < BRICK_COLOR_ARRAY.length) {
            element[indexY][indexX].setColor(BRICK_COLOR_ARRAY[indexY]);
        } else {
            element[indexY][indexX].setColor(BRICK_BASIC_COLOR);
        }
        element[indexY][indexX].setFilled(true);
        return element[indexY][indexX];
    }
}
