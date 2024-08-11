package com.shpp.p2p.cs.vholovin.assignment10;

import java.util.*;

/**
 * Calculator Part I
 * Support for the following mathematical operators: -, +, /, *, ^.
 *
 * Use command-line arguments for works and tests this assignment.
 * In first parameter: mathematical expression or formula.
 * Each of the following parameters: variables.
 */
public class Assignment10Part1 {

    /**
     * Outputs a mathematical calculation from the input parameters.
     *
     * @param args input parameters from the terminal line.
     */
    public static void main(String[] args) {
        try {

            if (args.length >= 2) {
                System.out.println("RESULT: " + calculate(parsFormula(args[0]), parsVariables(args)).floatValue());
            } else if (args.length == 1) {
                System.out.println("RESULT: " + calculate(parsFormula(args[0]), null).floatValue());
            } else {
                throw new Exception("invalid count of input parameters");
            }

        } catch (Exception message) {
            System.err.println("ERROR: " + message + ".");
        }
    }

    /**
     * This method parses a single string formula to a string array of elements
     * for easy processing in further.
     *
     * @param str unparsed formula from string.
     * @return parsed formula to string array of elements and symbols.
     */
    private static List<String> parsFormula(String str) throws Exception {

        if (str.isEmpty()) { throw new Exception("formula is empty"); }

        List<String> formula = new ArrayList<>();
        StringBuilder element = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {

            if (Character.isLetterOrDigit(str.charAt(i)) || str.charAt(i) == '.') {

                // add to element when char-index is letter or digit and is dot
                element.append(str.charAt(i));

            } else if (str.charAt(i) == '-' || str.charAt(i) == '+' ||
                    str.charAt(i) == '*' || str.charAt(i) == '/' ||
                    str.charAt(i) == '^') {

                // push previous collected element to formula
                if (!element.isEmpty()) {
                    formula.add(element.toString());
                    element.setLength(0);
                }

                // add to element when char-index is symbol: -, +, *, /, ^
                element.append(str.charAt(i));

                // push collected element to formula and reset
                formula.add(element.toString());
                element.setLength(0);

            } else if (Character.isSpaceChar(str.charAt(i)) && !element.isEmpty()) {
                // skip spaces and if element is not empty - push collected element to formula and reset
                formula.add(element.toString());
                element.setLength(0);
            }

        }

        // push last collected element to formula
        if (!element.isEmpty()) {
            formula.add(element.toString());
        }

        // for case when first element has negative value
        if (formula.get(0).contentEquals("-")) {
            formula.set(0, "*");
            formula.add(0, "-1");
        }

        return formula;
    }

    /**
     * This method parses the input string array to the name and value of the variables.
     *
     * @param args unparsed names and values of the variables.
     * @return parsed names and values of the variables.
     */
    private static HashMap<String, Double> parsVariables(String[] args) throws Exception {
        HashMap<String, Double> variables = new HashMap<>();
        List<String> keyId = new ArrayList<>();
        List<Double> valueId = new ArrayList<>();
        String[] param;

        for (int i = 1; i < args.length; i++) {

            param = args[i].split("=");
            if (param.length == 2) {
                keyId.add(parsVariableName(param[0]));
                valueId.add(parsVariableValue(param[1]));
            } else {
                throw new Exception("invalid in variables: " + args[i]);
            }

        }

        for (int i = 0; i < keyId.size(); i++) {
            variables.put(keyId.get(i), valueId.get(i));
        }

        return variables;
    }

    /**
     * This method parses the names of a variable.
     *
     * @param nameParse unparsed name of the variable.
     * @return parsed value of the variable.
     */
    private static String parsVariableName(String nameParse) throws Exception {
        StringBuilder name = new StringBuilder();

        for (int i = 0; i < nameParse.length(); i++) {

            // skip spaces
            if (!Character.isSpaceChar(nameParse.charAt(i))) {

                // add to the string builder when char-index is letter or digit
                if ((Character.isLetterOrDigit(nameParse.charAt(i)))) {
                    name.append(nameParse.charAt(i));
                } else {
                    throw new Exception("name of variables has another elements: " + nameParse);
                }

            }
        }

        if (name.isEmpty()) { throw new Exception("name of variables is empty"); }

        return name.toString();
    }

    /**
     * This method parses the value of a variable.
     *
     * @param valueParse unparsed value of the variable.
     * @return parsed value of the variable.
     */
    private static double parsVariableValue(String valueParse) throws Exception {
        StringBuilder value = new StringBuilder();
        boolean isNegative = false;
        boolean isFraction = false;

        for (int i = 0; i < valueParse.length(); i++) {

            // skip spaces
            if (!Character.isSpaceChar(valueParse.charAt(i))) {

                // add to the string builder when char-index is digit
                // and there was already symbols minus or dot at least once
                if ((Character.isDigit(valueParse.charAt(i))) ||
                        (valueParse.charAt(i) == '.' && !isFraction) ||
                        (valueParse.charAt(i) == '-' && !isNegative)) {

                    if (valueParse.charAt(i) == '.') { isFraction = true; }
                    if (valueParse.charAt(i) == '-') { isNegative = true; }

                    value.append(valueParse.charAt(i));

                } else {
                    throw new Exception("name of variables has another elements: " + valueParse);
                }

            }
        }

        if (value.isEmpty()) { throw new Exception("value of variables is empty"); }

        return Double.parseDouble(value.toString());
    }

