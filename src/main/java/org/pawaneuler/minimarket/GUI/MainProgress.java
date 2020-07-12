package org.pawaneuler.minimarket.GUI;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainProgress extends JPanel {
    private String filePath;
    private JTextArea progress;
    private JTextField jcomp2;
    private JButton send;

    private MainProgress(String filePath) {
        this.filePath = filePath;
        
        // construct components
        progress = new JTextArea(5, 5);
        jcomp2 = new JTextField(5);
        send = new JButton("Send");

        // set components properties
        progress.setEnabled(false);

        // adjust size and set layout
        setPreferredSize(new Dimension(667, 362));
        setLayout(null);

        // add components
        add(progress);
        add(jcomp2);
        add(send);

        // set component bounds (only needed by Absolute Positioning)
        progress.setBounds(10, 10, 650, 300);
        jcomp2.setBounds(10, 320, 490, 30);
        send.setBounds(515, 320, 140, 30);

        this.runProgress();
    }

    private void runProgress() {
        String extension = this.getExtension();
        System.out.println("Got extension : " + extension);

        if (extension.equals("tcsv")) {
            this.progress.append("Got TCSV File, opening...");
        } else if (extension.equals("triescript")) {
            this.progress.append("Got triescript, opening...");
        } else {
            this.progress.append("Unsupported file type!");
        }
    }

    private String getExtension() {
        return this.filePath.substring(this.filePath.lastIndexOf('.') + 1).trim();
    }

    public static void run(String filePath) {
        JFrame frame = new JFrame("Main Progress Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MainProgress(filePath));
        frame.pack();
        frame.setVisible(true);
    }
}