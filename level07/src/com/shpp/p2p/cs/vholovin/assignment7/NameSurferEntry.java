package com.shpp.p2p.cs.vholovin.assignment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.ArrayList;

public class NameSurferEntry implements NameSurferConstants {

    /** Name of one database. */
    private final String nameArray;

    /** Values of one database. */
    private final ArrayList<Integer> dataArray = new ArrayList<>();

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file. Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {

        String[] array = line.split(" ");

        nameArray = array[0];
        for (int i = 1; i < array.length; i++) {
            dataArray.add(Integer.parseInt(array[i]));
        }
    }

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return nameArray;
    }

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return dataArray.get(decade);
    }

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        StringBuilder stringValue = new StringBuilder();

        stringValue.append(nameArray).append(" [");

        for (int i = 0; i < dataArray.size(); i++) {
            stringValue.append(dataArray.get(i));

            if (i != dataArray.size() - 1) {
                stringValue.append(" ");
            }
        }
        stringValue.append("]");

        return stringValue.toString();
    }
}

