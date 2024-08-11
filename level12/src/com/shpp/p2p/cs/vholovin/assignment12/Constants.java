package com.shpp.p2p.cs.vholovin.assignment12;

/**
 * Interface for constants
 */
public interface Constants {

    /* name and path to the test file */
    String FILE_PATH = "image_new.png";
    String FOLDER_PATH = "assets";
    String TEST_FILE = FOLDER_PATH + '/' + FILE_PATH;

    /* tolerance constant for brightness difference when comparing pixels */
    int DELTA_NOISE_COLOR = 5;

    /* the percentage of the garbage size tolerance from the total image size */
    double PERCENTAGE_TRASH_ELEMENT = 0.5 / 100;

    /* in case when the image size is very small */
    int DEFAULT_TRASH_ELEMENT = 10;
}
