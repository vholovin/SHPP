package com.shpp.p2p.cs.vholovin.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * In this class the robot must:
 * - fill the vertical columns with beeper.
 */
public class Assignment1Part2 extends KarelTheRobot {

    public void run() throws Exception {
        mainStep();
        finalStep();
    }

    /* main cycle for move robot from start point first line, for each line and to start position last line */
    private void mainStep() throws Exception {
        while (frontIsClear() && facingEast()) {
            toTurnLeft();
            toCheckAndMoveOnCollision();
            toTurnAround();
            toMoveOnCollision();
            toTurnLeft();
            toMoveOnPeriod();
        }
    }

    /* final step for move robot from start point to finish point at last line */
    private void finalStep() throws Exception {
        if (facingEast()) {
            toTurnLeft();
            toCheckAndMoveOnCollision();
        }
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

    /* cycle method for move robot to wall and check beepers */
    private void toCheckAndMoveOnCollision() throws Exception {
        toCheckAndPutBeeper();
        while (frontIsClear()) {
            toMove();
            toCheckAndPutBeeper();
        }
    }

    /* cycle method for quick move robot to down part of platform */
    private void toMoveOnCollision() throws Exception {
        while (frontIsClear()) {
            toMove();
        }
    }

    /* method for move forward 4 position */
    private void toMoveOnPeriod() throws Exception {
        for (int i = 0; i < 4; i++) {
            if (frontIsClear()) {
                toMove();
            } else {
                turnLeft();
            }
        }
    }

    /* method for move */
    private void toMove() throws Exception {
        move();
    }

    /* method for check and put beeper */
    private void toCheckAndPutBeeper() throws Exception {
        if (noBeepersPresent()) {
            putBeeper();
        }
    }
}