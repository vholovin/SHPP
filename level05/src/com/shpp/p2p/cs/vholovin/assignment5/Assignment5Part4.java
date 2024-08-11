package com.shpp.p2p.cs.vholovin.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * CSV parsing:
 * - opens a file
 * - reads the string
 * - splits into columns by comma
 * - parsing quotes marks
 * - returns array with the set index per column
 */
public class Assignment5Part4 extends TextProgram {

    /* Path to CSV file. */
    private static final String PATH = "src\\com\\shpp\\p2p\\cs\\vholovin\\assignment5\\food-origins.csv";

    /**
     * Launching the method and outputting its result.
     */
    public void run() {

        ArrayList<String> words = extractColumn(PATH, 1);

        if (words != null) {
            for (String word : words) {
                println(word);
            }
        }
    }

    /*
     * This method opens a file and reads the rows, splits into columns,
     * and returns an array with the set index per column.
     *
     * @param filename - input file name for read file.
     * @param columnIndex - index for column.
     * @return array of string from column index.
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {

        // if file not opened
        ArrayList<String> scv = fieldsIn(filename);
        if (scv.isEmpty())
            return null;

        // SCV parsing
        ArrayList<String> words = new ArrayList<>();
        for (String s : scv) {
            words.add(splitString(s).get(columnIndex));
        }
        return words;
    }

    /*
     * This method takes a single whole string and splits it to array by commas. Also checks for quotes marks.
     * If there are quotes marks in the first element of the split - add to this index of split next index of split
     * and return comma between them. And remove next index of split.
     *
     * @param s - input one string from array for split.
     * @return ar array of stings, witch splitting by commas.
     */
    private ArrayList<String> splitString(String s)
    {
        //array of split by comma
        String[] split = s.split(",");

        //convert array to ArrayList for easy work
        ArrayList<String> tmp = new ArrayList<>(Arrays.asList(split));

        //search quotes mark in start position of split
        for (int j = 0; j < tmp.size(); j++) {
            if (tmp.get(j) != null && tmp.get(j).charAt(0) == '"') {

                //return comma and add next index of split
                String tmpNew = tmp.get(j).subSequence(1, tmp.get(j).length()) +
                        "," +
                        tmp.get(j + 1).subSequence(0, tmp.get(j + 1).length() - 1);

                tmp.set(j, tmpNew);
                tmp.remove(j + 1);
            }
        }
        return tmp;
    }


    /*
     * This method open, read and close file.
     *
     * @param line - input file name for read file.
     * @return an array of strings.
     */
    private ArrayList<String> fieldsIn(String line) {
        File file = new File(line);
        ArrayList<String> words = new ArrayList<>();
        BufferedReader br;

        // try to work with file
        try {
            br = new BufferedReader(new FileReader(file));

            // try to read file
            try {
                String str;
                while ((str = br.readLine()) != null) {
                    words.add(str);
                }
            } catch (IOException e) {
                println("error to read file");
            }

            br.close();

        } catch (IOException e) {
            println("error to open file");
        }

        return words;
    }
}