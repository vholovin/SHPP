package com.shpp.p2p.cs.vholovin.assignment5;

import com.shpp.cs.a.console.TextProgram;


/**
 * This class adds 2 strings of number using the classic method from a column starting from the end of numbers.
 */
public class Assignment5Part2 extends TextProgram {
    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number: ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {

        if (!checkIsNumeric(n1) || !checkIsNumeric(n2)) {
            return ("null");
        }

        // Compare length n1 and n2, make length of n2 is larger, swaps each other
        if (n1.length() > n2.length()) {
            String tmp = n1;
            n1 = n2;
            n2 = tmp;
        }

        // New empty String for storing result
        StringBuilder str = new StringBuilder();

        // Calculate length of both String number
        int len1 = n1.length(), len2 = n2.length();

        // Reverse both of Strings number
        n1 = new StringBuilder(n1).reverse().toString();
        n2 = new StringBuilder(n2).reverse().toString();

        int remnant = 0;
        for (int i = 0; i < len1; i++) {

            // Compute sum of current numbers and remnant
            int sum = ((n1.charAt(i) - '0') + (n2.charAt(i) - '0') + remnant);
            str.append((char) (sum % 10 + '0'));

            // Calculate remnant for next step
            remnant = sum / 10;
        }

        // Add remnant to larger number starting from last index of short numeric
        for (int i = len1; i < len2; i++) {

            int sum = ((n2.charAt(i) - '0') + remnant);
            str.append((char) (sum % 10 + '0'));

            remnant = sum / 10;
        }

        // Add remnant if need to create next number to long numeric
        if (remnant > 0)
            str.append((char) (remnant + '0'));

        // reverse and return result
        return str.reverse().toString();
    }

    /**
     * Method checks input line to empty line and compare with numeric letter: 0-9.
     *
     * @param number - input line for check.
     * @return TRUE if line is number.
     */
    private boolean checkIsNumeric(String number) {
        if (number.isEmpty()) {
            println(number + " empty line");
            return false;
        }

        for (int i = 0; i < number.length(); i++) {
            if ((int) number.charAt(i) < (int) '0' || (int) number.charAt(i) > (int) '9') {
                println(number + " - is not natural number");
                return false;
            }
        }
        return true;
    }

}