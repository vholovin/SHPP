package com.shpp.p2p.cs.vholovin.assignment13;

/**
 * This class is for nicely outputting information to the terminal
 */
public class Log {

    /** setup for text color **/
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    /** reset color to default **/
    public static final String ANSI_RESET = "\u001B[0m";

    /** green for result **/
    public static void result(String message) {
        System.out.println(ANSI_GREEN + "RESULT: " + ANSI_RESET + message);
    }

    /** red for error **/
    public static void error(String message) {
        System.out.println(ANSI_RED + "ERROR: " + ANSI_RESET + message);
    }

    /** blue for debug **/
    public static void debug(String message) {
        System.out.println(ANSI_BLUE + "DEBUG: " + ANSI_RESET + message);
    }
}