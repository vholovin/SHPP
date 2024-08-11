package com.shpp.p2p.cs.vholovin.assignment2;

import com.shpp.cs.a.console.TextProgram;

/**
 * This class solves a simplified quadratic equation : a * x^2 + b * x + c = 0.
 * There are three constants ('a', 'b', 'c') which accept by the terminal.
 * The class outputs the solution as a text message to the terminal.
 * There is some certain simplifications - it does not count complex numbers : i = sqrt(-1).
 */
public class Assignment2Part1 extends TextProgram {

    /** There are three constants for quadratic equation. */
    private final double a;
    private final double b;
    private final double c;

    /**
     * Default constructors, which reads the input parameters('a', 'b', 'c') for quadratic equation.
     */
    public Assignment2Part1() {
        this.a = readDouble("Please enter a: ");
        this.b = readDouble("Please enter b: ");
        this.c = readDouble("Please enter c: ");
    }

    /* This method checks the input constants and directs them to the appropriate solutions.
     */
    public void run() {
        if ((this.a != 0) && (this.b != 0) && (this.c != 0)) {
            fullQuadraticEquation();
        } else if (this.a != 0) {
            incompleteQuadraticEquation();
        } else {
            otherLinearEquations();
        }
    }

    /* This method solves the full quadratic equation by finding the discriminant.
     * Complex number are limited.
     */
    void fullQuadraticEquation() {
        double D = this.b * this.b - 4 * this.a * this.c;

        if (D < 0) {
            println("There are no real roots");
        } else if (D == 0) {
            double x = -(this.b / (2.0 * this.a));
            println("There is one root: " + x);
        } else if (D > 0) {
            D = Math.sqrt(D);
            double x1 = (-this.b - D) / (2.0 * this.a);
            double x2 = (-this.b + D) / (2.0 * this.a);

            if (Double.isNaN(x1) && Double.isNaN(x2)) {
                println("There are any roots");
            } else if (Double.isInfinite(x1) && Double.isInfinite(x2)) {
                println("There are no roots");
            } else {
                println("There are two roots: " + x1 + " and " + x2);
            }
        }
    }

    /* This method solves the incomplete quadratic equation, when some constant ('b' and/or 'c') equal zero.
     */
    void incompleteQuadraticEquation() {
        if ((this.b != 0) && (this.c == 0)) {
            double x = -this.b / this.a;
            println("There are two roots: " + (double)0 + " and " + x);
        }

        if ((this.b == 0) && (this.c != 0)) {
            double x = -this.c / this.a;
            if (x > 0)
                println("There are two roots: " + (-Math.sqrt(x)) + " and " + (Math.sqrt(x)));
            else if (x < 0)
                println("There are no roots");
        }

        if ((this.b == 0) && (this.c == 0)) {
            println("There is one root: " + (double)0);
        }
    }

    /* This method solves linear equations where the constant a = 0.
     * And another cases when some constant ('b' and/or 'c') equal zero.
     */
    void otherLinearEquations() {
        if ((this.b != 0) && (this.c != 0)) {
            double x = -this.c / this.b;
            println("There is one root: " + x);
        }

        if ((this.b != 0) && (this.c == 0)) {
            println("There is one root: " + (double)0);
        }

        if ((this.b == 0) && (this.c != 0)) {
            println("There are no roots");
        }

        if ((this.b == 0) && (this.c == 0)) {
            println("There are any roots");
        }
    }
}