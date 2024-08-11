package com.shpp.p2p.cs.vholovin.assignment7;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.*;
import java.util.ArrayList;

public class NameSurferDataBase implements NameSurferConstants {

    /**
     * Array of lines from input database file.
     */
    public ArrayList<String> getLine = new ArrayList<>();

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {
        File file = new File(filename);
        String str;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((str = br.readLine()) != null) {
                getLine.add(str);
            }
        } catch (IOException e) {
            getLine = null;
        }
    }

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists. If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        NameSurferEntry dataLine = null;
        String nameSearch;

        if (getLine != null && name.length() != 0) {

            for (String nameLine : getLine) {
                nameSearch = nameLine.split(" ")[0];

                // The compareToIgnoreCase() converts to lower case two string value.
                // The method returns 0 if the string is equal to the other string, ignoring case differences.
                // A value less than 0 is returned if the string is less than the other string (fewer characters)
                // and a value greater than 0 if the string is greater than the other string (more characters).
                if (nameSearch.compareToIgnoreCase(name) == 0) {
                    dataLine = new NameSurferEntry(nameLine);
                    break;
                }
            }
        }
        return dataLine;
    }
}