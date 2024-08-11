package com.shpp.p2p.cs.vholovin.assignment6.sg;

import acm.graphics.*;

import java.awt.*;

public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {

        int[][] arraySource = source.getPixelArray();
        boolean[][] arrayBoolean = new boolean[arraySource.length][arraySource[0].length];

        // Search even/odd number in red chanel
        for (int i = 0; i < arraySource.length; i++) {
            for (int j = 0; j < arraySource[i].length; j++) {
                arrayBoolean[i][j] = GImage.getRed(arraySource[i][j]) % 2 != 0;
            }
        }

        return arrayBoolean;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {

        int[][] arraySource = source.getPixelArray();

        // Calculate color in pixels
        for (int i = 0; i < arraySource.length; i++) {
            for (int j = 0; j < arraySource[i].length; j++) {
                arraySource[i][j] = setNewColor(new Color(arraySource[i][j]), message[i][j]).getRGB();
            }
        }

        return new GImage(arraySource);
    }

    /**
     * This method writes red color in pixel to even value
     * and write to odd value when pixel be had hidden message.
     *
     * @param oldColor The source color of pixel.
     * @param isMessage The message to hide pixel.
     * @return A new Color of pixel with the hidden message in it.
     */
    private static Color setNewColor(Color oldColor, boolean isMessage) {

        int red = oldColor.getRed();

        // make all pixel in red chanel to even value
        if (red % 2 != 0) {
            red--;
        }

        //  Make pixel in red chanel to odd value If it is must be hidden message
        if (isMessage) {
            red++;
        }

        return new Color(red, oldColor.getGreen(), oldColor.getBlue());
    }
}
