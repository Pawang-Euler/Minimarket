package org.pawaneuler.DataTypes.Trie;

import java.util.ArrayList;

/**
 * Null node to return if the index in the trie array is not available
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