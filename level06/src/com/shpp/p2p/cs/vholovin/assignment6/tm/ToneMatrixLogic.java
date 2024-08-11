package com.shpp.p2p.cs.vholovin.assignment6.tm;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {

        double[] result = new double[ToneMatrixConstants.sampleSize()];

        // count number of sounds
        int k = sumResult(toneMatrix, column, samples, result);

        // normalization result amplitudes
        if (k != 0) {
            normalizationResult(result);
        }

        return result;
    }

    /**
     * This method save sounds in active tone matrix to array, sum result amplitudes of array sounds
     * and return count of sounds.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @param result     A non normalization sound sample corresponding to all notes currently being played.
     *                   Array result uses pointers Pass-by-address.
     * @return A count number of sound sample being played.
     */
    private static int sumResult(boolean[][] toneMatrix, int column, double[][]samples, double[] result)
    {
        double[][] sound = new double[samples.length][samples[column].length];
        int k = 0;

        // Save sounds in active tone matrix to array
        for (int i = 0; i < toneMatrix[column].length; i++) {
            if (toneMatrix[i][column]) {
                sound[k] = samples[i];
                k++;
            }
        }

        // sum result amplitudes of array sounds
        for (int j = 0; j < k; j++) {
            for (int i = 0; i < result.length; i++) {
                result[i] += sound[j][i];
            }
        }
        return k;
    }

    /**
     * This method to normalization amplitudes on result of sum sounds.
     * Searches max value from amplitudes and divisions it to all value of amplitudes.
     *
     * @param result non normalization amplitudes of result. Array uses pointers Pass-by-address.
     */
    private static void normalizationResult(double[] result) {

        // Search max amplitude
        double max = result[0];
        for (int i = 0; i < result.length; i++) {
            if (Math.abs(max) < Math.abs(result[i])) {
                max = result[i];
            }
        }

        // Normalization only when |max| > 1
        if (Math.abs(max) > 1.0) {
            // Normalization - division amplitude to max value of amplitude
            for (int i = 0; i < result.length; i++) {
                result[i] /= max;
            }
        }
    }
}
