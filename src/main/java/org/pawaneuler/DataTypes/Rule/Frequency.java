package org.pawaneuler.DataTypes.Rule;

import java.util.ArrayList;

import org.pawaneuler.DataTypes.Trie.Node;
import org.pawaneuler.DataTypes.Trie.Trie;

public class Frequency {
    private Trie T;
    private ArrayList<String> itemset;

    public Frequency(Trie T) {
        this.T = T;
    }

    public int getItemsetFreq(ArrayList<String> itemset) {
        this.itemset = itemset;
        return getItemsetFreqRecursive(0, 0);
    }
    
    private int getItemsetFreqRecursive(int currIndex, int currNode) {
        Node currentNode = T.getNodeAt(currNode);
        boolean isNodeNull = currentNode.isNull();
        
        if (isAllItemSetFound(currIndex)) {
            return generateAllFrequent(currIndex, currNode);
        } else {
            String currentItem = itemset.get(currIndex);

            if (isNodeNull || isCurrentNodeProductGreaterThanCurrentItem(currentNode, currentItem)) {
                return 0;
            }
            else {
                if (currentItem.compareTo(currentNode.getProduct()) == 0) {
                    currIndex++;
                }

                if (isAllItemSetFound(currIndex)) {
                    return childRecursive(currentNode,currIndex) + currentNode.getFrequency();
                } else {
                    return childRecursive(currentNode,currIndex);
                }
            }
        }
    }

    private boolean isCurrentNodeProductGreaterThanCurrentItem(Node currentNode, String currentItem) {
        if (currentNode.isRoot()) {
            return false;
        }
        else {
            return currentNode.getProduct().compareTo(currentItem) > 0;
        }
    }

    private boolean isAllItemSetFound(int currIndex) {
        return itemset.size() == currIndex;
    }

    private int childRecursive(Node currentNode, int currIndex) {
        int sumFreq = 0;

        for (int i = 0; i < currentNode.getDegree(); i++) {
            int childrenIndex = currentNode.getChildIndexAt(i);
            sumFreq += getItemsetFreqRecursive(currIndex, childrenIndex);
        }
        return sumFreq;
    }

    /**
     * generate frequency itemset if all itemset is found
     * 
     * @param currIndex
     * @param currNode
     * @return
     */
    private int generateAllFrequent(int currIndex, int currNode) {
        Node currentNode = T.getNodeAt(currNode);
        boolean isNodeNull = currentNode.isNull();
        if (isNodeNull) {
            return 0;
        } else {
            return childRecursive(currentNode, currIndex) + currentNode.getFrequency(); 
        }
    }
}