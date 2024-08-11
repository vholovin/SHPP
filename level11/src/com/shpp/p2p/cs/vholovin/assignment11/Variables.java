package com.shpp.p2p.cs.vholovin.assignment11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/** Variables class for processing input parameters. */
public class Variables {

    /** HashMap class to save name and value of variables. */
    HashMap<String, Double> variables = new HashMap<>();

    /**
     * The default constructor for the variable class that receives an array of string for parsing.
     *
     * @param args unparsed names and values of the variables.
     */
    Variables(String[] args) throws Exception {
        parsVariables(args);
    }

    /**
     * This method parses the input string array to the name and value of the variables.
     *
     * @param args unparsed names and values of the variables.
     */
    private void parsVariables(String[] args) throws Exception {
        List<String> keyId = new ArrayList<>();
        List<Double> valueId = new ArrayList<>();
        String[] param;

        // variables start from the 1 index.
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
    }

    /**
     * This method parses the names of a variable.
     *
     * @param nameParse unparsed name of the variable.
     * @return parsed value of the variable.
     */
    private String parsVariableName(String nameParse) throws Exception {
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
    private double parsVariableValue(String valueParse) throws Exception {
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

                    if (valueParse.charAt(i) == '.') {
                        isFraction = true;
                    }
                    if (valueParse.charAt(i) == '-') {
                        isNegative = true;
                    }

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
     * Return parsed name and value variables.
     *
     * @return HashMap the variable.
     */
    public HashMap<String, Double> get() {
        return variables;
    }
}
