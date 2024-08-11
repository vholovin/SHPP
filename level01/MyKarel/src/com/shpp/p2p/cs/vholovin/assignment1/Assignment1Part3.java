package com.shpp.p2p.cs.vholovin.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * In this class the robot must:
 * - find the middle of the bottom line,
 * - and leave the beeper there.
 */
public class Assignment1Part3 extends KarelTheRobot {

    public void run() throws Exception {
        setLineBeepers();
        toTurnAround();
        if (frontIsClear()) {
            while (facingWest() || facingEast()) {
                toRemoveBeepers();
                toCheckMiddleBeepers();
            }
        }
    }

    /* set beepers into line */
    private void setLineBeepers() throws Exception {
        toCheckAndPutBeeper();
        while (frontIsClear()) {
            toMove();
            toCheckAndPutBeeper();
        }
    }

    /* remove the beepers at the edges */
    private void toRemoveBeepers() throws Exception {
        toRemoveBeeper();
        while (frontIsClear()) {
            toMove();
        }
        toTurnAround();
        if (noBeepersPresent()) {
            while (noBeepersPresent()) {
                toMove();
            }
        }
    }

    /* remove the beepers at the edges */
    private void toCheckMiddleBeepers() throws Exception {
        toMove();
        if (noBeepersPresent()) {
            turnLeft();
        }
        else if (beepersPresent()) {
            toTurnAround();
            toMove();
            toTurnAround();
        }
    }

    /* method for move */
    private void toMove() throws Exception {
        move();
    }

    /* method for turn 180 degree */
    private void toTurnAround() throws Exception {
        turnLeft();
        turnLeft();
    }

    /* method for check and remote beeper */
    private void toRemoveBeeper() throws Exception {
        while (beepersPresent()) {
            pickBeeper();
        }
    }

    /* method for check and put beeper */
    private void toCheckAndPutBeeper() throws Exception {
        if (noBeepersPresent()) {
            putBeeper();
        }
    }
}