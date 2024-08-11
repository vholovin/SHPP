package com.shpp.p2p.cs.vholovin.assignment4;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * For historical reference:
 * Breakout is an arcade video game developed and published by Atari, and released on May 13, 1976.
 * It was designed by Steve Wozniak, based on conceptualization from Nolan Bushnell
 * and Steve Bristow who were influenced by the seminal 1972 Atari arcade game Pong.
 *
 * In Breakout, a layer of bricks lines the top third of the screen and the goal is to destroy them all
 * by repeatedly bouncing a ball off a paddle into them. The arcade game was released in Japan by Namco.
 * Breakout was a worldwide commercial success, among the top five highest-grossing arcade video games
 * of 1976 in both the United States and Japan and then among the top three highest-grossing arcade
 * video games of 1977 in the US and Japan.
 */
public class Breakout extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10; //x

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10; //y

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static final int NTURNS = 3;


    /**
     * Parameters for text: font type, font size, font color and game messenger.
     */
    private static final String FONT = "Verdana";
    private static final int FONT_SIZE = 30;
    private static final Color FONT_COLOR = Color.BLACK;
    private static final String[] GAME_MESS = {"YOU LOSE", "YOU WIN"};


    /**
     * Parameter for pause between the frame.
     */
    private static final double PAUSE = 1000.0 / 60;

    /**
     * Parameters for gameplay: status play for continues game, player life and alive bricks.
     */
    private boolean play = false;
    private int life = NTURNS;
    private int brick = 0;


    /**
     * Gameplay elements: paddle, ball, array of bricks and messenger.
     */
    private final PaddleElement paddle = new PaddleElement(PADDLE_WIDTH, PADDLE_HEIGHT, WIDTH);
    private final BallElement ball = new BallElement(BALL_RADIUS);
    private final BrickArrayElement brickArray = new BrickArrayElement(NBRICK_ROWS, NBRICKS_PER_ROW);
    private final GLabel label = new GLabel("");

    /**
     * Main sequential core for game.
     */
    public void run() {
        addMouseListeners();
        setElements();
        drawElements();
        while (true) {
            while (play) {
                moveElements();
                pause(PAUSE);
            }
            showLabel();
        }
    }

    /* Initialization parameters for game elements. */
    void setElements() {
        paddle.setPosition(WIDTH, HEIGHT, PADDLE_Y_OFFSET);
        ball.setPosition(WIDTH / 2.0 - BALL_RADIUS, HEIGHT / 2.0 - BALL_RADIUS);
        ball.setDirection();
        brickArray.setPosition(WIDTH, BRICK_WIDTH, BRICK_HEIGHT, BRICK_SEP, BRICK_Y_OFFSET);
        brick = NBRICKS_PER_ROW * NBRICK_ROWS;
    }

    /* Draws all game elements. */
    void drawElements() {
        add(paddle.draw());
        add(ball.draw());
        for (int j = 0; j < NBRICK_ROWS; j++) {
            for (int i = 0; i < NBRICKS_PER_ROW; i++) {
                add(brickArray.draw(j, i));
            }
        }
    }

   /* Moves paddle and ball elements.
    * The movement of the ball in one frame occurs pixel-by-pixel iteratively on the speed.
    * After the pixel displacement, ball checks to collision with other elements - where we stop the iteration
    * and direct it.
    */
    void moveElements() {
        paddle.move();

        double moveVX = Math.abs(ball.dirX());
        double moveVY = Math.abs(ball.dirY());

        int separator;

        if (moveVX > moveVY)
            separator = (int) moveVX;
        else
            separator = (int) moveVY;

        double stepVX = ball.dirX() / separator;
        double stepVY = ball.dirY() / separator;

        while (separator > 0) {
            separator--;
            ball.move(stepVX, stepVY);

            if (checkCollisionBorder()) {
                break;
            }
            if (checkCollisionPaddleBrick(getCollidingObject())) {
                break;
            }
        }
    }

    /*
     * Checks collision with border in all sides.
     * If it is a bottom side - method stops play, subtracts life and resets ball parameters.
     *
     * @return TRUE - if there was a collision, and FALSE - if there was not a collision;
     */
    boolean checkCollisionBorder() {
        if (ball.checkCollisionBorder(WIDTH)) {
            return true;
        }
        if (ball.checkBottomBorder(HEIGHT)) {
            play = false;
            life--;
            paddle.setPosition(WIDTH, HEIGHT, PADDLE_Y_OFFSET);
            ball.setPosition(WIDTH / 2.0 - BALL_RADIUS, HEIGHT / 2.0 - BALL_RADIUS);
            ball.setDirection();
            return true;
        }
        return false;
    }

    /*
     * Checks collision with paddle and bricks.
     * if collision with brick - method remove this brick.
     *
     * @param collider - the object with which to check whether there is a collision;
     * @return TRUE - if there was a collision, and FALSE - if there was not a collision;
     */
    boolean checkCollisionPaddleBrick(GObject collider) {
        if (collider == null) {
            return false;
        }

        if (!(collider instanceof GOval)) {
            if (collider == paddle.getElement()) {
                if (ball.onCollision(collider)) {
                    return true;
                }
            } else {
                if (ball.onCollision(collider)) {
                    remove(collider);
                    brick--;
                    if (brick == 0) {
                        play = false;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /* Method for reading mouse movements. */
    public void mouseMoved(MouseEvent me) {
        paddle.setMouseX(me.getX());
    }

    /* Method for reading mouse movements. Works when game is not play, players life is more 0 */
    public void mouseClicked(MouseEvent me) {
        if (!play && life > 0 && brick > 0) {
            play = true;
        } else if (!play) {
            exit();
        }
    }

   /* Method checks the collision of the ball with objects in the predefined coordinates of the ball itself:
    *  (0, 0) - - - (2R, 0)
    *       |       |
    *       |   *   |
    *       |       |
    * (0, 2R) - - - (2R, 2R)
    */
    private GObject getCollidingObject() {
        GObject collider = null;

        double steep = 2.0 * ball.radius();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (getElementAt(ball.posX() + steep * i, ball.posY() + steep * j) != null) {
                    collider = getElementAt(ball.posX() + steep * i, ball.posY() + steep * j);
                }
            }
        }
        return collider;
    }

    /* Method outputs the result of the game: win or lose. */
    void showLabel() {
        if (life == 0) {
            label.setLabel(GAME_MESS[0]);
        } else if (brick == 0) {
            label.setLabel(GAME_MESS[1]);
        }
        label.setFont(FONT + "-" + FONT_SIZE);
        label.setColor(FONT_COLOR);
        label.setLocation(getWidth() / 2.0 - label.getWidth(), getHeight() / 2.0 - label.getDescent());
        add(label);
    }
}