    /**
     * The main method for mathematical calculations.
     *
     * @param formula   input string array of formula elements and symbols.
     * @param variables input variables for the replaces in formula.
     * @return result of a mathematical calculations.
     */
    public static Double calculate(List<String> formula, HashMap<String, Double> variables) throws Exception {

        replaceVariables(formula, variables);

        calculateRaiseToPower(formula);
        calculateMultDiv(formula);
        calculateSubAdd(formula);

        return Double.parseDouble(formula.get(0));
    }

    /**
     * The method replaces parameters in the formula to specific values with variables, if any.
     *
     * @param formula   input string array of formula elements and symbols.
     * @param variables variables for changes parameters to values in the formula.
     */
    private static void replaceVariables(List<String> formula, HashMap<String, Double> variables) {

        if (variables == null) { return; }

        for (String parameter : variables.keySet()) {
            for (int i = 0; i < formula.size(); i++) {

                if (formula.get(i).contentEquals(parameter)) {
                    formula.set(i, variables.get(parameter).toString());
                }
            }
        }
    }

    /**
     * A method for calculates a mathematical operations: raise to power.
     * The loop search mathematical symbols from the end of the array,
     * and performs the corresponding operation with the next and previous element of the array.
     * The result overwrites to an index of mathematical symbol,
     * and the elements for calculations remove from the array.
     *
     * @param formula input string array of formula elements and symbols.
     */
    private static void calculateRaiseToPower(List<String> formula) {
        double newParam;
        boolean isRaiseToPower = true;

        // if some operators are not found in the cycle, we exit from there
        while (isRaiseToPower) {
            isRaiseToPower = false;

            // loop for searching some operators in a formula
            for (int i = formula.size() - 1; i > 0; i--) {

                if (formula.get(i).contentEquals("^")) {

                    newParam = Math.pow(Double.parseDouble(formula.get(i - 1)), Double.parseDouble(formula.get(i + 1)));
                    isRaiseToPower = updateFormula(formula, i, newParam);
                }
            }
        }
    }

    /**
     * A method for calculates a mathematical operations: multiplication and division.
     * The loop search mathematical symbols from the end of the array,
     * and performs the corresponding operation with the next and previous element of the array.
     * The result overwrites to an index of mathematical symbol,
     * and the elements for calculations remove from the array.
     *
     * @param formula input string array of formula elements and symbols.
     */
    private static void calculateMultDiv(List<String> formula) throws Exception {
        double newParam;
        boolean isMultDiv = true;

        // if some operators are not found in the cycle, we exit from there
        while (isMultDiv) {
            isMultDiv = false;

            // loop for searching some operators in a formula
            for (int i = formula.size() - 1; i > 0; i--) {

                if (formula.get(i).contentEquals("/")) {

                    if (Double.parseDouble(formula.get(i + 1)) == 0) { throw new Exception("division by zero"); }

                    newParam = Double.parseDouble(formula.get(i - 1)) / Double.parseDouble(formula.get(i + 1));
                    isMultDiv = updateFormula(formula, i, newParam);

                } else if (formula.get(i).contentEquals("*")) {

                    newParam = Double.parseDouble(formula.get(i - 1)) * Double.parseDouble(formula.get(i + 1));
                    isMultDiv = updateFormula(formula, i, newParam);

                }
            }
        }
    }

    /**
     * A method for calculates a mathematical operations: addition and subtraction.
     * The loop search mathematical symbols from the end of the array,
     * and performs the corresponding operation with the next and previous element of the array.
     * The result overwrites to an index of mathematical symbol,
     * and the elements for calculations remove from the array.
     *
     * @param formula input string array of formula elements and symbols.
     */
    private static void calculateSubAdd(List<String> formula) {
        double newParam;
        boolean isSubAdd = true;

        // if some operators are not found in the cycle, we exit from there
        while (isSubAdd) {
            isSubAdd = false;

            // loop for searching some operators in a formula
            for (int i = formula.size() - 1; i > 0; i--) {

                if ((i - 2) > 0 && (formula.get(i).contentEquals("+") || formula.get(i).contentEquals("-"))) {
                    // for case when previous value is negative
                    // we transfer "-" to the previous value and replace this parameter to "+"
                    if (formula.get(i - 2).contentEquals("-")) {
                        formula.set(i - 1, Double.toString(Double.parseDouble(formula.get(i - 1)) * -1.0));
                        formula.set(i - 2, "+");
                    }
                }

                if (formula.get(i).contentEquals("-")) {

                    newParam = Double.parseDouble(formula.get(i - 1)) - Double.parseDouble(formula.get(i + 1));
                    isSubAdd = updateFormula(formula, i, newParam);

                } else if (formula.get(i).contentEquals("+")) {

                    newParam = Double.parseDouble(formula.get(i - 1)) + Double.parseDouble(formula.get(i + 1));
                    isSubAdd = updateFormula(formula, i, newParam);
                }
            }
        }
    }

    /**
     * This method updates a specific index of array value in the formula.
     *
     * @param formula  input string array of formula elements and symbols.
     * @param index    index in array where updates values in formula.
     * @param newParam new values for update.
     * @return always true that the update has passed.
     */
    private static boolean updateFormula(List<String> formula, int index, double newParam) {

        formula.set(index, Double.toString(newParam));
        formula.remove(index + 1);
        formula.remove(index - 1);

        return true;
    }
}