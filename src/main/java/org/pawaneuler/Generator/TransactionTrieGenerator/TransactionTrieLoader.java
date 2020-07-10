package org.pawaneuler.Generator.TransactionTrieGenerator;

import java.io.IOException;

import org.pawaneuler.IOTools.TriescriptTools.TriescriptReader;
import org.pawaneuler.DataTypes.Trie.Node;
import org.pawaneuler.DataTypes.Trie.Trie;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;

/**
 * Class to load trie from a triescript
 * 
 * @author ReyRizki
 */
public class TransactionTrieLoader {
    private TriescriptReader source;

    public TransactionTrieLoader(String filePath) throws BadExtentionException, IOException {
        this.source = TriescriptReader.createReader(filePath);
    }
    
    public Trie load() throws IOException{
        Trie trie = new Trie();

        // read root node
        Node root = source.readLine();
        if (!root.isNull())
            trie = new Trie(root);

        Node node = source.readLine();
        while (!node.isNull()) {
            trie.addNode(node);

            node = source.readLine();
        }
        
        return trie;
    }
}