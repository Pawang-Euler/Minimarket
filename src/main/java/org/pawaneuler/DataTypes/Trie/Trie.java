package org.pawaneuler.DataTypes.Trie;

import java.util.ArrayList;

/**
 * Trie class implemented using array
 * 
 * @author ReyRizki
 */
public class Trie {
    private ArrayList<Node> nodes;

    /**
     * The trie constuctor with a null node as the root of the trie
     */
    public Trie() {
        this.nodes = new ArrayList<Node>();
        this.addNode(Node.createRootNode());
    }

    public Trie(Node root) {
        this.nodes = new ArrayList<Node>();
        this.addNode(root);
    }

    public Node getRootNode() {
        return this.getNodeAt(0);
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
        if (index >= 0 && index < nodes.size()) {
            return this.nodes.get(index);
        }
        
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
}

