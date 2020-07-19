package org.pawaneuler.minimarket.GUI;

import java.awt.Dimension;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.pawaneuler.DataTypes.Trie.Trie;
import org.pawaneuler.Generator.TransactionLogGenerator.TransactionLogGenerator;
import org.pawaneuler.Generator.TransactionTrieGenerator.TransactionTrieCreator;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;
import org.pawaneuler.IOTools.TriescriptTools.TriescriptWriter;

public class GenerateMenu extends JPanel {
    private final JLabel maxTransactionLabel;
    private final JTextField transactionNumberField;
    private final JLabel maxVarietyLabel;
    private final JTextField maxVarietyField;
    private final JButton execute;

    private GenerateMenu() {
        // construct components
        maxTransactionLabel = new JLabel("Number of Transaction");
        transactionNumberField = new JTextField(5);
        maxVarietyLabel = new JLabel("Maximum Variety");
        maxVarietyField = new JTextField(5);
        execute = new JButton("Generate");

        execute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int maxTransaction = Integer.parseInt(transactionNumberField.getText());
                int maxVariety = Integer.parseInt(maxVarietyField.getText());
                String path = getSavePath();
                
                try {
                    String filePath = generateAtPath(path, maxTransaction, maxVariety);
                    MainProgress.run(filePath);
                } catch (BadExtentionException | IOException e1) {
                    JOptionPane.showMessageDialog(null, "Error creating the file.");

                    e1.printStackTrace();
                    System.exit(0);
                }
            }
        });
        // adjust size and set layout
        setPreferredSize(new Dimension(580, 131));
        setLayout(null);

        // add components
        add(maxTransactionLabel);
        add(transactionNumberField);
        add(maxVarietyLabel);
        add(maxVarietyField);
        add(execute);

        // set component bounds (only needed by Absolute Positioning)
        maxTransactionLabel.setBounds(15, 30, 150, 25);
        transactionNumberField.setBounds(175, 30, 125, 25);
        maxVarietyLabel.setBounds(15, 65, 150, 25);
        maxVarietyField.setBounds(175, 65, 125, 25);
        execute.setBounds(380, 30, 155, 60);
    }

    private static String getSavePath() {
        showWarning();
        final JFrame saveFrame = new JFrame();
        final JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        final int userChoice = chooser.showSaveDialog(saveFrame);
        if (userChoice == JFileChooser.APPROVE_OPTION) {
            System.out.println("Got Path : " + chooser.getSelectedFile().getAbsolutePath());
            return chooser.getSelectedFile().getAbsolutePath();
        } else {
            return getSavePath();
        }
    }

    private static void showWarning() {
        JOptionPane.showMessageDialog(null, "Please choose a folder to use to save the generated file");
    }

    private static String generateAtPath(String path, int maxTransaction, int maxVariety)
            throws BadExtentionException, IOException {
        String filePath = path + "/" + maxVariety + "-" + maxTransaction;

        TransactionLogGenerator generator = new TransactionLogGenerator(maxVariety);
        generator.generate(maxTransaction, filePath + ".tcsv");

        TransactionTrieCreator creator = new TransactionTrieCreator(filePath + ".tcsv");
        Trie createdTree = creator.createTranssactionTrie();

        TriescriptWriter writer = TriescriptWriter.createWriter(filePath + ".triescript");
        writer.bulkWriteLine(createdTree);

        return filePath + ".triescript";
    }

    public static void run() {
        final JFrame frame = new JFrame("Generate Menu");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new GenerateMenu());
        frame.pack();
        frame.setVisible(true);
    }
}