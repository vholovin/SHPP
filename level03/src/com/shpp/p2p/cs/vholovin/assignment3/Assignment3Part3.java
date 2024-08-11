package com.shpp.p2p.cs.vholovin.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * This class demonstrates how works to raise to a power.
 */
public class Assignment3Part3 extends TextProgram {

    /* For examples.
     */
    public void run() {
        println(raiseToPower(0.5, -2) + " Correct answer is 4.0");
        println(raiseToPower(-2, 2) + " Correct answer is 4.0");
        println(raiseToPower(2, 3) + " Correct answer is 8.0");
        println(raiseToPower(0, 0) + " Correct answer is 1.0");
        println(raiseToPower(0.5, 2) + " Correct answer is 0.25");
        println(raiseToPower(2, 10) + " Correct answer is 1024.0");
        println(raiseToPower(9999, 0) + " Correct answer is 1.0");
        println(raiseToPower(777, 1) + " Correct answer is 777.0");
    }

    /* Exponentiation is a mathematical operation, written as b^n, involving two numbers,
     * the base b and the exponent or power n, and pronounced as "b raised to the power of n".
     * b ^ n = b * b * ... * b (n times).
     * This method raises a number to a power.
     */
    private double raiseToPower(double base, int exponent) {
        double result = 1;

        while (exponent != 0) {
            if (exponent < 0) {
                result /= base;
                exponent++;
            }
            if (exponent > 0) {
                result *= base;
                exponent--;
            }
        }
        return result;
    }
}