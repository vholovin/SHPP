package com.shpp.p2p.cs.vholovin.assignment3;

import acm.graphics.GRect;

import java.awt.*;
import java.util.Random;

/**
 * this class for Assignment3 Part6.
 * Here are writing the main parameters and methods for the ball.
 */
public class Ball {

    /* Size of ball. */
    private final double sizeX;
    private final double sizeY;

    /* Direction of move ball. */
    private double directionX;
    private double directionY;

    /* Main graph GRect class. */
    public final GRect element;

    /**
     * Default constructors, which set default parameters:
     * - create GRect with defined parameters.
     * - set sizes.
     * - set direction : Random works between -1 and 1.
     */
    public Ball(double width, double height) {
        element = new GRect(width, height);

        sizeX = width;
        sizeY = height;

        Random randomGenerate = new Random();
        int[] side = {-1, 1};

        directionX = side[randomGenerate.nextInt(2)];
        directionY = side[randomGenerate.nextInt(2)];
    }

    /* This method set speed of object. */
    public void setSpeed(double speed) {
        directionX *= speed;
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

    /* This method move the object. */
    public void moveObject() {
        element.move(directionX, directionY);
    }

    /* This method check to collision on border.
     * Calculate position is necessary to warn climb behind the texture.
     * If it climbs in position, we change the direction of ball.
     */
    public void collisionBorder(double windowWidth, double windowHeight, double borderSize) {
        if ((element.getX() < borderSize) || ((element.getX() + sizeX) > (windowWidth - borderSize)))
            directionX *= -1;
        if ((element.getY() < borderSize) || ((element.getY() + sizeY) > (windowHeight - borderSize)))
            directionY *= -1;
    }

    /* This method check to collision on Platform #0.
     * If it is collision, we change the direction of ball.
     */
    public void collisionPlatformPlayer0(double platformPosX, double platformSizeX) {
        if ((element.getX() < (platformPosX + platformSizeX / 2))) {
            directionX *= -1;
        }
    }

    /* This method check to collision on Platform #1.
     * If it is collision, we change the direction of ball.
     */
    public void collisionPlatformPlayer1(double platformPosX, double platformSizeX) {
        if ((element.getX() + sizeX) > (platformPosX - platformSizeX / 2)) {
            directionX *= -1;
        }
    }

    /* This method return the middle from position X. */
    public double getPositionX() {
        return (element.getX() + sizeX / 2);
    }

    /* This method return the middle from position Y. */
    public double getPositionY() {
        return (element.getY() + sizeY / 2);
    }
}