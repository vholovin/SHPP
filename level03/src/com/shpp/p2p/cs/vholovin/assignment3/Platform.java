package com.shpp.p2p.cs.vholovin.assignment3;

import acm.graphics.GRect;

import java.awt.*;
import java.util.Random;

/**
 * this class for Assignment3 Part6.
 * Here are writing the main parameters and methods for the platform.
 */
public class Platform {

    /* Size of platform. */
    private final double sizeX;
    private final double sizeY;

    /* Direction of move platform. */
    private double directionY;

    /* These boolean values determine when platform can move:
     * isMoveBorder - determines the movement within the limits of the extreme positions of the borders.
     * isMoveTarget - determines the movement within the central positions of the ball.
     */
    private static boolean isMoveBorder;
    private static boolean isMoveTarget;

    /* Main graph GRect class. */
    public final GRect element;

    /**
     * Default constructors, which set default parameters:
     * - create GRect with defined parameters.
     * - set sizes.
     * - set direction in only Y axes : Random works between -1 and 1.
     * - movements are allowed.
     */
    public Platform(double width, double height) {
        element = new GRect(width, height);

        sizeX = width;
        sizeY = height;

        Random randomGenerate = new Random();
        int[] side = {-1, 1};
        directionY = side[randomGenerate.nextInt(2)];

        isMoveBorder = true;
        isMoveTarget = true;
    }

    /* This method set speed of object. */
    public void setSpeed(double speed) {
        directionY *= speed;
    }

    /* This method set position of object. */
    public void setObjectPosition(double x, double y) {
        element.setLocation(x - sizeX / 2, y - sizeY / 2);
    }

    /* This method set color of object. */
    public void setObjectColor(Color borderColor, Color bodyColor) {
        element.setColor(borderColor);
        element.setFilled(true);
        element.setFillColor(bodyColor);
    }

    /* This method move the object.
     * isMoveBorder - determines the movement within the limits of the extreme positions of the borders.
     * isMoveTarget - determines the movement within the central positions of the ball.
     */
    public void moveObject() {
        if (isMoveBorder && isMoveTarget) {
            element.move(0, directionY);
        }
    }

    /* This method follow the position of ball and change the direction of platform.
     * We stop move platform when difference position of platform and position of ball are in given limits.
     */
    public void followTarget(double targetPosY) {
        double difference = targetPosY - (element.getY() + sizeY / 2);
        isMoveTarget = !(Math.abs(difference) < (sizeY / 2));

        if ((difference < 0) && (directionY > 1)) {
            directionY *= -1;
        } else if ((difference > 0) && (directionY < 1)) {
            directionY *= -1;
        }
    }

    /* This method check to collision on border.
     * Calculate next position is necessary to warn climb behind the texture.
     * If it climbs in next position, we stop move platform and change the direction of ball.
     */
    public void collisionBorder(double windowHeight, double borderSize) {
        double nextPosY = element.getY() + directionY;
        isMoveBorder = (!(nextPosY < borderSize)) && (!((nextPosY + sizeY) > (windowHeight - borderSize)));
    }

    /* This method return the middle from position X. */
    public double getPositionX() {
        return (element.getX() + sizeX / 2);
    }

    /* This method return the size X. */
    public double getSizeX() {
        return sizeX;
    }
}