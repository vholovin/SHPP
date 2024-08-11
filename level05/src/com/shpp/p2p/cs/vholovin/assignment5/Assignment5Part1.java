package com.shpp.p2p.cs.vholovin.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * This class counts the number of syllables.
 */
public class Assignment5Part1 extends TextProgram {
    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesIn(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {

        // Need to lower case for convenience
        word = word.toLowerCase();

        int count = 0;
        boolean isLastVowel = false;

        for (int i = 0; i < word.length(); i++) {

            // in first - check letter to a vowels
            // in second - cut off the last letter 'e' in word
            if (isVowel(word.charAt(i))
                    && !((word.charAt(i) == 'e') && (i == word.length() - 1))) {

                // if it is the first vowel letter after the consonants then count++
                if (!isLastVowel) {
                    count++;
                    isLastVowel = true;
                }

            } else {
                isLastVowel = false;
            }
        }

        // a word always has 1 syllable
        if (count == 0){
            count++;
        }

        return count;
    }

    /**
     * This method checks a letter for a vowel.
     *
     * @param l - letter for check to a vowels;
     * @return - TRUE if there was a match with a vowels letter.
     */
    private boolean isVowel(char l) {
        return (l == 'a' || l == 'e' || l == 'i' || l == 'o' || l == 'u' || l == 'y');
    }

}