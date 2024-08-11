package com.shpp.p2p.cs.vholovin.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    /**
     * Array of graphs to be displayed on the screen.
     */
    public ArrayList<NameSurferEntry> graphs = new ArrayList<>();

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
    }

    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        graphs.clear();
    }

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        boolean isCompare = false;

        if (entry != null) {
            for (NameSurferEntry graph : graphs) {
                if (entry.getName().compareToIgnoreCase(graph.getName()) == 0) {
                    isCompare = true;
                    break;
                }
            }

            if (!isCompare) {
                graphs.add(entry);
            }
        }
    }

    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        displayGrid();
        displayGraph();
    }

    /**
     * This method draws vertical and horizontal lines for the graphics grid.
     * And also labels the lines with the value of the decade.
     */
    private void displayGrid() {
        int posX;
        int posY;
        int year;

        for (int i = 0; i < NDECADES; i++) {

            // draw vertical line
            posX = getWidth() / NDECADES * i + GRAPH_OFFSET_X;
            GLine lineVertical = new GLine(posX, 0, posX, getHeight());
            add(drawLine(lineVertical, GRID_COLOR));

            // label vertical line
            year = START_DECADE + DECADE_YEARS * i;
            GLabel yearLabel = new GLabel("" + year);
            yearLabel.setLocation(posX + FONT_OFFSET_X, getHeight() + FONT_OFFSET_Y);
            add(drawLabel(yearLabel, GRID_COLOR, FONT_GRID_SIZE));
        }
        // draw horizontal up-position line
        posY = GRAPH_MARGIN_SIZE;
        GLine lineHorizontalUp = new GLine(0, posY, getWidth(), posY);
        add(drawLine(lineHorizontalUp, GRID_COLOR));

        // draw horizontal down-position line
        posY = getHeight() - GRAPH_MARGIN_SIZE;
        GLine lineHorizontalDown = new GLine(0, posY, getWidth(), posY);
        add(drawLine(lineHorizontalDown, GRID_COLOR));
    }

    /**
     * This method draws graphs and labels of their values.
     */
    private void displayGraph() {
        int indexColor;

        int posXStart;
        int posYStart;
        int rankStart;

        int posXEnd;
        int posYEnd;
        int rankEnd;

        // Calculate coefficient for scaling the ranks value to fit the values into the grid.
        double steepPerPixel = (getHeight() - 2 * GRAPH_MARGIN_SIZE) / (double) MAX_RANK;

        for (int k = 0; k < graphs.size(); k++) {

            // If the number of graphs in the array is greater than the array of color constants,
            // then we decrease its index.
            indexColor = k;
            while (indexColor > GRAPH_COLOR.length - 1) {
                indexColor -= GRAPH_COLOR.length;
            }

            for (int i = 1; i < NDECADES; i++) {

                posXStart = getWidth() / NDECADES * (i - 1) + GRAPH_OFFSET_X;
                posXEnd = getWidth() / NDECADES * i + GRAPH_OFFSET_X;

                rankStart = graphs.get(k).getRank((i - 1));
                rankEnd = graphs.get(k).getRank(i);

                if (rankStart == 0) {
                    posYStart = getHeight() - GRAPH_MARGIN_SIZE;
                } else {
                    posYStart = (int) (GRAPH_MARGIN_SIZE + rankStart * steepPerPixel);
                }

                if (rankEnd == 0) {
                    posYEnd = getHeight() - GRAPH_MARGIN_SIZE;
                } else {
                    posYEnd = (int) (GRAPH_MARGIN_SIZE + rankEnd * steepPerPixel);
                }

                drawGraphLines(posXStart, posYStart, rankStart, posXEnd, posYEnd, rankEnd, indexColor);
                drawGraphLabels(posXStart, posYStart, rankStart, posXEnd, posYEnd, rankEnd, indexColor, i, k);
            }
        }
    }

    /**
     * This method draws one graph line with given coordinates two points.
     * If two ranks equals zero then method does not draw lines.
     *
     * @param posXStart position X first point.
     * @param posYStart position Y first point.
     * @param rankStart rank value first point.
     * @param posXEnd position X last point.
     * @param posYEnd position Y last point.
     * @param rankEnd rank value last point.
     * @param indexColor index from array of color constants.
     */
    private void drawGraphLines(int posXStart, int posYStart, int rankStart,
                                int posXEnd, int posYEnd, int rankEnd,
                                int indexColor) {

        if (rankStart != 0 || rankEnd != 0) {
            GLine lineGraph = new GLine(posXStart, posYStart, posXEnd, posYEnd);
            add(drawLine(lineGraph, GRAPH_COLOR[indexColor]));
        }
    }

    /**
     * This method draws one label with given start coordinate.
     * if there is last decades then method draws one more label with given end coordinate.
     * If rank equals zero then method draws symbol "*" instead of the value.
     *
     * @param posXStart position X first point.
     * @param posYStart position Y first point.
     * @param rankStart rank value first point.
     * @param posXEnd position X last point.
     * @param posYEnd position Y last point.
     * @param rankEnd rank value last point.
     * @param indexColor index from array of color constants.
     * @param i index from values of decades.
     * @param k index from array of graphs.
     */
    private void drawGraphLabels(int posXStart, int posYStart, int rankStart,
                                int posXEnd, int posYEnd, int rankEnd,
                                int indexColor, int i, int k) {
        String infoLabel;

        infoLabel = graphs.get(k).getName() + " : " + ((rankStart == 0) ? "*" : rankStart);
        GLabel rankLabelStart = new GLabel(infoLabel);
        rankLabelStart.setLocation(posXStart + FONT_OFFSET_X, posYStart + FONT_OFFSET_Y);
        add(drawLabel(rankLabelStart, GRAPH_COLOR[indexColor], FONT_GRAPH_SIZE));

        // For last decades.
        if (i == NDECADES - 1) {
            infoLabel = graphs.get(k).getName() + " : " + ((rankEnd == 0) ? "*" : rankEnd);
            GLabel rankLabelEnd = new GLabel(infoLabel);
            rankLabelEnd.setLocation(posXEnd + FONT_OFFSET_X, posYEnd + FONT_OFFSET_Y);
            add(drawLabel(rankLabelEnd, GRAPH_COLOR[indexColor], FONT_GRAPH_SIZE));
        }
    }

    /**
     * Set color for line.
     *
     * @param line input GObject of line.
     * @param color value of color for line.
     * @return GObject for method ADD to display in window.
     */
    private GObject drawLine(GObject line, Color color) {
        line.setColor(color);
        return line;
    }

    /**
     * Set font, size and color for label.
     *
     * @param label input GLabel of label.
     * @param color value of color for label.
     * @param size value of size for label.
     * @return GLabel for method ADD to display in window.
     */
    private GLabel drawLabel(GLabel label, Color color, int size) {
        label.setFont(FONT + "-" + size);
        label.setColor(color);
        return label;
    }

    /**
     * Implementation of the ComponentListener interface
     */
    public void componentHidden(ComponentEvent e) {
    }

    /**
     * Implementation of the ComponentListener interface
     */
    public void componentMoved(ComponentEvent e) {
    }

    /**
     * Implementation of the ComponentListener interface
     */
    public void componentResized(ComponentEvent e) {
        update();
    }

    /**
     * Implementation of the ComponentListener interface
     */
    public void componentShown(ComponentEvent e) {
    }
}