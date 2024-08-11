package com.shpp.p2p.cs.vholovin.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import javax.swing.*;
import java.awt.event.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    /** Text field for entering and searching data. */
    private JTextField          textField;

    /** Data base, which read from the input file.  */
    private NameSurferDataBase  dataBase;

    /** Сanvas for drawing graphs */
    private NameSurferGraph     graph;

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        add(new JLabel("Name:"), NORTH);

        textField = new JTextField(NUM_COLUMNS_TEXT_FIELD);
        textField.setActionCommand("EnterPressed");
        textField.addActionListener(this);
        add(textField, NORTH);

        add(new JButton("Graph"), NORTH);
        add(new JButton("Clear"), NORTH);
        addActionListeners();

        dataBase = new NameSurferDataBase(NAMES_DATA_FILE);

        graph = new NameSurferGraph();
        add(graph);
    }

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("EnterPressed") || cmd.equals("Graph")) {
            graph.addEntry(dataBase.findEntry(textField.getText()));
        }
        else if (cmd.equals("Clear")) {
            graph.clear();
        }

        graph.update();
        textField.setText("");
    }
}