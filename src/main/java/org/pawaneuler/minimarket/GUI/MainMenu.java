package org.pawaneuler.minimarket.GUI;

import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JPanel {
    private JLabel prompt;
    private JButton load;
    private JButton generate;
    private JLabel copyright;

    public MainMenu() {
        // construct components
        prompt = new JLabel("Select One From The Menu Below");
        load = new JButton("Load File");
        generate = new JButton("Generate Transaction");
        copyright = new JLabel("PawangEuler, 2020");

        load.addActionListener(new LoadFile());
        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenerateMenu.run();
            }
        });

        // set components properties
        load.setToolTipText("Load triescript™ or tscv");
        generate.setToolTipText("Generate new random transaction file");

        // adjust size and set layout
        setPreferredSize(new Dimension(409, 302));
        setLayout(null);

        // add components
        add(prompt);
        add(load);
        add(generate);
        add(copyright);

        // set component bounds (only needed by Absolute Positioning)
        prompt.setBounds(110, 40, 195, 30);
        load.setBounds(120, 95, 175, 35);
        generate.setBounds(120, 145, 175, 35);
        copyright.setBounds(290, 275, 115, 25);
    }

    public static void run() {
        JFrame frame = new JFrame("Minimarket");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MainMenu());
        frame.pack();
        frame.setVisible(true);
    }
}