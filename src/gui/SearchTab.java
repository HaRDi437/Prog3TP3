/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.DataBase;
import gui.externalLib.SpringUtilities;
import java.awt.Dimension;
import java.awt.FlowLayout;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * @author riadh
 */
public class SearchTab extends JPanel implements ActionListener {

    JPanel panelFormes = new JPanel();
    JTextField textField = new JTextField(10);
    JButton buttonClear = new JButton("Clear Formes");
    JButton buttonSearch = new JButton("Search Person");
    JTable tableResults;
    JPanel panelRadio = new JPanel(new FlowLayout());
    JRadioButton radioButtonID = new JRadioButton("ID");
    JRadioButton radioButtonName = new JRadioButton("Name");
    JRadioButton radioButtonAge = new JRadioButton("Age");
    JRadioButton radioButtonAddress = new JRadioButton("Address");

    public SearchTab() {
        super();

        //     String[] labels = {"ID: ", "Name: ", "Age: ", "Address: "};
        //      int numPairs = labels.length;
//
////Create and populate the panel.
//        for (int i = 0; i < numPairs; i++) {
//            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
//            panelFormes.add(l);
//            JTextField textField = new JTextField(10);
//            l.setLabelFor(textField);
//            panelFormes.add(textField);
//        }


//        //Lay out the panel.
//        SpringUtilities.makeCompactGrid(panelFormes,
//                numPairs, 2, //rows, cols
//                6, 6, //initX, initY
//                6, 6);       //xPad, yPad

        ButtonGroup buttonGroup = new ButtonGroup();

        buttonGroup.add(radioButtonID);
        buttonGroup.add(radioButtonName);
        buttonGroup.add(radioButtonAge);
        buttonGroup.add(radioButtonAddress);

        panelRadio.add(radioButtonID);
        panelRadio.add(radioButtonName);
        panelRadio.add(radioButtonAge);
        panelRadio.add(radioButtonAddress);

        panelFormes.add(textField);
        panelFormes.add(panelRadio);
        //Action buttons
        JPanel panelButtons = new JPanel(new GridLayout());

        panelButtons.add(buttonClear);
        buttonClear.addActionListener(this);
        panelButtons.add(buttonSearch);
        buttonSearch.addActionListener(this);

        //tableResults setup
        tableResults = new JTable(DataBase.toTableModel((new DataBase()).selectAll()));
        //Adding scroll bar to the table
        tableResults.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tableResults.setFillsViewportHeight(true);

        //new panel that contain the table
        JScrollPane scrollPane = new JScrollPane(tableResults);


        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(panelFormes);
        this.add(panelButtons);
        this.add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (true) {
            if (evt.getSource() == buttonClear) {
                clearForms();
            }
            if (evt.getSource() == buttonSearch) {
                try {
                    if ("".equals(textField.getText())) {
                        tableResults.setModel(DataBase.toTableModel((new DataBase()).selectAll()));
                    } else {
                        if (radioButtonID.isSelected()) {
                            Integer ID = AddTab.verifieID(textField);
                            tableResults.setModel(DataBase.toTableModel((new DataBase()).selectByID(ID)));
                            tableResults.updateUI();
                        }

                        if (radioButtonName.isSelected()) {
                            String name = AddTab.verifieName(textField);
                            tableResults.setModel(DataBase.toTableModel((new DataBase()).selectByName(name)));
                            tableResults.updateUI();
                        }
                        if (radioButtonAge.isSelected()) {
                            Integer ID = AddTab.verifieAge(textField);
                            tableResults.setModel(DataBase.toTableModel((new DataBase()).selectByAge(ID)));
                            tableResults.updateUI();
                        }
                        if (radioButtonAddress.isSelected()) {
                        }
                    }
                } catch (InputMismatchException ex) {
                    Logger.getLogger(AddTab.class.getName()).log(Level.SEVERE, null, ex);
                    MainWindow.statusPanel.setStatus(ex.getMessage());
                }
            }
        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    private void clearForms() {
        //Component in panelForms are arrenged in the label JTextField order, we cast only the
        // second component in the row
        for (int i = 1; i < panelFormes.getComponentCount(); i += 2) {
            JTextField component = (JTextField) panelFormes.getComponent(i);
            component.setText("");
        }
    }
}