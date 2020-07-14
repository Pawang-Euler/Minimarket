package org.pawaneuler.DataTypes.Trie;

import java.util.ArrayList;

/**
 * Null Node representation (ADT)
 * 
 * @author fauh45
 */
public class NodeNull extends Node {
    public NodeNull() {
        super(null, 0, new ArrayList<Integer>());
    }

    public NodeNull(ArrayList<Integer> indexesOfChildren) {
        super(null, 0, indexesOfChildren);
    }

    @Override
    public boolean isNull() {
        return true;
    }
}