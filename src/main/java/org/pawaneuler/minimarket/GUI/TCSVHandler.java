package org.pawaneuler.minimarket.GUI;

import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.pawaneuler.DataTypes.Trie.Trie;
import org.pawaneuler.Generator.TransactionTrieGenerator.TransactionTrieCreator;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;
import org.pawaneuler.IOTools.TriescriptTools.TriescriptWriter;

public class TCSVHandler {
    private TransactionTrieCreator creator;
    private JTextArea progress;
    private String path;

    private TCSVHandler(JTextArea progress, String path) {
        this.progress = progress;
        this.path = path;
    }

    private String getSavePath() {
        showWarning();
        JFrame saveFrame = new JFrame();
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save triescript");
        chooser.setFileFilter(new FileNameExtensionFilter(".triescript", "triescript"));

        int userChoice = chooser.showSaveDialog(saveFrame);
        if (userChoice == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        } else {
            return getSavePath();
        }
    }

    private static void showWarning() {
        JOptionPane.showMessageDialog(null, "Please choose path to save generated triescript file");
    }

    public Trie handle() {
        try {
            this.creator = new TransactionTrieCreator(this.path);

            this.progress.append("Building the tree\n");
            TriescriptWriter writer = TriescriptWriter.createWriter(this.getSavePath());

            Trie tempTrie = this.creator.createTranssactionTrie();
            writer.bulkWriteLine(tempTrie);
            return tempTrie;
        } catch (BadExtentionException | IOException e) {
            this.progress.append("Got an error : " + e + "\n");
            e.printStackTrace();
        }

        return new Trie();
    }

    public static Trie handleTCSV(JTextArea progress, String path) {
        TCSVHandler handler = new TCSVHandler(progress, path);

        return handler.handle();
    }
}