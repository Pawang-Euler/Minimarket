package org.pawaneuler.DataTypes.Trie;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class NodeRootTest {
    @Test
    public void nodeEqualsTest() {
        try {
            Node node = new NodeRoot(new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
            Node expected = new NodeRoot(new ArrayList<Integer>(Arrays.asList(1)));

            assertNotEquals("Node is equals", expected, node);
            
        } catch (Exception e) {
            System.out.println("Got exception " + e);

            fail();
        }
    }
}