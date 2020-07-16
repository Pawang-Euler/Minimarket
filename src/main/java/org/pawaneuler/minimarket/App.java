package org.pawaneuler.minimarket;

import java.util.ArrayList;

import org.pawaneuler.DataTypes.Trie.Node;
import org.pawaneuler.DataTypes.Trie.Trie;
import org.pawaneuler.Generator.TransactionTrieGenerator.TransactionTrieCreator;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // System.out.println("Ya Yeet!");
        Trie trie;

        try {
            TransactionTrieCreator creator = new TransactionTrieCreator("src/main/java/org/pawaneuler/minimarket/transactionLogTest.tcsv");

            trie = creator.createTranssactionTrie();
            
            ArrayList<Node> nodes = trie.getNodes();
            
            ArrayList<String> itemset = new ArrayList<String>();
            itemset.add("Apple");
            itemset.add("Beer");
            itemset.add("Ham");

            int result = trie.getItemsetFreq(itemset, 0, 1);
            System.out.println(result);


        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
