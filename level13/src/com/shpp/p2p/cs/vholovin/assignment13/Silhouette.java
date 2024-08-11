package com.shpp.p2p.cs.vholovin.assignment13;

import acm.graphics.GImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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

    /** size for filtering trash **/
    private int trashSize;

    /** intermediate parameters for calculate size and color of vertexes **/
    int countVertexesSize;
    int countVertexesColor;

    /** final size and color of vertexes **/
    ArrayList<Integer> vertexesSize = new ArrayList<>();
    ArrayList<Integer> vertexesColor = new ArrayList<>();

    /** size and color of background **/
    private int backgroundSize;
    private int backgroundColor;

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
     * @return true when image has alpha chanel
     */
    public boolean getIsAlpha() {

        return isAlpha;
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
     * Set the maximum limit for the size of the trash.
     * It is taken as a percentage of the total image size.
     */
    public void setTrashSize() {
        trashSize = (int) (pixel.length * pixel[0].length * PERCENTAGE_TRASH_ELEMENT);

        // for case when very small image
        if (trashSize == 0) {
            trashSize = DEFAULT_TRASH_ELEMENT;
        }
    }

    /**
     * @return size for filtering trash
     */
    public int getSizeTrash() {

        return trashSize;
    }

    /**
     * cycle to go through all the pixels where the Breadth-first search did not reach
     */
    public void toSearchSilhouettes() {

        for (int i = 0; i < pixel.length; i++) {
            for (int j = 0; j < pixel[i].length; j++) {

                // reset parameters and start the search
                countVertexesSize = 0;
                countVertexesColor = 0;

                toBreadthFirstSearch(i, j);

                // sort the trash
                if (countVertexesSize >= trashSize) {

                    vertexesSize.add(countVertexesSize);
                    vertexesColor.add(countVertexesColor);
                }
            }
        }
    }

    /**
     * This method uses a queue (First In First Out) and
     * it checks whether a vertex has been explored before enqueueing the vertex rather
     * than delaying this check until the vertex is dequeued from the queue.
     */
    private void toBreadthFirstSearch(int startPosX, int startPosY) {

        // we check the coordinates, whether there was an algorithm here
        if (isVisited[startPosX][startPosY]) { return; }

        // create a queue array and add the first vertex to the queue
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{startPosX, startPosY});

        Integer[] vertex;
        int       posX, posY;

        while (! queue.isEmpty()) {

            // extract and remove the last element from the queue
            vertex = queue.remove();
            posX = vertex[0];
            posY = vertex[1];

            // we check the coordinates, whether there was an algorithm here
            if (! isVisited[posX][posY]) {

                isVisited[posX][posY] = true;
                countVertexesSize++;
                countVertexesColor += pixel[posX][posY];

                addToQueueNextVertexes(queue, posX, posY);
            }
        }

        // the average value of the color of the silhouette
        countVertexesColor /= countVertexesSize;
    }

    /**
     * Check the vertexes in 8 directions and add them to the queue.
     *    \ | /
     *    - 0 -
     *    / | \
     */
    private void addToQueueNextVertexes(Queue<Integer[]> queue, int posX, int posY) {

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

                        //add next pixel to queue
                        queue.add(new Integer[]{nextPosX, nextPosY});
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
     * Outputs a stored array of silhouette sizes and their average color.
     */
    public void outputCountSilhouettes() {

        for (int i = 0; i < vertexesSize.size(); i++) {
            Log.debug("VERTEXES: " + vertexesSize.get(i) + " PIXEL COLOR: " + vertexesColor.get(i));
        }
    }

    /**
     * @return number of silhouettes
     */
    public int getCountSilhouette() {

        return vertexesSize.size();
    }

    /**
     * This method search background and
     * removes those vertexes that are equal in color.
     */
    public void removeBackground() {

        searchBackground();
        removeEqualBackgroundVertexes();
    }

    /**
     * Search color for the largest size from array of vertexes.
     */
    public void searchBackground() {

        int backgroundIndex = 0;
        backgroundSize = 0;

        for (int i = 0; i < vertexesSize.size(); i++) {

            if (vertexesSize.get(i) > backgroundSize) {

                backgroundIndex = i;
                backgroundSize = vertexesSize.get(backgroundIndex);
            }
        }

        backgroundColor = vertexesColor.get(backgroundIndex);
        vertexesSize.remove(backgroundIndex);
        vertexesColor.remove(backgroundIndex);
    }

    /**
     * Removes from the array of vertexes those elements
     * that equals the background color.
     */
    public void removeEqualBackgroundVertexes() {

        for (int i = vertexesColor.size() - 1; i >= 0; i--) {

            if (checkEqualColor(vertexesColor.get(i), backgroundColor)) {

                backgroundSize += vertexesSize.get(i);
                vertexesSize.remove(i);
                vertexesColor.remove(i);
            }
        }
    }

    /**
     * @return color of background
     */
    public int getBackgroundColor() {

        return backgroundColor;
    }

    /**
     * @return size of background
     */
    public int getBackgroundSize() {

        return backgroundSize;
    }
}
