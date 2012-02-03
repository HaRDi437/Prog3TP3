package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import data.DataBase;

/**
 * Class MainWindow
 */
public class MainWindow extends JFrame implements ActionListener {



    public static StatusPanel statusPanel = new StatusPanel();
    //
    // Fields
    //
    JMenuItem menuItemQuit = new JMenuItem("Quit");
    JMenuItem menuItemCut = new JMenuItem("Cut");
    JMenuItem menuItemCopy = new JMenuItem("Copy");
    JMenuItem menuItemPaste = new JMenuItem("Paste");
    JMenuItem menuItemConf = new JMenuItem("Configuration");
    //
    // Constructors
    //

    public MainWindow() {
        super("Prog3TP3");

        //Controller initiation

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Add", new AddTab());
        tabbedPane.addTab("Search", new SearchTab());


        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(tabbedPane, BorderLayout.CENTER);
        contentPane.add(statusPanel, BorderLayout.SOUTH);

        //File menu
        JMenu menuFile = new JMenu("File");

        menuFile.add(menuItemQuit);
        menuItemQuit.setEnabled(true);
        menuItemQuit.addActionListener(this);

        //Edit menu
        JMenu menuEdit = new JMenu("Edit");

        menuEdit.add(menuItemCut);
        menuItemCut.setEnabled(true);
        menuItemCut.addActionListener(this);

        menuEdit.add(menuItemCopy);
        menuItemCopy.setEnabled(true);
        menuItemCopy.addActionListener(this);

        menuEdit.add(menuItemPaste);
        menuItemPaste.setEnabled(true);
        menuItemPaste.addActionListener(this);

        menuEdit.addSeparator();

        menuEdit.add(menuItemConf);
        menuItemConf.addActionListener(this);

        //MenuBar setup
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuEdit);

        this.setJMenuBar(menuBar);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.pack();
    }
//
// Methods
//

    //
// Accessor methods
//
//
// Other methods
//
    @Override
    public void actionPerformed(ActionEvent evt) {
        if ((Object) evt.getSource() == menuItemQuit) {
            System.exit(0);
        } else if (evt.getSource() == menuItemConf) {
        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
