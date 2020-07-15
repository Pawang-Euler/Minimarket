package org.pawaneuler.minimarket.GUI;

import java.io.IOException;

import javax.swing.JTextArea;

import org.pawaneuler.DataTypes.Trie.Trie;
import org.pawaneuler.Generator.TransactionTrieGenerator.TransactionTrieCreator;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;

public class TCSVHandler {
    private TransactionTrieCreator creator;
    private JTextArea progress;
    private String path;

    private TCSVHandler(JTextArea progress, String path) {
        this.progress = progress;
        this.path = path;
    }

    public Trie handle() {
        try {
            this.creator = new TransactionTrieCreator(this.path);

            this.progress.append("Building the tree");
            return this.creator.createTranssactionTrie();
        } catch (BadExtentionException | IOException e) {
            this.progress.append("Got an error : " + e);
            e.printStackTrace();
        }

        return new Trie();
    }

    public static Trie handleTCSV(JTextArea progress, String path) {
        TCSVHandler handler = new TCSVHandler(progress, path);

        return handler.handle();
    }
}