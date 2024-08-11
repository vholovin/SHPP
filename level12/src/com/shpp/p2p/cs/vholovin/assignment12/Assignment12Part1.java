package com.shpp.p2p.cs.vholovin.assignment12;

/**
 * This class looks for silhouettes in an image - part 1.
 * Use the terminal command to run it.
 * If there is no parameter at the input, it is started with a test file.
 * Set in VM option "-Xss1024m"
 */
public class Assignment12Part1 implements Constants {

    /**
     * If there is no parameter at the input, it is started with a test file.
     * @param args use one file.
     */
    public static void main(String[] args) {

        try {
            if (args.length > 1) {
                throw new Exception("use one file");

            } else if (args.length == 1) {
                Log.debug("file from terminal: " + args[0]);
                Log.result(calculate(args[0]));

            } else {
                Log.debug("file from constants: " + TEST_FILE);
                Log.result(calculate(TEST_FILE));
            }

        } catch (Exception message) {
            Log.error(message.toString());
        }
    }

    /**
     * This method sequentially outputs service info and work stages:
     * 1. determine whether there is an alpha channel
     * 2. convert RGBA to GrayScale
     * 3. Search and set background color
     * 4. setting the size for sifting out the trash
     * 5. run Depth-first Search algorithm
     *
     * @param fileName this is the name of the file to be processed.
     * @return number of silhouettes.
     */
    private static String calculate(String fileName) {

        Silhouette silhouette = new Silhouette(fileName);

        silhouette.isAlpha();
        Log.debug("is alpha: " + silhouette.getIsAlpha());

        silhouette.toGrayscale();
        Log.debug("convert color to grayscale");

        silhouette.setBackgroundColor();
        Log.debug("background color: " + silhouette.getBackgroundColor());

        silhouette.setSizeTrash();
        Log.debug("size trash: " + silhouette.getSizeTrash());

        silhouette.toDepthFirstSearch();
        Log.debug("search silhouettes");

        return Integer.toString(silhouette.getCount());
        }
}