package com.shpp.p2p.cs.vholovin.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.math.BigInteger;

/**
 * This class is an imitation of a "hailstone numerals" sequence.
 *
 * if number odd: n = 3 * n + 1;
 *
 * if number even: n = n / 2;
 *
 * Continue this process until n is equal to 1.
 */
public class Assignment3Part2 extends TextProgram {

    /** There is the class of Int type whose has "unlimited" sizes. */
    private BigInteger number = BigInteger.ZERO;

    /**
     * Default constructors, which reads and checks the input parameter - is a positive and Integer number.
     *
     * Check to exceptions when the input data:
     * - is not integer,
     * - has other letters and symbols,
     * - is negative.
     */
    public Assignment3Part2() {
        String input;
        boolean isNumber = false;
        while (!isNumber) {
            input = readLine("Enter a positive and Integer number: ");
            try {
                number = new BigInteger(input);
                if (number.signum() <= 0) {
                    println("There is not positive number");
                } else {
                    isNumber = true;
                }
            } catch (NumberFormatException e) {
                println("There is not Integer number");
            }
        }
    }

    /* This method runs a one-time check for value = 1 and a loop check for value != 1.
     */
    public void run() {
        if (number.equals(new BigInteger("1"))) {
            mathematicalOperation();
        }
        while (!number.equals(new BigInteger("1"))) {
            mathematicalOperation();
        }
        println("The end.");
    }


    /* This method checks the number to even/odd and does the correspond operations.
     */
    void mathematicalOperation()
    {
        print(number);
        if (isOdd(number)) {
            print(" - is odd, multiply to 3 and add 1, result: ");
            number = number.multiply(new BigInteger("3"));
            number = number.add(new BigInteger("1"));
        } else {
            print(" - is even, divide to 2, result: ");
            number = number.divide(new BigInteger("2"));
        }
        println(number);
    }

    /* This method checks if this number is odd.
     * If it is true - return true, else false.
     */
    boolean isOdd(BigInteger isOddNumber) {
        isOddNumber = isOddNumber.mod(new BigInteger("2"));
        return (!isOddNumber.equals(BigInteger.ZERO));
    }
}
