package org.pawaneuler.Generator.TransactionTrieGenerator;

import java.io.IOException;

import org.pawaneuler.DataTypes.TCSV.TCSV;
import org.pawaneuler.DataTypes.Trie.Node;
import org.pawaneuler.DataTypes.Trie.Trie;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;
import org.pawaneuler.IOTools.TCSVTools.TCSVReader;

/**
 * Class to create trie from TCSV
 * 
 * @author ReyRizki
 */
public class TransactionTrieCreator {
    private TCSVReader source;

    TransactionTrieCreator(String filePath) throws BadExtentionException, IOException{
        source = TCSVReader.createReader(filePath);
    }

    public Trie createTranssactionTrie() throws IOException {
        Trie trie = new Trie();

        TCSV tcsvData = source.readLine();
        String[] products = tcsvData.getProducts();

        while (products != null) {
            // traverse the trie from the root node
            int trav = 0;
            int productsLength = products.length;

            for (int i = 0; i < productsLength; i++) {
                Node currentNode = trie.getNodeAt(trav);

                if (currentNode.isLeaf()) {
                    currentNode.addChildIndex(trie.size());
                    trav = trie.size();
                    
                    Node newNode = new Node(products[i]);
                    trie.addNode(newNode);

                    if (i == productsLength - 1) {
                        newNode.increaseFrequncy();
                    }
                } else {
                    // traverse the child indexes
                    int j = 0;
                    Node currentNodeChild = trie.getNodeAt(currentNode.getChildIndexAt(j));

                    while (!currentNodeChild.isNull() && currentNodeChild.getProduct().compareTo(products[i]) < 0 && j < currentNode.getDegree()) {
                        j++;
                        currentNodeChild = trie.getNodeAt(currentNode.getChildIndexAt(j));
                    }

                    // product name is greater than all of the child node products
                    // so, just add the node in the end of the child nodes
                    if (currentNodeChild.isNull()) {
                        currentNode.addChildIndex(trie.size());
                        trav = trie.size();

                        Node newNode = new Node(products[i]);
                        trie.addNode(newNode);

                        if (i == productsLength - 1) {
                            newNode.increaseFrequncy();
                        }
                    // product name is exist in the child node
                    } else if (currentNodeChild.getProduct().equals(products[i])) {
                        trav = currentNode.getChildIndexAt(j);
                        
                        if (i == productsLength - 1) {
                            currentNodeChild.increaseFrequncy();
                        }
                    // product name isn't exist in the child node
                    // and between all of the child
                    } else {
                        int target = currentNode.getChildIndexAt(j);

                        this.shiftIndexes(trie, target);
                        
                        currentNode.addChildIndex(j, target);
                        
                        Node newNode = new Node(products[i]);
                        trie.addNode(target, newNode);

                        if (i == productsLength - 1) {
                            newNode.increaseFrequncy();
                        }
                    }
                }
            }

            tcsvData = source.readLine();
            products = tcsvData.getProducts();
        }

        return trie;
    }

    /**
     * Method to shift all child indexes of a node
     * so that the new node can place the target index
     * 
     * @param trie
     * @param target
     */
    private void shiftIndexes(Trie trie, int target) {
        int trieSize = trie.size();

        for (int i = 0; i < trieSize; i++) {
            Node currentNode = trie.getNodeAt(i);
            int nodeDegree = currentNode.getDegree();
            for (int j = 0; j < nodeDegree; j++) {
                if (currentNode.getChildIndexAt(j) >= target)
                    currentNode.increaseChildIndexAt(j);
            }
        }
    }
}