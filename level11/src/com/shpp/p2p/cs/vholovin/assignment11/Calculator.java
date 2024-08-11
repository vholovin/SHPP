package com.shpp.p2p.cs.vholovin.assignment11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/** Calculator class for calculate formula. */
public class Calculator {

    /** Result of calculate. */
    private double result;

    /**
     * The method replaces parameters in the formula to specific values with variables, if any.
     *
     * @param formula   input string array of formula elements and symbols.
     * @param variables variables for changes parameters to values in the formula.
     */
    public void replaceVariables(List<String> formula, HashMap<String, Double> variables) {

        if (variables == null) {
            return;
        }

        for (String parameter : variables.keySet()) {
            for (int i = 0; i < formula.size(); i++) {

                if (formula.get(i).contentEquals(parameter)) {
                    formula.set(i, variables.get(parameter).toString());
                }
            }
        }
    }

    /** This method evaluates Polish postfix notation. */
    public void evaluate(ArrayList<String> postfix) throws Exception {

        Stack<String> stack = new Stack<>();
        String element;

        for (String s : postfix) {
            element = s;

            if (!isOperator(element) && !isFunction(element)) {
                // add values to stack.
                stack.push(element);
            } else {
                // pop values from stack for operations.
                if (isOperator(element)) { switchOperators(stack, element); }
                if (isFunction(element)) { switchFunctions(stack, element); }
            }
        }

        result = Double.parseDouble(stack.pop());

        if (!stack.isEmpty()) {throw new Exception("has another elements");}
    }

    /**
     * @return result of calculate.
     */
    public double get() {
        return result;
    }

    /** Boolean method for operator. */
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

    /** Boolean method for function. */
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

    /** Switch method for operation that needs two parameters. */
    private void switchOperators(Stack<String> stack, String operator) throws Exception {

        double result;

        // pop two parameters from stack.
        if (stack.isEmpty()) { throw new Exception("no correct counts in stack for parameters p2"); }
        double p2 = Double.parseDouble(stack.pop());

        if (stack.isEmpty()) { throw new Exception("no correct counts in stack for parameters p1"); }
        double p1 = Double.parseDouble(stack.pop());


        if (operator.contentEquals("^")) {

            result = Math.pow(p1, p2);
            stack.push(Double.toString(result));

        } else if (operator.contentEquals("*")) {

            result = p1 * p2;
            stack.push(Double.toString(result));

        } else if (operator.contentEquals("/")) {

            if (p2 == 0) {
                throw new Exception("division by zero");
            }
            result = p1 / p2;
            stack.push(Double.toString(result));

        } else if (operator.contentEquals("+")) {

            result = p1 + p2;
            stack.push(Double.toString(result));

        } else if (operator.contentEquals("-")) {

            result = p1 - p2;
            stack.push(Double.toString(result));

        }
    }

    /** Switch method for function that needs one parameter. */
    private void switchFunctions(Stack<String> stack, String operator) throws Exception {

        double result;
        // pop one parameter from stack.
        if (stack.isEmpty()) { throw new Exception("no correct counts in stack for function"); }
        double p = Double.parseDouble(stack.pop());

        if (operator.contentEquals("sin")) {

            result = Math.sin(p);
            stack.push(Double.toString(result));

        } else if (operator.contentEquals("cos")) {

            result = Math.cos(p);
            stack.push(Double.toString(result));

        } else if (operator.contentEquals("tan")) {

            result = Math.tan(p);
            stack.push(Double.toString(result));

        } else if (operator.contentEquals("atan")) {

            result = Math.atan(p);
            stack.push(Double.toString(result));

        } else if (operator.contentEquals("log2")) {

            if (p == 0) {
                throw new Exception("log2 has zero value");
            }
            if (p < 0) {
                throw new Exception("log2 has negative value");
            }
            result = Math.log(p) / Math.log(2);
            stack.push(Double.toString(result));

        } else if (operator.contentEquals("log10")) {

            if (p == 0) {
                throw new Exception("log10 has zero value");
            }
            if (p < 0) {
                throw new Exception("log10 has negative value");
            }
            result = Math.log10(p);
            stack.push(Double.toString(result));

        } else if (operator.contentEquals("sqrt")) {

            if (p < 0) {
                throw new Exception("sqrt has negative value");
            }
            result = Math.sqrt(p);
            stack.push(Double.toString(result));
        }
    }
}