package com.shpp.p2p.cs.vholovin.assignment8;

import acm.graphics.GObject;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Main class for work this program.
 */
public class Exam extends WindowProgram implements ExamConstants {

    /** Current window width and height for dynamics windows. */
    private int windowWidth;
    private int windowHeight;

    /** Array of class for boxes. */
    private final ArrayList<ExamBox> arrayBoxes = new ArrayList<>();

    /** Blue and Red class switchers. */
    private final ExamSwitch switchBlue;
    private final ExamSwitch switchRed;

    /** Object over which the mouse is located and its index. */
    private GObject mouseColl;
    private int mouseIndex;

    /**
     * Default construction for window size and index|direction|color for switcher.
     */
    public Exam() {
        windowWidth = 0;
        windowHeight = 0;

        RandomGenerator rGen = RandomGenerator.getInstance();
        // random positions for switchers
        int indexBlue = rGen.nextInt(0, NUM_BOXES - 1);
        int indexRed = rGen.nextInt(0, NUM_BOXES - 1);
        // if the positions match
        while (indexBlue == indexRed) {
            indexBlue = rGen.nextInt(0, NUM_BOXES - 1);
        }

        // random directions for switchers
        boolean directionUpBlue = rGen.nextBoolean(0.5);
        boolean directionUpRed = rGen.nextBoolean(0.5);

        switchBlue = new ExamSwitch(indexBlue, directionUpBlue, Color.BLUE);
        switchRed = new ExamSwitch(indexRed, directionUpRed, Color.RED);
    }

    /**
     * Run method with while cycle.
     */
    public void run() {
        addMouseListeners();

        while (true) {
            if (checkWindowSize()) {
                initArrayBoxes();
            }
            removeAll();
            switchColorBoxes();
            displayArrayBoxes();
            pause(PAUSE);
        }
    }

    /**
     * Save GObject of collision boxes array and mouse position.
     */
    public void mouseMoved(MouseEvent me) {
        mouseColl = getElementAt(me.getX(), me.getY());
    }

    /**
     * Dynamics windows.
     */
    private boolean checkWindowSize() {
        if ((windowWidth != getWidth()) || (windowHeight != getHeight())) {
            windowWidth = getWidth();
            windowHeight = getHeight();
            return (true);
        }
        return (false);
    }

    /**
     * Init positions and size of boxes arrays.
     */
    private void initArrayBoxes() {

        double posX;
        double posY;
        double sizeBoxX = (double) getWidth() / NUM_ROWS;
        double sizeBoxY = (double) getHeight() / NUM_COLS;

        arrayBoxes.clear();
        for (int i = 0; i < NUM_BOXES; i++) {


            if (i < (NUM_ROWS - 1)) {
                //up boxes
                posX = sizeBoxX * i;
                posY = 0;

            } else if (i < ((NUM_ROWS - 1) + (NUM_COLS - 1))) {
                //right boxes
                int k = i - (NUM_ROWS - 1);
                posX = getWidth() - sizeBoxX;
                posY = sizeBoxY * k;

            } else if (i < (2 * (NUM_ROWS - 1) + (NUM_COLS - 1))) {
                //down boxes
                int k = i - ((NUM_ROWS - 1) + (NUM_COLS - 1));
                posX = getWidth() - sizeBoxX - sizeBoxX * k;
                posY = getHeight() - sizeBoxY;

            } else {
                //left boxes
                int k = i - (2 * (NUM_ROWS - 1) + (NUM_COLS - 1));
                posX = 0;
                posY = getHeight() - sizeBoxY - sizeBoxY * k;

            }
            arrayBoxes.add(new ExamBox(posX, posY, sizeBoxX, sizeBoxY));
        }
    }

    /**
     * Draw all boxes from arrays.
     */
    private void displayArrayBoxes() {

        for (int i = 0; i < arrayBoxes.size(); i++) {

            // blue color
            if (i == switchBlue.getIndex()) {
                add(arrayBoxes.get(i).getRect(switchBlue.getColor()));
                // red color
            } else if (i == switchRed.getIndex()) {
                add(arrayBoxes.get(i).getRect(switchRed.getColor()));
                // default color
            } else {
                add(arrayBoxes.get(i).getRect());
            }

        }
    }

    /**
     * Method that controls the switchers.
     */
    private void switchColorBoxes() {

        checkRedBlueCollision();

        if (isMouseCollision()) {
            checkMouseBoxCollision(switchBlue);
            checkMouseBoxCollision(switchRed);
        }

        switchBlue.updateIndex();
        switchRed.updateIndex();
    }

    /**
     * Checks collision with switcher red and blue boxes.
     */
    private void checkRedBlueCollision() {
        int diffRedBlue = switchBlue.getIndex() - switchRed.getIndex();

        // Collision index boxes and their direction
        if (Math.abs(diffRedBlue) <= DIFF_INDEX_COLLISION_BOXES) {
            if ((diffRedBlue < 0 && switchBlue.getDirectionUp() && !switchRed.getDirectionUp()) ||
                    (diffRedBlue > 0 && !switchBlue.getDirectionUp() && switchRed.getDirectionUp())) {
                switchBlue.changeDirection();
                switchRed.changeDirection();
            }
        }
    }

    /**
     * Check collision in boxes arrays and save index of mouse position.
     */
    private boolean isMouseCollision() {
        if (mouseColl != null) {
            int mouseCollHash = mouseColl.hashCode();
            // searches index of boxes where is the mouse position
            for (int i = 0; i < arrayBoxes.size(); i++) {
                if (mouseCollHash == arrayBoxes.get(i).getRect().hashCode()) {
                    mouseIndex = i;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks collision with mouse and input switcher box.
     */
    private void checkMouseBoxCollision(ExamSwitch switcher) {
        int diffMouseBlue = switcher.getIndex() - mouseIndex;

        // Check collision when indexes mouse and box equals 0 and max index;
        if ((diffMouseBlue == NUM_BOXES - 1) && switcher.getDirectionUp()) {
            diffMouseBlue = -DIFF_INDEX_COLLISION_MOUSE;
        } else if ((diffMouseBlue == -(NUM_BOXES - 1)) && !switcher.getDirectionUp()) {
            diffMouseBlue = DIFF_INDEX_COLLISION_MOUSE;
        }
        // Collision blue box and mouse
        if (Math.abs(diffMouseBlue) <= DIFF_INDEX_COLLISION_MOUSE) {
            if ((diffMouseBlue < 0 && switcher.getDirectionUp()) ||
                    (diffMouseBlue > 0 && !switcher.getDirectionUp())) {
                switcher.changeDirection();
            }
        }
    }
}
