package org.pawaneuler.DataTypes.Trie;

import java.util.ArrayList;

/**
 * Class for root node in Trie class
 * 
 * @author ReyRizki
 */
public class NodeRoot extends Node{
    public NodeRoot() {
        super("root", 0);
    }

    public NodeRoot(ArrayList<Integer> indexesOfChildren) {
        super("root", 0, indexesOfChildren);
    }

    @Override
    public String getProduct() {
        return super.getProduct();
    }

    @Override
    public int getFrequency() {
        return super.getFrequency();
    }
    
    @Override
    public ArrayList<Integer> getIndexesOfChildren() {
        return super.getIndexesOfChildren();
    }

    public boolean isRoot() {
        return true;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof NodeRoot) {
            boolean productEquals = this.getProduct().equals(((Node) object).getProduct());
            boolean frequencyEquals = this.getFrequency() == ((Node) object).getFrequency();
            boolean sizeEquals = this.getDegree() == ((Node) object).getDegree();
            boolean childrenEquals = this.getIndexesOfChildren().equals(((Node) object).getIndexesOfChildren());

            return productEquals && frequencyEquals && sizeEquals && childrenEquals;
        }

        return false;
    }
}