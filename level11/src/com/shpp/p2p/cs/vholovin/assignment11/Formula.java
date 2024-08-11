package com.shpp.p2p.cs.vholovin.assignment11;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Formula class for processing input parameters.
 */
public class Formula {

    /**
     * Array of String for the traditional formula representation.
     */
    private final ArrayList<String> formula = new ArrayList<>();

    /**
     * Array of String for the Polish postfix notation.
     */
    private final ArrayList<String> postfix = new ArrayList<>();

    /**
     * The default constructor for the formula class that receives string formula for parsing.
     *
     * @param str unparsed formula from string.
     */
    Formula(String str) throws Exception {

        parsFormula(str);
        convertInfixToPostfix();
    }

    /**
     * This method parses a single string formula to a string array of elements
     * for easy processing in further.
     *
     * @param str unparsed formula from string.
     */
    private void parsFormula(String str) throws Exception {

        if (str.isEmpty()) {
            throw new Exception("formula is empty");
        }

        StringBuilder element = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {

            if (Character.isLetterOrDigit(str.charAt(i)) || str.charAt(i) == '.') {

                // add to element when char-index is letter or digit and is dot
                element.append(str.charAt(i));

            } else if (str.charAt(i) == '-' || str.charAt(i) == '+' ||
                    str.charAt(i) == '*' || str.charAt(i) == '/' ||
                    str.charAt(i) == '^' || str.charAt(i) == '(' || str.charAt(i) == ')') {

                // push previous collected element to formula
                if (!element.isEmpty()) {
                    formula.add(element.toString());
                    element.setLength(0);
                }

                // add to element when char-index is symbol: -, +, *, /, ^, (, ).
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

        parsNegativeSymbol();
    }

    /**
     * additional processing for some cases.
     */
    private void parsNegativeSymbol() {
        // for case when first element has negative value
        if (formula.get(0).contentEquals("-")) {
            formula.set(0, "*");
            formula.add(0, "-1");
        }

        for (int i = formula.size() - 1; i > 0; i--) {

            // for case when minus set between "(" and "digit".
            if (formula.get(i).contentEquals("-")
                    && Character.isDigit(formula.get(i + 1).charAt(0))
                    && formula.get(i - 1).contentEquals("(")) {

                formula.set(i + 1, "-" + formula.get(i + 1));
                formula.remove(i);
            }

            // for case when minus set between "(" and "(".
            if (formula.get(i).contentEquals("-")
                    && formula.get(i + 1).contentEquals("(")
                    && formula.get(i - 1).contentEquals("(")) {

                formula.set(i, "*");
                formula.add(i, "-1");
            }

            // for case when minus set between "operator" and "digit".
            if (formula.get(i).contentEquals("-")
                    && Character.isDigit(formula.get(i + 1).charAt(0))
                    && isOperator(formula.get(i - 1))) {

                formula.set(i + 1,"-" + formula.get(i + 1));
                formula.remove(i);
            }
        }
   }

    /**
     * This method converts the traditional formula representation to the Polish postfix notation.
     * For
     */
    private void convertInfixToPostfix() throws Exception {

        Stack<String> stack = new Stack<>();
        String element;
        int bracket = 0;

        for (String s : formula) {
            element = s;

            if (!isOperator(element) && !isFunction(element) && !isBracket(element)) {
                // add value of variables to the array.
                postfix.add(element);

            } else if (element.contentEquals("(")) {
                // add open bracket to stack.
                bracket++;
                stack.push(element);

            } else if (element.contentEquals(")")) {

                bracket--;
                // Transfers values, symbols and functions from the stack
                // to the array accumulated from close to open brackets.
                while (!stack.isEmpty() && !stack.peek().contentEquals("(")) {
                    postfix.add(stack.peek());
                    stack.pop();
                }
                // delete open bracket from stack.
                if (!stack.isEmpty()) {
                    stack.pop();
                }

            } else {


                if (element.contentEquals("^")) {
                    // for '^' right position symbol only '<' precedence.
                    while (!stack.isEmpty() && (precedence(element) < precedence(stack.peek()))) {
                        postfix.add(stack.peek());
                        stack.pop();
                    }
                } else {
                    // transfers symbols and functions from stack to the array following their priority.
                    while (!stack.isEmpty() && (precedence(element) <= precedence(stack.peek()))) {
                        postfix.add(stack.peek());
                        stack.pop();
                    }

                }

                stack.push(element);

            }
        }

        if (bracket != 0) throw new Exception("no correct count of brackets");

        // add all the operators from the stack to array.
        while (!stack.isEmpty()) {
            postfix.add(stack.peek());
            stack.pop();
        }
    }

    /**
     * Boolean method for operator.
     */
    private boolean isOperator(String operator) {

        if (operator.contentEquals("^")) {
            return true;
        } else if (operator.contentEquals("*") || operator.contentEquals("/")) {
            return true;
        } else if (operator.contentEquals("+") || operator.contentEquals("-")) {
            return true;
        }
        return false;
    }

    /**
     * Boolean method for function.
     */
    private boolean isFunction(String function) {

        if (function.contentEquals("sin") || function.contentEquals("cos")) {
            return true;
        } else if (function.contentEquals("tan") || function.contentEquals("atan")) {
            return true;
        } else if (function.contentEquals("log10") || function.contentEquals("log2")) {
            return true;
        } else if (function.contentEquals("sqrt")) {
            return true;
        }
        return false;
    }

    /**
     * Boolean method for bracket.
     */
    private boolean isBracket(String bracket) {

        if (bracket.contentEquals("(") || bracket.contentEquals(")")) {
            return true;
        }
        return false;
    }

    /**
     * @return precedence of a given operator or function. Higher returned value means higher precedence.
     */
    private int precedence(String function) {

        if (function.contentEquals("sin") || function.contentEquals("cos")) {
            return 3;
        } else if (function.contentEquals("tan") || function.contentEquals("atan")) {
            return 3;
        } else if (function.contentEquals("log10") || function.contentEquals("log2")) {
            return 3;
        } else if (function.contentEquals("^") || function.contentEquals("sqrt")) {
            return 3;
        } else if (function.contentEquals("*") || function.contentEquals("/")) {
            return 2;
        } else if (function.contentEquals("+") || function.contentEquals("-")) {
            return 1;
        }

        return (-1);
    }

    /**
     * @return Array of String for the traditional formula representation.
     */
    public ArrayList<String> get() {
        return formula;
    }

    /**
     * @return Array of String for the Polish postfix notation.
     */
    public ArrayList<String> getPostfix() {
        return postfix;
    }
}
