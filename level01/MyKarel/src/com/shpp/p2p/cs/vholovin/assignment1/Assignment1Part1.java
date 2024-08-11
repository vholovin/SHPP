package com.shpp.p2p.cs.vholovin.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * In this class the robot must:
 * - go to the beeper (mail behind the door),
 * - pick it up,
 * - and return to the starting position.
 */
public class Assignment1Part1 extends KarelTheRobot {

    public void run() throws Exception {
        goToNewspaper();
        pickUpNewspaper();
        returnToStartPoint();
    }

    /* step 1 - go to the newspaper */
    private void goToNewspaper() throws Exception {
        toMoveOnCollision();
        toTurnRight();
        toMove();
        toTurnLeft();
        toMoveOnBeeper();
    }

    /* step 2 - pick up the newspaper */
    private void pickUpNewspaper() throws Exception {
        toPickUpBeeper();
    }

    /* step 3 - return to starting point */
    private void returnToStartPoint() throws Exception {
        toTurnAround();
        toMoveOnCollision();
        toTurnRight();
        toMoveOnCollision();
        toTurnRight();
    }

    /* cycle method for move to front collision */
    private void toMoveOnCollision() throws Exception {
        while (frontIsClear()) {
            toMove();
        }
    }

    /* method for move to beeper */
    private void toMoveOnBeeper() throws Exception {
        while (noBeepersPresent()) {
            toMove();
        }
    }

    /* method for move */
    private void toMove() throws Exception {
        move();
    }

    /* method for turn right */
    private void toTurnRight() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }

    /* method for turn left */
    private void toTurnLeft() throws Exception {
        turnLeft();
    }

    /* method for turn 180 degree */
    private void toTurnAround() throws Exception {
        turnLeft();
        turnLeft();
    }

    /* method for pickup beeper */
    private void toPickUpBeeper() throws Exception {
        if (beepersPresent()) {
            pickBeeper();
        }
    }
}