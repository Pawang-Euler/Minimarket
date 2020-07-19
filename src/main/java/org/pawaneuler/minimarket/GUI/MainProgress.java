package org.pawaneuler.minimarket.GUI;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.pawaneuler.DataTypes.Trie.Trie;
import org.pawaneuler.Generator.TransactionTrieGenerator.TransactionTrieLoader;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;

public class MainProgress extends JPanel {
    private String filePath;
    private Trie processedTrie;

    private JTextField minSupField;
    private JLabel minimumSupportLabel;
    private JLabel minimumConfidenceLabel;
    private JTextField minimumConfidenceField;
    private JTextArea progress;
    private JButton execute;

    private MainProgress(String filePath) {
        this.filePath = filePath;

        // construct components
        minSupField = new JTextField(5);
        minimumSupportLabel = new JLabel("Minimum Support");
        minimumConfidenceLabel = new JLabel("Minimum Confidence");
        minimumConfidenceField = new JTextField(5);
        progress = new JTextArea(5, 5);
        progress.setLineWrap(true);
        execute = new JButton("Generate");

        // adjust size and set layout
        setPreferredSize(new Dimension(643, 406));
        setLayout(null);

        // add components
        add(minSupField);
        add(minimumSupportLabel);
        add(minimumConfidenceLabel);
        add(minimumConfidenceField);
        add(progress);
        add(execute);

        // set component bounds (only needed by Absolute Positioning)
        minSupField.setBounds(155, 30, 125, 25);
        minimumSupportLabel.setBounds(20, 30, 135, 25);
        minimumConfidenceLabel.setBounds(20, 65, 135, 25);
        minimumConfidenceField.setBounds(155, 65, 125, 25);
        progress.setBounds(20, 125, 610, 265);
        execute.setBounds(465, 30, 150, 60);

        this.runProgress();
    }

    private void runProgress() {
        String extension = this.getExtension();
        System.out.println("Got extension : " + extension);

        if (extension.equals("tcsv")) {
            this.progress.append("Got TCSV File, opening...\n");

            this.processedTrie = TCSVHandler.handleTCSV(this.progress, this.filePath);
        } else if (extension.equals("triescript")) {
            this.progress.append("Got triescript, opening...\n");

            TransactionTrieLoader loader;
            try {
                loader = new TransactionTrieLoader(this.filePath);
                this.processedTrie = loader.load();
            } catch (BadExtentionException | IOException e) {
                this.progress.append("Error occured while loading the file : " + e + "\n");
                e.printStackTrace();
            }
        } else {
            this.progress.append("Unsupported file type!\n");
        }

        this.generateAssociationRules();
    }

    private void generateAssociationRules() {
        // TODO: Add Association Rules loader
    }

    private String getExtension() {
        return this.filePath.substring(this.filePath.lastIndexOf('.') + 1).trim();
    }

    public static void run(String filePath) {
        JFrame frame = new JFrame("Generate Association Rules");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new MainProgress(filePath));
        frame.pack();
        frame.setVisible(true);
    }
}
