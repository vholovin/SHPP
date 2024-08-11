package com.shpp.p2p.cs.vholovin.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * In this class the robot must:
 * - fill the surface with beepers in a chess-like order.
 */
public class Assignment1Part4 extends KarelTheRobot {

    public void run() throws Exception {
        while (facingEast()) {
            firstPeriod();
            if (rightIsClear()) {
                toReturn();
                secondPeriod();
                if (rightIsClear())
                    toReturn();
            }
        }
    }

    /* put beepers on even numbers (0,2,6,..) */
    private void firstPeriod() throws Exception {
        toTurnLeft();
        toCheckAndPutBeeper();
        while (frontIsClear()) {
            if (frontIsClear()) {
                toMove();
            }
            if (frontIsClear()) {
                toMove();
                toCheckAndPutBeeper();
            }
        }
    }

    /* put beepers on odd numbers (1,3,5,..) */
    private void secondPeriod() throws Exception {
        toTurnLeft();
        while (frontIsClear()) {
            if (frontIsClear()) {
                toMove();
                toCheckAndPutBeeper();
            }
            if (frontIsClear()) {
                toMove();
            }
        }
    }

    /* method for return to down */
    private void toReturn() throws Exception {
        toTurnAround();
        while (frontIsClear())
            toMove();
        toTurnLeft();
        if (frontIsClear()) {
            toMove();
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