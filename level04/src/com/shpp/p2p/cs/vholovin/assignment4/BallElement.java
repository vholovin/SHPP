package com.shpp.p2p.cs.vholovin.assignment4;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This class defines the behavior of one game element - the ball.
 * This class calculates the direction of the ball movement depending on collisions.
 */
public class BallElement extends WindowProgram {

    /* Constant for speed of ball. */
    private static final int BALL_SPEED = 10;

    /* Constant for color of ball. */
    private static final Color BALL_COLOR = Color.BLACK;

    /* Constant for the statistical error of one pixel for post-collision displacement. */
    private static final double STAT_ERROR = 0.1;

    /* Direction of move ball. */
    private double vx, vy;

    /* Size of ball. */
    private final int radius;

    /* Graphical class GOval. */
    private final GOval element;

    /**
     * Default constructor for writing default values, and set graphic object - ball.
     *
     * @param radius - size of ball;
     */
    public BallElement(int radius) {
        element = new GOval(radius * 2, radius * 2);
        this.radius = radius;
    }

    /**
     * Set position of ball.
     *
     * @param posX - position in X cord;
     * @param posY - position in Y cord;
     */
    public void setPosition(double posX, double posY) {
        element.setLocation(posX, posY);
    }

    /**
     * Set direction of ball. In this method directs ball to random downside with same speed.
     */
    public void setDirection() {
        vy = BALL_SPEED;
        RandomGenerator rGen = RandomGenerator.getInstance();
        vx = rGen.nextDouble(1.0, BALL_SPEED);
        if (rGen.nextBoolean(0.5))
            vx = -vx;
    }

    /**
     * Set direction of ball. In this method directs ball to determined side with same speed.
     *
     * @param newVX - determines direction vector on X cord;
     * @param newVY - determines direction vector on X cord;
     */
    public void setDirection(double newVX, double newVY) {
        // Normalize the direction vector and multiply with BALL_SPEED
        double length = Math.sqrt(newVX * newVX + newVY * newVY);
        vx = BALL_SPEED * (newVX / length);
        vy = BALL_SPEED * (newVY / length);
    }

    /**
     * Check collision with border in top, left and right sides.
     *
     * @param gameWidth - to determine the position of the right wall;
     * @return TRUE - if there was a collision, and FALSE - if there was not a collision;
     */
    public boolean checkCollisionBorder(int gameWidth) {
        // Top side
        if (posY() < 0) {
            setDirection(vx, Math.abs(vy));
            return true;
        }
        // Left sides
        if (posX() < 0) {
            setDirection(Math.abs(vx), vy);
            return true;
        }
        // Right sides
        if (posX() + 2 * radius > gameWidth) {
            setDirection(-Math.abs(vx), vy);
            return true;
        }
        return false;
    }

    /**
     * Check collision with border in bottom side.
     *
     * @param gameHeight - to determine the position of the bottom wall;
     * @return TRUE - if there was a collision, and FALSE - if there was not a collision;
     */
    public boolean checkBottomBorder(int gameHeight) {
        return (posY() + 2 * radius > gameHeight);
    }

    /**
     * This method checks the collision of the ball with the object.
     * Collision is checked by dipping the ball into the object and comparing their sizes.
     *
     * @param collider - the object with which to check whether there is a collision;
     * @return TRUE - if there was a collision, and FALSE - if there was not a collision;
     */
    public boolean onCollision(GObject collider) {

        // Calculate sizeX and sizeY dip ball to object
        double sizeX = calculateSizeX(collider);
        double sizeY = calculateSizeY(collider);

        // Center of the ball x and y coordinates
        double ballCenterPosX = posX() + radius;
        double ballCenterPosY = posY() + radius;

        // Center of the collider x and y coordinates
        double colliderCenterPosX = collider.getX() + collider.getWidth() / 2.0;
        double colliderCenterPosY = collider.getY() + collider.getHeight() / 2.0;

        // The origin (0.0) is in the top-left corner of the screen
        // Set collision response
        if (sizeX > sizeY) {
            if (ballCenterPosY > colliderCenterPosY) {
                // Bottom
                setPosition(posX(), posY() + sizeY + STAT_ERROR); // Move out of collision
                responseBallCollider(3); // Bottom
            } else {
                // Top
                setPosition(posX(), posY() - sizeY + STAT_ERROR); // Move out of collision
                responseBallCollider(1); // Top
            }
        } else {
            if (ballCenterPosX < colliderCenterPosX) {
                // Left
                setPosition(posX() - sizeX + STAT_ERROR, posY()); // Move out of collision
                responseBallCollider(0); // Left
            } else {
                // Right
                setPosition(posX() + sizeX + STAT_ERROR, posY()); // Move out of collision
                responseBallCollider(2); // Right
            }
        }
        return true;
    }

