package org.pawaneuler.DataTypes.Trie;

import java.util.ArrayList;

/**
 * @author ReyRizki
 */
public class Trie {
    private ArrayList<Node> nodes;
    private int AllFreq;

    /**
     * The trie constuctor with a null node as the root of the trie
     */
    public Trie() {
        this.nodes = new ArrayList<Node>();
        this.addNode(Node.getNullNode());
        this.AllFreq = 0;
    }

    /**
     * Method to add a node in the end of the nodes array
     * 
     * @param node
     */
    public void addNode(Node node) {
        this.nodes.add(node);
    }

    /**
     * Method to add a node in a specifix index of the nodes array
     * 
     * @param index
     * @param node
     */
    public void addNode(int index, Node node) {
        this.nodes.add(index, node);
    }

    /**
     * Method to get a node in a specifix index of the nodes array
     * 
     * @param index
     * @return the node if the index is valid, else return a NullNode
     */
    public Node getNodeAt(int index) {
        if (index >= 0 && index < nodes.size())
            return this.nodes.get(index);
        else
            return Node.getNullNode();
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     * Method to get elements in the nodes array
     * 
     * @return number of nodes
     */
    public int size() {
        return this.nodes.size();
    }

    public void generateAllFreq() {
        int sum = 0;
        for (int i = 0; i < nodes.size(); i++) {
            sum += getNodeAt(i).getFrequency();
        }
        this.AllFreq = sum;
    }

    public int getAllFreq() {
        return AllFreq;
    }

    /**
     * @author Marissa Nur
     * 
     * @param itemset itemset that want to be checked
     * @param currIndex current item index in itemset that want to be found
     * @param currNode current node index in trie
     * @return
     */
    public int getItemsetFreq(ArrayList<String> itemset, int currIndex, int currNode) {
        String currentItem = itemset.get(currIndex);
        Node currentNode = getNodeAt(currNode);
        boolean isNodeNull = currentNode.isNull();
        boolean isCurrentNodeProductGreaterThanCurrentItem = currentItem.compareTo(currentNode.getProduct()) < 0;

        if (isNodeNull || isCurrentNodeProductGreaterThanCurrentItem) {
            return 0;
        }
        else {
            if (currentItem.compareTo(currentNode.getProduct()) == 0) {
                currIndex++;
            }

            int sumFreq = 0;

            for (int i = 0; i < currentNode.getDegree(); i++) {
                sumFreq += getItemsetFreq(itemset, currIndex, currentNode.getChildIndexAt(i));
            }

            boolean isAllItemSetFound = itemset.size() == currIndex;

            if (isAllItemSetFound) {
                return sumFreq + currentNode.getFrequency();
            } else {
                return sumFreq;
            }
        }
    }

}