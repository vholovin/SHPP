package com.shpp.p2p.cs.vholovin.assignment3;

import com.shpp.cs.a.console.TextProgram;
import java.util.Random;

/**
 * This class illustrates how works "St. Petersburg lottery".
 * The St. Petersburg paradox or St. Petersburg lottery is a paradox involving the game of flipping
 * a coin where the expected payoff of the theoretical lottery game approaches infinity
 * but nevertheless seems to be worth only a very small amount to the participants.
 */
public class Assignment3Part5 extends TextProgram {

    /* Constant of guaranteed minimum winnings. */
    private static final int MIN_RATE = 1;

    /* Constant of possible maximum winnings. */
    private static final int MAX_RATE = 20;

    /* Constant multiplication rate. */
    private static final int MULTIPLICATION_RATE = 2;

    /** Value of the rate in the round. */
    private int currentRate;

    /** Counter of the number of rounds. */
    private int countRate;

    /** Money for Lucky guy. */
    private int resultMoney;

    /**
     * Default constructors, which sets the starting values for the game.
     */
    public Assignment3Part5(){
        currentRate = MIN_RATE;
        countRate = 0;
        resultMoney = 0;
    }

    /* This method has two loops:
     * - the first is limited to the possible maximum winning.
     * - the second is limited of random value to the money sides: Eagle and Tail.
     *   Method "randomRate.nextBoolean()" returns true - "EAGLE" or false - "TAIL".
     */
    public void run() {
        Random randomRate = new Random();

        while (resultMoney < MAX_RATE) {
            while (randomRate.nextBoolean()) {
                currentRate *= MULTIPLICATION_RATE;
            }
            resultMoney += currentRate;

            println("This game, you earned $" + currentRate);
            println("Your total is $" + resultMoney);

            currentRate = MIN_RATE;
            countRate++;
        }
        println("It took " + countRate + " games to earn $" + MAX_RATE);
    }
}