    /* Dipping the ball into the object in X cord: when minX - left diving point, maxX - right diving point. */
    double calculateSizeX(GObject collider) {
        double minX;
        if (collider.getX() > posX()) {
            minX = collider.getX();
        } else {
            minX = posX();
        }

        double maxX;
        if (collider.getX() + collider.getWidth() < posX() + 2 * radius) {
            maxX = collider.getX() + collider.getWidth();
        } else {
            maxX = posX() + 2 * radius;
        }

        return (maxX - minX);
    }

    /* Dipping the ball into the object in Y cord: when minY - up diving point, maxY - down diving point. */
    double calculateSizeY(GObject collider) {
        double minY;
        if (collider.getY() > posY()) {
            minY = collider.getY();
        } else {
            minY = posY();
        }

        double maxY;
        if (collider.getY() + collider.getHeight() < posY() + 2 * radius) {
            maxY = collider.getY() + collider.getHeight();
        } else {
            maxY = posY() + 2 * radius;
        }
        return (maxY - minY);
    }

    /* Set the correct parameters for the direction of the ball after collision. */
    void responseBallCollider(int dirIndex) {
        // direction of index 0: Left, 1: Top, 2: Right, 3: Bottom

        // Direction factors
        int dirX = 1;
        int dirY = 1;

        if (vx > 0) {
            // Ball is moving in the positive x direction
            if (vy > 0) {
                // Ball is moving in the positive y direction
                // +1 +1
                if (dirIndex == 0 || dirIndex == 3) {
                    dirX = -1;
                } else {
                    dirY = -1;
                }
            } else if (vy < 0) {
                // Ball is moving in the negative y direction
                // +1 -1
                if (dirIndex == 0 || dirIndex == 1) {
                    dirX = -1;
                } else {
                    dirY = -1;
                }
            }
        } else if (vx < 0) {
            // Ball is moving in the negative x direction
            if (vy > 0) {
                // Ball is moving in the positive y direction
                // -1 +1
                if (dirIndex == 2 || dirIndex == 3) {
                    dirX = -1;
                } else {
                    dirY = -1;
                }
            } else if (vy < 0) {
                // Ball is moving in the negative y direction
                // -1 -1
                if (dirIndex == 1 || dirIndex == 2) {
                    dirX = -1;
                } else {
                    dirY = -1;
                }
            }
        }
        // Set the new direction of the ball, by multiplying the old direction
        // with the determined direction factors
        setDirection(dirX * vx, dirY * vy);
    }

    /**
     * Set color and fill for ball.
     *
     * @return GObject for method ADD to display in window.
     */
    public GObject draw() {
        element.setColor(BALL_COLOR);
        element.setFilled(true);
        return element;
    }

    /**
     * Move tha ball.
     *
     * @param stepVX move in X cord;
     * @param stepVY move in Y cord;
     */
    public void move(double stepVX, double stepVY) {
        element.move(stepVX, stepVY);
    }

    /**
     * @return position in X cord.
     */
    public int posX() {
        return (int) (element.getX());
    }

    /**
     * @return position in X cord.
     */
    public int posY() {
        return (int) (element.getY());
    }

    /**
     * @return size of ball.
     */
    public int radius() {
        return radius;
    }

    /**
     * @return direction in X cord.
     */
    public double dirX() {
        return vx;
    }

    /**
     * @return direction in Y cord.
     */
    public double dirY() {
        return vy;
    }
}