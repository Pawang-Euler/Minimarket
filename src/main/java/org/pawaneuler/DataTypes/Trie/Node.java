package org.pawaneuler.DataTypes.Trie;

import java.util.ArrayList;

import org.pawaneuler.DataTypes.Writeable;

/**
 * @author ReyRizki
 */
public class Node implements Comparable<Node>, Writeable {
    private String product;
    private int frequency;
    private ArrayList<Integer> indexesOfChildren;

    public Node(String product) {
        this.product = product;
        this.frequency = 0;
        this.indexesOfChildren = new ArrayList<Integer>();
    }

    /**
     * Constructor with 2 parameters for create a leaf node with the frequency
     * 
     * @param product
     * @param frequency
     */
    public Node(String product, int frequency) {
        this.product = product;
        this.frequency = frequency;
        this.indexesOfChildren = new ArrayList<Integer>();
    }

    public Node(String product, int frequency, ArrayList<Integer> indexesOfChildren) {
        this.product = product;
        this.frequency = frequency;
        this.indexesOfChildren = indexesOfChildren;
    }

    /**
     * Method to get a null Node object
     * 
     * @return null Node
     */
    public static Node getNullNode() {
        return new NodeNull();
    }

    /**
     * @return boolean
     */
    public boolean isNull() {
        return false;
    }

    /**
     * Method to get number of children in a node
     * 
     * @return Degree of a node
     */
    public int getDegree() {
        return this.indexesOfChildren.size();
    }

    /**
     * @return String
     */
    public String getProduct() {
        return product;
    }

    /**
     * @return int
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Method to increase frequency by one
     */
    public void increaseFrequncy() {
        this.frequency++;
    }

    /**
     * Method to get a child index in the ArrayList by specifix index
     * 
     * @param index to get
     * @return index of the child in the ArrayList
     */
    public int getChildIndexAt(int index) {
        if (index >= 0 && index < this.getDegree()) {
            return this.indexesOfChildren.get(index);
        } else {
            return -1;
        }
    }

    /**
     * Method to add a child index in back
     * 
     * @param index to be added
     */
    public void addChildIndex(int index) {
        this.indexesOfChildren.add(index);
    }

    /**
     * Method to add a child index in a specific index at the ArrayList
     * 
     * @param target index in the ArrayList
     * @param index  to be added
     */
    public void addChildIndex(int target, int index) {
        this.indexesOfChildren.add(target, index);
    }

    /**
     * Method to increase an element in ArrayList with 1
     * 
     * @param index target to be increased
     */
    public void increaseChildIndexAt(int index) {
        this.indexesOfChildren.set(index, this.getChildIndexAt(index) + 1);
    }

    /**
     * Method to get a new node from a triescript line
     * 
     * @param line of a triescript
     * @return a new node
     */
    public static Node stringToNode(String line) {
        if (line == null) {
            return getNullNode();
        }

        String[] temp = line.split(",", 0);

        ArrayList<Integer> indexes = new ArrayList<Integer>();

        for (int i = 2; i < temp.length; i++)
            indexes.add(Integer.parseInt(temp[i]));

        return new Node(temp[0], Integer.parseInt(temp[1]), indexes);
    }

    /**
     * Method to get string from a node for create a triescript file
     * 
     * @return a string
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(this.product + ",");
        result.append(Integer.toString(this.frequency) + ",");

        int size = this.indexesOfChildren.size();
        for (int i = 0; i < size; i++) {
            result.append(Integer.toString(this.getChildIndexAt(i)));

            if (i < size - 1)
                result.append(",");
        }

        return result.toString();
    }

    /**
     * @param node
     * @return int
     */
    @Override
    public int compareTo(Node node) {
        return this.product.compareTo(node.product);
    }

    /**
     * @return String
     */
    @Override
    public String generateRecord() {
        String temp = "";

        for (int item : this.indexesOfChildren) {
            temp += item;
            temp += ",";
        }

        if (temp.isEmpty()) {
            temp = "";
        } else {
            temp = temp.substring(0, temp.lastIndexOf(","));
        }
        return this.product + "," + this.frequency + "," + temp;
    }
}