package org.pawaneuler.DataTypes.Rule;

import java.util.ArrayList;

import org.pawaneuler.DataTypes.Trie.Node;
import org.pawaneuler.DataTypes.Trie.Trie;

public class Frequency {
    private Trie T;
    private ArrayList<String> itemset;
    private int allFreq;

    public Frequency(Trie T) {
        this.T = T;
        this.allFreq = generateAllFreq();
    }

    private int generateAllFreq() {
        ArrayList<Node> nodes = T.getNodes();
        int sum = 0;
        for (int i = 0; i < nodes.size(); i++) {
            sum += T.getNodeAt(i).getFrequency();
        }
        return sum;
    }

    public int getAllFreq() {
        return this.allFreq;
    }

    /**
     * 
     * @param itemset itemset that want to be checked
     * @return frequency in trie
     */
    public int getItemsetFreq(ArrayList<String> itemset) {
        this.itemset = itemset;
        //Start recursive from current index 0 and current node 0 (root)
        return getItemsetFreqRecursive(0, 0);
    }
    
    /**
     * to get itemset frequency in recursive
     * 
     * @param currIndexItemset current index of current item string that want to checked
     * @param currNode current node of trie
     * @return frequency of itemset
     */
    private int getItemsetFreqRecursive(int currIndexItemset, int currNode) {
        if (isAllItemSetFound(currIndexItemset)) {
            return generateAllFrequent(currIndexItemset, currNode);
        } else {
            return searchAllItemset(currIndexItemset, currNode);
        }
    }

    /**
     * method to get itemset frequency if all itemset isn't all found in trie
     * 
     * @param currIndexItemset current index of current item string that want to checked
     * @param currNode current node of trie
     * @return frequency of itemset
     */
    private int searchAllItemset(int currIndexItemset, int currNode) {
        Node currentNode = T.getNodeAt(currNode);
        boolean isNodeNull = currentNode.isNull();
        String currentItem = itemset.get(currIndexItemset);

        if (isNodeNull || isCurrentNodeProductGreaterThanCurrentItem(currentNode, currentItem)) {
            return 0;
        }
        else {
            //Check if current item is same with current node's product
            if (currentItem.compareTo(currentNode.getProduct()) == 0) {
                currIndexItemset++;
                return getItemsetFreqRecursive(currIndexItemset, currNode);
            } else {
                return childRecursive(currentNode,currIndexItemset);
            }
        }
    }

    /**
     * to check if the current node is greater than current item but have exception for comparison with root.
     * 
     * @param currentNode current node
     * @param currentItem current item string that want to checked
     * @return
     */
    private boolean isCurrentNodeProductGreaterThanCurrentItem(Node currentNode, String currentItem) {
        if (currentNode.isRoot()) {
            return false;
        }
        else {
            return currentNode.getProduct().compareTo(currentItem) > 0;
        }
    }

    /**
     * 
     * @param currIndexItemset
     * @return
     */
    private boolean isAllItemSetFound(int currIndexItemset) {
        return itemset.size() == currIndexItemset;
    }

    /**
     * to get frequency of itemset from current node child with recursive
     * 
     * @param currIndexItemset current index of current item string that want to checked
     * @param currNode current node of trie
     * @return
     */
    private int childRecursive(Node currentNode, int currIndexItemset) {
        int sumFreq = 0;

        for (int i = 0; i < currentNode.getDegree(); i++) {
            int childrenIndex = currentNode.getChildIndexAt(i);
            sumFreq += getItemsetFreqRecursive(currIndexItemset, childrenIndex);
        }
        return sumFreq;
    }

    /**
     * generate frequency itemset if all itemset is found
     * 
     * @param currIndexItemset current index of current item string that want to checked
     * @param currNode current node of trie
     * @return
     */
    private int generateAllFrequent(int currIndexItemset, int currNode) {
        Node currentNode = T.getNodeAt(currNode);
        boolean isNodeNull = currentNode.isNull();
        if (isNodeNull) {
            return 0;
        } else {
            return childRecursive(currentNode, currIndexItemset) + currentNode.getFrequency(); 
        }
    }
}