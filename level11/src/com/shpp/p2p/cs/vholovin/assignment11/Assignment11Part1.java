package com.shpp.p2p.cs.vholovin.assignment11;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Calculator Part II
 * Support for the following mathematical operators: -, +, /, *, ^.
 * Support some function: sqrt, log10, log2, tan, atan, sin, cos.
 *
 * Use command-line arguments for works and tests this assignment.
 * In first parameter: mathematical expression or formula.
 * Each of the following parameters: variables.
 */
public class Assignment11Part1 {

    /** Constant for quick debug. */
    private final static boolean SHOW_DEBUG_INFO = false;

    /**
     * Outputs a mathematical calculation from the input parameters.
     *
     * @param args input parameters from the terminal line.
     */
    public static void main(String[] args) {

        try {
            if (args.length == 0) { throw new Exception("not correct counts of input parameters"); }

            Formula formula = new Formula(args[0]);
            Variables variables = new Variables(args);

            if (SHOW_DEBUG_INFO) {
                System.out.print("raw input:");
                for (String s : args) { System.out.print(" " + '"' + s + '"'); }
                System.out.println();
                System.out.println("parse input: " + formula.get() + " " + variables.get());
                System.out.println("convert to postfix: " + formula.getPostfix() + " " + variables.get());
            }

            System.out.println("RESULT: " + calculate(formula.getPostfix(), variables.get()).floatValue());

        } catch (Exception message) {
            System.err.println("ERROR: " + message + ".");
        }
    }

    /**
     * This method replaces variables in formula and calculates.
     *
     * @param formula   input string array of formula elements and symbols.
     * @param variables input variables for the replaces in formula.
     * @return result of a mathematical calculations.
     */
    public static Double calculate(ArrayList<String> formula, HashMap<String, Double> variables) throws Exception {

        Calculator calc = new Calculator();
        calc.replaceVariables(formula, variables);

        if (SHOW_DEBUG_INFO) {
            System.out.println("after replace: " + formula);
        }

        calc.evaluate(formula);
        return calc.get();
    }
}