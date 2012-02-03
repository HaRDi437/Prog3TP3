/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.InputVerifier;
import javax.swing.JComponent;

/**
 *
 * @author riadh
 */
public class AgeInputVerifier extends InputVerifier implements ActionListener {

    @Override
    public boolean verify(JComponent input) {
        Boolean inputOK = true;
        if (inputOK) {
            return true;
        } else {
            Toolkit.getDefaultToolkit().beep();
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
