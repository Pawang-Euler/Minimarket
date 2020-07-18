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

    public TransactionTrieCreator(String filePath) throws BadExtentionException, IOException {
        this.source = TCSVReader.createReader(filePath);
    }

    public Trie createTranssactionTrie() throws IOException {
        Trie trie = new Trie();

        TCSV tcsvData = source.readLine();
        String[] products = tcsvData.getProducts();

        while (products != null) {
            this.insertProduct(trie, products);

            tcsvData = source.readLine();
            products = tcsvData.getProducts();
        }

        return trie;
    }

    private void insertProduct(Trie trie, String[] products) {
        // traverse the trie from the root node
        int currentTraverseIndex = 0;
        int productsLength = products.length;

        for (int i = 0; i < productsLength; i++) {
            Node currentNode = trie.getNodeAt(currentTraverseIndex);

            int lowerBound = lowerBoundIndex(trie, currentNode, products[i]);
            Node currentNodeChild = trie.getNodeAt(currentNode.getChildIndexAt(lowerBound));

            boolean currentNodeisNotExpected = currentNodeChild.isNull() || !currentNodeChild.getProduct().equals(products[i]);
            
            if (currentNodeisNotExpected) {
                Node newNode = new Node(products[i]);

                // Case 1: product name is greater than all of the child node products
                if (currentNodeChild.isNull()) {
                    currentNode.addChildIndex(trie.size());
                    currentTraverseIndex = trie.size();

                    trie.addNode(newNode);
                // Case 2: product name is among the currentNode's children
                } else {
                    int target = currentNode.getChildIndexAt(lowerBound);
                    
                    this.shiftIndexes(trie, target);
                    
                    currentNode.addChildIndex(lowerBound, target);
                    currentTraverseIndex = target;

                    trie.addNode(target, newNode);
                }

                if (i == productsLength - 1) {
                    newNode.increaseFrequncy();
                }
            // Case 3: product name is exist in the child node
            } else if (currentNodeChild.getProduct().equals(products[i])) {
                currentTraverseIndex = currentNode.getChildIndexAt(lowerBound);

                if (i == productsLength - 1) {
                    currentNodeChild.increaseFrequncy();
                }
            }
        }
    }

    /**
     * Method to shift all child indexes of a node so that the new node can place
     * the target index
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

    private int lowerBoundIndex(Trie trie, Node node, String product) {
        // traverse the child indexes
        int currentChildIndex = 0;

        while (nodeIsNotLowerBound(trie, node, product, currentChildIndex)) {
            currentChildIndex++;
        }

        return currentChildIndex;
    }

    private boolean nodeIsNotLowerBound(Trie trie, Node node, String product, int currentChildIndex) {
        Node nodeChild = trie.getNodeAt(node.getChildIndexAt(currentChildIndex));

        boolean nodeIsInsideLimit = currentChildIndex < node.getDegree();
        boolean nodeIsBeforeCurrentProduct = !nodeChild.isNull() && (nodeChild.getProduct().compareTo(product) < 0);

        return nodeIsInsideLimit && nodeIsBeforeCurrentProduct;
    }
}