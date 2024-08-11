package com.shpp.p2p.cs.vholovin.assignment5;

import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class searches in dictionary possible words from 3 letter of american car numbers .
 */
public class Assignment5Part3 extends TextProgram {

    /* Path to dictionary file. */
    private static final String PATH = "src\\com\\shpp\\p2p\\cs\\vholovin\\assignment5\\en-dictionary.txt";

    /* Choose - output all result or one random from all result. */
    private static final Boolean OUTPUT_ALL = true;

    /**
     * This method reads the file once
     * and infinitely many times from the keyboard,
     * checking that the input is correct.
     */
    public void run() {
        // read file and write to array words
        File file = new File(PATH);
        ArrayList<String> words = readDictionary(file);

        // if array is not empty
        if (!words.isEmpty()) {
            while (true) {
                outputResult(words);
            }
        }
    }

    /**
     * Method reads the value from the keyboard, sends input value to the check,
     * compare letters from word dictionary, and outputs the result.
     *
     * @param words - array of words from file dictionary.
     */
    private void outputResult(ArrayList<String> words) {

        String number = readLine("Enter a 3 letter: ").toLowerCase();

        if (checkInput(number)) {

            // cast position only letters
            ArrayList<String> word = searchInDictionary(words, number);

            //output all or one random from all
            if (!word.isEmpty()) {
                if (OUTPUT_ALL) {
                    println("  word: " + word);
                } else {
                    //RandomGenerator.getInstance() - for found words and an index generator
                    println("  word: " + word.get(RandomGenerator.getInstance().nextInt(0, word.size() - 1)));
                }
            } else
                println("  word: " + "not search");

            // clear result
            word.clear();
        } else {
            println("wrong length of number");
        }

    }

    /**
     * Method checks input line to have length 3 numbers and compare with letters: a-z.
     *
     * @param number - input line for check.
     * @return true if line has 3 letter.
     */
    private boolean checkInput(String number) {
        if (number.length() != 3) {
            return false;
        }

        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) < 'a' || number.charAt(i) > 'z') {
                return false;
            }
        }
        return true;
    }

    /**
     * This method open, read and close file.
     *
     * @param file - input file for read.
     * @return array of read words.
     */
    private ArrayList<String> readDictionary(File file) {
        ArrayList<String> words = new ArrayList<>();
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));

            String str;
            while ((str = br.readLine()) != null) {
                str = str.toLowerCase();
                words.add(str);
            }
            br.close();

        } catch (IOException e) {
            println("error to open and read file");
        }
        return words;
    }

    /**
     * This method matches all letters in an array of available words.
     *
     * @param words   - array of possible words to check.
     * @param letters - letters to search for words.
     * @return an array of words where a match was found.
     */
    private ArrayList<String> searchInDictionary(ArrayList<String> words, String letters) {
        ArrayList<String> word = new ArrayList<>();

        for (String s : words) {
            int k = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == letters.charAt(k)) {
                    k++;
                    if (k == letters.length()) {
                        break;
                    }
                }
            }
            // If all the letters match, one of the words has been found
            if (k == letters.length()) {
                word.add(s);
            }
        }
        return word;
    }
}