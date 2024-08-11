package com.shpp.p2p.cs.vholovin.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * This class shows the animation of a PING PONG game.
 * Left and right platforms are automated.
 */
public class Assignment3Part6 extends WindowProgram {

    /* The default width and height of the window. */
    public static final int         APPLICATION_WIDTH   = 1280;
    public static final int         APPLICATION_HEIGHT  = 740;

    /* Constant of parameters of ball: size, speed of move and color. */
    private static final double     BALL_SIZE           = 20;
    private static final double     BALL_SPEED          = 10;
    private static final Color      BALL_COLOR          = Color.WHITE;

    /* Constant of parameters of platform: sizes, speed of moves and color. */
    private static final double     PLATFORM_SIZE_X     = 20;
    private static final double     PLATFORM_SIZE_Y     = 60;
    private static final double     PLATFORM_SPEED_0    = 10;
    private static final double     PLATFORM_SPEED_1    = 10;
    private static final Color      PLATFORM_COLOR_0    = Color.WHITE;
    private static final Color      PLATFORM_COLOR_1    = Color.WHITE;

    /* Constant of parameters of border: size and color. */
    private static final double     BORDER_SIZE         = 20;
    private static final Color      BORDER_COLOR        = Color.WHITE;

    /* Constant of parameters of background color. */
    private static final Color      BACKGROUND_COLOR    = Color.PINK;

    /* Constant for delay, 60 is FPS. */
    private static final double     DELAY_MILLIS        = 1000.0 / 60;

    /* Constant for limit times work. */
    private static final long       TIME_FOR_WORK       = 5000;

    /* Constant for ignore timer. */
    private static final boolean    IS_INFINITY_WORK    = true;


    /* Parameter for check end time. */
    private final long              endTime;

    /* Class for ball. */
    private final Ball              ball;

    /* Class for two platform. */
    private final Platform[]        platform            = new Platform[2];

    /** Data of windows size. */
    protected int                   windowWidth;
    protected int                   windowHeight;

    /**
     * Default constructors, which set default parameters:
     * - set end time.
     * - size of window.
     * - set size and speed for ball.
     * - set sizes and speeds for two platforms.
     */
    public Assignment3Part6() {
        endTime = System.currentTimeMillis() + TIME_FOR_WORK;

        this.windowWidth = 0;
        this.windowHeight = 0;

        ball = new Ball(BALL_SIZE, BALL_SIZE);
        ball.setSpeed(BALL_SPEED);

        platform[0] = new Platform(PLATFORM_SIZE_X, PLATFORM_SIZE_Y);
        platform[0].setSpeed(PLATFORM_SPEED_0);
        platform[1] = new Platform(PLATFORM_SIZE_X, PLATFORM_SIZE_Y);
        platform[1].setSpeed(PLATFORM_SPEED_1);
    }

    /* This method checks the window sizes, updates and move content. It has an eternal cycle.
     * The cycle terminate by the end value of the timer if constant IS_INFINITY_WORK is true.
     * The pause function is needed so that the cycle does not clog all processor resources.
     * If you resize windows sizes - reset game.
     * Exit close the windows.
     */
    public void run() {
        while ((System.currentTimeMillis() < endTime) || IS_INFINITY_WORK) {
            if (checkWindowSize()) {
                removeAll();
                drawAllElements();
             }
            moveAllElements();
            pause(DELAY_MILLIS);
        }
        exit();
    }

    /* This method checks size of window with the recorded screen size data.
     * If they different, method overwrites data and returns true.
     * Otherwise, it returns false.
     */
    boolean checkWindowSize() {
        if ((windowWidth != getWidth()) || (windowHeight != getHeight())) {
            windowWidth = getWidth();
            windowHeight = getHeight();
            return (true);
        }
        return (false);
    }

    /* This method draws objects:
     * - Background.
     * - Border.
     * - ball.
     * - Platforms.
     */
    void drawAllElements() {
        drawBorder();
        drawBackground();
        drawBall();
        drawPlatformPlayer0();
        drawPlatformPlayer1();
    }

    /* This method draws background. */
    void drawBackground() {
        GRect background = new GRect(BORDER_SIZE, BORDER_SIZE,
                                    getWidth() - (2 * BORDER_SIZE),
                                    getHeight() - (2 * BORDER_SIZE));
        background.setColor(BACKGROUND_COLOR);
        background.setFilled(true);
        background.setFillColor(BACKGROUND_COLOR);
        add(background);
    }

    /* This method draws border. */
    void drawBorder() {
        GRect border = new GRect(0, 0, getWidth(), getHeight());
        border.setColor(BORDER_COLOR);
        border.setFilled(true);
        border.setFillColor(BORDER_COLOR);
        add(border);
    }

    /* This method draws ball. */
    void drawBall() {
        ball.setObjectPosition(getWidth() / 2.0, getHeight() / 2.0);
        ball.setObjectColor(BALL_COLOR, BALL_COLOR);
        add(ball.element);
    }

    /* This method draws platform number 0. */
    void drawPlatformPlayer0() {
        platform[0].setObjectPosition(BORDER_SIZE * 2, getHeight() / 2.0);
        platform[0].setObjectColor(PLATFORM_COLOR_0, PLATFORM_COLOR_0);
        add(platform[0].element);
    }

    /* This method draws platform number 1. */
    void drawPlatformPlayer1() {
        platform[1].setObjectPosition(getWidth() - BORDER_SIZE * 2, getHeight() / 2.0);
        platform[1].setObjectColor(PLATFORM_COLOR_1, PLATFORM_COLOR_1);
        add(platform[1].element);
    }

    /* This method moves objects:
     * - Platform #0 works in first half of the screen.
     * - Platform #1 works in second half of the screen..
     * - ball.
     */
    void moveAllElements() {
        if ((getWidth() / 2.0) > ball.getPositionX()) {
            movePlatform(0);
        } else if ((getWidth() / 2.0) < ball.getPositionX()) {
            movePlatform(1);
        }
        moveBall();
    }

    /* This method moves platform, follow the ball and check to collision on border. */
    void movePlatform(int i) {
        platform[i].followTarget(ball.getPositionY());
        platform[i].collisionBorder(getHeight(), BORDER_SIZE);
        platform[i].moveObject();
    }

    /* This method moves ball, check to collision on border and two platforms. */
    void moveBall() {
        ball.collisionBorder(getWidth(), getHeight(), BORDER_SIZE);
        ball.collisionPlatformPlayer0(platform[0].getPositionX(), platform[0].getSizeX());
        ball.collisionPlatformPlayer1(platform[1].getPositionX(), platform[1].getSizeX());
        ball.moveObject();
    }
}