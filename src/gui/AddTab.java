package gui;

import gui.externalLib.SpringUtilities;
import data.DataBase;
import gui.MainWindow;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;

/**
 * Class AddTab
 */
public class AddTab extends JPanel implements ActionListener {

    //
    // Fields
    //
    JPanel panelForms = new JPanel(new SpringLayout());
    JButton buttonClear = new JButton("Clear Formes");
    JButton buttonAdd = new JButton("Add Person");
    JTextField nameTextField = new JTextField(10);
    JTextField ageTextField = new JTextField(10);
    JTextField addressTextField = new JTextField(10);

    //
    // Constructors
    //
    public AddTab() {

        super();

        String[] labels = {"Name: ", "Age: ", "Address: "};
        int numPairs = labels.length;

//Create and populate the panel.
        int i = 0;
        panelForms.add(new JLabel(labels[i], JLabel.TRAILING));
        panelForms.add(nameTextField);
        i++;

        panelForms.add(new JLabel(labels[i], JLabel.TRAILING));
        panelForms.add(ageTextField);
        i++;

        panelForms.add(new JLabel(labels[i], JLabel.TRAILING));
        panelForms.add(addressTextField);
        i++;

//Lay out the panel.
        SpringUtilities.makeCompactGrid(panelForms,
                numPairs, 2, //rows, cols
                6, 6, //initX, initY
                6, 6);       //xPad, yPad

        JPanel panelButtons = new JPanel(new GridLayout());

        panelButtons.add(buttonClear);
        buttonClear.addActionListener(this);
        panelButtons.add(buttonAdd);
        buttonAdd.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(panelForms);
        this.add(panelButtons);
    }

    //;
// Methods
//
    @Override
    public void actionPerformed(ActionEvent evt) {
        MainWindow.statusPanel.clearStatus();

        if (evt.getSource() == buttonClear) {
            clearForms();
        } else if (evt.getSource() == buttonAdd) {
            Integer age;
            String name;
            String address;
            try {
                clearBackgourd();
                age = verifieAge(ageTextField);
                name = verifieName(nameTextField);
                address = addressTextField.getText();
                clearForms();
                (new DataBase()).insert(name, age, address);

            } catch (InputMismatchException ex) {
                Logger.getLogger(AddTab.class.getName()).log(Level.SEVERE, null, ex);
                MainWindow.statusPanel.setStatus(ex.getMessage());
            }

        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }//end of actionPerformed

//
// Accessor methods
//
//
// Other methods
//
    private void clearForms() {
        nameTextField.setText("");
        ageTextField.setText("");
        addressTextField.setText("");
        clearBackgourd();
    }

    private void clearBackgourd() {
        nameTextField.setBackground(Color.white);
        ageTextField.setBackground(Color.white);
        addressTextField.setBackground(Color.white);
    }

    public static Integer verifieAge(JTextField textField) throws InputMismatchException {
        int MAX = 120;
        int MIN = 0;
        Integer aux;
        try {
            aux = new Integer(Integer.parseInt(textField.getText()));
            if (aux.compareTo(MAX) < 0 && aux.compareTo(MIN) > 0) {
                return aux;
            }
        } catch (NumberFormatException ex) {
            Logger.getLogger(AddTab.class.getName()).log(Level.SEVERE, null, ex);
            textField.setBackground(Color.red);
            throw new InputMismatchException("Age not valid");
        }
        return null;
    }

    public static Integer verifieID(JTextField textField) throws InputMismatchException {
        Integer aux;
        try {
            aux = new Integer(Integer.parseInt(textField.getText()));
            if (aux > 0) {
                return aux;
            }
        } catch (NumberFormatException ex) {
            Logger.getLogger(AddTab.class.getName()).log(Level.SEVERE, null, ex);
            textField.setBackground(Color.red);
            throw new InputMismatchException("ID not valid");
        }
        return null;
    }

    public static String verifieName(JTextField textField) {
        //Name only alphabetic and " " not numerical or empty
        String name = textField.getText();
        if (name.equals("")) {
            throw new InputMismatchException("Name not valid");

        } else {
            for (int i = 0; i < name.length(); i++) {
                Character c = name.charAt(i);

                if (!(c.equals(' ') || Character.isAlphabetic(c))) {
                    //TODO log
                    textField.setBackground(Color.red);
                    throw new InputMismatchException("Name not valid");
                }
            }
        }
        return name;
    }
}//end class AddTab

