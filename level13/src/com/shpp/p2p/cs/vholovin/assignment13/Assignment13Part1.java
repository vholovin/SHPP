package com.shpp.p2p.cs.vholovin.assignment13;

/**
 * This class looks for silhouettes in an image - part 2.
 * Use the terminal command to run it.
 * If there is no parameter at the input, it is started with a test file.
 * Set in VM option "-Xss1024m"
 */
public class Assignment13Part1 implements Constants {

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
     * 3. setting the size for sifting out the trash
     * 4. run Breadth-first Search algorithm
     * 5. Search and set background
     *
     * @param fileName this is the name of the file to be processed.
     * @return number of silhouettes.
     */
    private static String calculate(String fileName) {

        Silhouette silhouette = new Silhouette(fileName);

        silhouette.isAlpha();
        Log.debug("is alpha: " + silhouette.getIsAlpha());

        silhouette.toGrayscale();
        Log.debug("convert color to grayscale...");

        silhouette.setTrashSize();
        Log.debug("size trash: " + silhouette.getSizeTrash());

        silhouette.toSearchSilhouettes();
        Log.debug("search silhouettes...");

        silhouette.outputCountSilhouettes();
        Log.debug("found silhouettes: " + silhouette.getCountSilhouette());

        silhouette.removeBackground();
        Log.debug("remove background...");
        Log.debug("background color: " + silhouette.getBackgroundColor());
        Log.debug("background size: " + silhouette.getBackgroundSize());

        Log.debug("update silhouettes...");
        silhouette.outputCountSilhouettes();
        Log.debug("found silhouettes: " + silhouette.getCountSilhouette());

        return Integer.toString(silhouette.getCountSilhouette());
        }
}