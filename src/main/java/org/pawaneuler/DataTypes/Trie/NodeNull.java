package org.pawaneuler.DataTypes.Trie;

import java.util.ArrayList;

public class NodeNull extends Node {
    public NodeNull() {
        super(null, -1, new ArrayList<Integer>());
    }

    @Override
    public boolean isNull() {
        return true;
    }
}