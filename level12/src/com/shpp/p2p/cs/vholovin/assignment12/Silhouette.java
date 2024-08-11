package com.shpp.p2p.cs.vholovin.assignment12;

import acm.graphics.GImage;

/**
 * The main class that looks for silhouettes in the image.
 */
public class Silhouette implements Constants {

    /** an array of pixels for one grayscale color **/
    private final int[][] pixel;

    /** true when image has alpha chanel **/
    private boolean isAlpha;

    /** an array to mark where the recursion has already been **/
    private final boolean[][] isVisited;

    /** color of background **/
    private int backgroundColor;

    /** size for filtering trash **/
    private int sizeTrash;

    /** search depth **/
    private int depth;

    /** number of silhouettes **/
    private int count;

    /**
     * Default constructor for creating an array of pixels and their visits.
     * @param fileName image file.
     */
    public Silhouette(String fileName) {

        GImage image = new GImage(fileName);
        pixel = image.getPixelArray();
        isVisited = new boolean[pixel.length][pixel[0].length];
    }

    /**
     * If the alpha channel in the first pixel differs somewhere in next pixels,
     * it means that there is a different alpha channel values in pixels.
     */
    public void isAlpha() {

        int alphaIndex = GImage.getAlpha(pixel[0][0]);
        isAlpha = false;

        for (int i = 0; i < pixel.length; i++) {
            for (int j = 0; j < pixel[i].length; j++) {

                if (alphaIndex != GImage.getAlpha(pixel[i][j])) {
                    isAlpha = true;
                    break;
                }
            }
        }
    }

    /**
     * Convert RGB/RGBA to Grayscale Color Conversion
     * Constants from here:
     * <a href="https://spec.oneapi.io/oneipl/latest/convert/rgb-gray-conversion.html">...</a>
     */
    public void toGrayscale() {

        int red, green, blue, alpha;

        for (int i = 0; i < pixel.length; i++) {
            for (int j = 0; j < pixel[i].length; j++) {

                red = GImage.getRed(pixel[i][j]);
                green = GImage.getGreen(pixel[i][j]);
                blue = GImage.getBlue(pixel[i][j]);
                alpha = GImage.getAlpha(pixel[i][j]);

                if (isAlpha) {
                    pixel[i][j] = alpha;

                } else {
                    pixel[i][j] = (int) (0.299D * red + 0.587D * green + 0.114D * blue);
                }

//                Log.debug("GRAYSCALE: CORD: " + i + "," + j +
//                        " BEFORE: " + red + "," + green + "," + blue + "," + alpha +
//                        " AFTER: " + pixel[i][j]);
            }
        }
    }

    /**
     * the background color is taken as an eyedropper from one corner of the image
     */
    public void setBackgroundColor() {

        backgroundColor = pixel[pixel.length - 1][pixel[0].length - 1];
    }

    /**
     * Set the maximum limit for the size of the trash.
     * It is taken as a percentage of the total image size.
     */
    public void setSizeTrash() {
        sizeTrash = (int) (pixel.length * pixel[0].length * PERCENTAGE_TRASH_ELEMENT);

        // for case when very small image
        if (sizeTrash == 0) {
            sizeTrash = DEFAULT_TRASH_ELEMENT;
        }
    }

    /**
     * cycle to go through all the pixels where the deep search did not reach
     */
    public void toDepthFirstSearch() {

        // number of silhouettes
        count = 0;

        for (int i = 0; i < pixel.length; i++) {
            for (int j = 0; j < pixel[i].length; j++) {

                // reset the depth and start the search
                depth = 0;
                deepSearch(i, j);

                // sort the trash
                if (depth >= sizeTrash) {

                    // count only those results in which the color does not match the background
                    if (! checkEqualColor(pixel[i][j], backgroundColor)) {
                        count++;
                    }
//                    Log.debug("BACKGROUND COLOR: " + backgroundColor + " PIXEL COLOR: " + pixel[i][j] +
//                              " DEPTH: " + depth + " COUNT: " + count);
                }
            }
        }
    }

    /**
     * Depth-first search algorithm.
     * Searches in 8 directions:)
     *    \ | /
     *    - 0 -
     *    / | \
     */
    private void deepSearch(int posX, int posY) {

        // we check the coordinate whether there was already a recursion
        if (isVisited[posX][posY]) {
            return;
        } else {
            isVisited[posX][posY] = true;
        }

        int nextPosX, nextPosY;

        // cycle for 8 directions
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                nextPosX = posX + i;
                nextPosY = posY + j;

                // check next coordinate does not go out of image and whether the recursion was there to save time
                if (checkInImage(nextPosX, nextPosY) && ! isVisited[nextPosX][nextPosY]) {

                    // check this coordinate matches the next one in color
                    if (checkEqualColor(pixel[posX][posY], pixel[nextPosX][nextPosY])) {

                        //add depth and going to next pixel
                        depth++;
                        deepSearch(nextPosX, nextPosY);

//                      Log.debug("NEXT POS: " + nextPosX + "," + nextPosY +
//                                " DEPTH: " + depth +
//                                " COLOR:" + pixel[nextPosX][nextPosY]);
                    }
                }
            }
        }
    }

    /**
     * @return true if the coordinates are within the image
     */
    private boolean checkInImage(int nextPosX, int nextPosY) {

        return ((nextPosX >= 0 && nextPosX < pixel.length) &&
                (nextPosY >= 0 && nextPosY < pixel[0].length));
    }

    /**
     * This method compares two colors with each other within the color error.
     * For cases to account for compression and smoothing artifacts.

     * @return true if the colors are equal within the set delta limit
     */
    private boolean checkEqualColor(int firstPixelColor, int secondPixelColor) {

        return (Math.abs(firstPixelColor - secondPixelColor) <= DELTA_NOISE_COLOR);
    }

    /**
     * @return true when image has alpha chanel
     */
    public boolean getIsAlpha() {

        return isAlpha;
    }

    /**
     * @return color of background
     */
    public int getBackgroundColor() {

        return backgroundColor;
    }

    /**
     * @return size for filtering trash
     */
    public int getSizeTrash() {

        return sizeTrash;
    }

    /**
     * @return number of silhouettes
     */
    public int getCount() {

        return count;
    }
}
