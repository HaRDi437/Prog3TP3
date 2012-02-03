/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author riadh
 */

import javax.swing.*;
import javax.swing.border.*;

public class StatusPanel extends JPanel {

    private final JLabel status = new JLabel();

    public StatusPanel() {

        super();

        this.setBorder(BorderFactory.createLoweredBevelBorder());
        this.add(status);
    }

    public void setStatus(String s) {
        status.setText(s);
    }

       public void clearStatus() {
        status.setText("");
    }
}