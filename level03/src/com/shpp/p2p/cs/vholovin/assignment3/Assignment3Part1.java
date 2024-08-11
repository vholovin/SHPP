package com.shpp.p2p.cs.vholovin.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * This class checks the success of the patient's exercises.
 *
 * 30 minutes of aerobic exercise and 5 days a week is recommended to maintain cardiovascular health.
 *
 * 40 minutes of aerobic exercise and 3 days a week is recommended to maintain low blood pressure.
 */
public class Assignment3Part1 extends TextProgram {

    /* Constant for days in week. */
    public static final int     DAYS_IN_WEEK                        = 7;

    /* Constant for recommended for time and days for cardiovascular health. */
    public static final int     RECOMMENDED_TIME_CARDIO_HEALTH      = 30;
    public static final int     RECOMMENDED_DAYS_CARDIO_HEALTH      = 5;

    /* Constant for recommended for time and days for blood pressure. */
    public static final int     RECOMMENDED_TIME_BLOOD_PRESSURE     = 40;
    public static final int     RECOMMENDED_DAYS_BLOOD_PRESSURE     = 3;

    /** this is an array of type Int whose size is determined by the constant number of days in week. */
    private final int[]         timeInDay                           = new int[DAYS_IN_WEEK];

    /**
     * Default constructors, which reads the input parameters(minutes of day) for determined array.
     */
    public Assignment3Part1() {
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            this.timeInDay[i] = checkLine("How many minutes did you do on day " + (i + 1) + " ? ");
        }
    }

    /* This method checks input values.
     */
    int checkLine(String message) {
        String input;
        int number = 0;
        boolean isNumber = false;

        while (!isNumber) {
            input = readLine(message);
            try {
                number = Integer.parseInt(input);
                if (number < 0) {
                    println("There is not positive number");
                } else {
                    isNumber = true;
                }
            } catch (NumberFormatException e) {
                println("There is not Integer number");
            }
        }
        return number;
    }

    /* This method records the result numbers of days allocated for exercise and displays verdicts.
     */
    public void run() {
        int resultDaysCardioHealth = resultDays(RECOMMENDED_TIME_CARDIO_HEALTH);
        int resultDaysBloodPressure = resultDays(RECOMMENDED_TIME_BLOOD_PRESSURE);

        println();

        println("Cardiovascular health:");
        verdictDays(resultDaysCardioHealth, RECOMMENDED_DAYS_CARDIO_HEALTH, "for cardiovascular health");

        println("Blood pressure:");
        verdictDays(resultDaysBloodPressure, RECOMMENDED_DAYS_BLOOD_PRESSURE, "to keep a low blood pressure");
    }

    /*This method counts how many days have been completed more than the recommended time.
     */
    int resultDays(int recommendedTime) {
        int realDays = 0;
        //Using Enhanced for-each Loop.
        for (int element : this.timeInDay) {
            if (element >= recommendedTime) {
                realDays++;
            }
        }
        return realDays;
    }

    /* This method concludes whether enough time was spent on the exercises.
     * If not, it displays how many days are not enough to the recommended days.
     */
    void verdictDays(int resultDays, int recommendedDays, String partPositiveMessage) {
        if (resultDays >= recommendedDays) {
            println("  Great job! You've done enough exercise " + partPositiveMessage + ".");
        } else {
            println("  You needed to train hard for at least " + (recommendedDays - resultDays) + " more day(s) a week!");
        }
    }
}