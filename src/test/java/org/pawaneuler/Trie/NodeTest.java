package org.pawaneuler.Trie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class NodeTest {
    /**
     * Basic node testing for constructor, getter, and setter.
     */
    @Test
    public void basicNodeTest() {
        try {
            Node node = new Node("Jaguar");

            String product = "Jaguar";
            node.increaseFrequncy();

            node.addChildIndex(3);
            node.addChildIndex(5);

            node.increaseChildIndexAt(1);

            assertEquals("Product isn't same", product, node.getProduct());
            assertEquals("Frequency didn't increased", 1, node.getFrequency());
            assertEquals("Nodes are not added", 2, node.getDegree());
            assertEquals("Index isn't increased", 6, node.getChildIndexAt(1));
        } catch (Exception e) {
            System.out.println("Got exception " + e);

            fail();
        }
    }

    @Test
    public void nodeToStringTest() {
        try {
            Node node = new Node("Jaguar", 1, new ArrayList<Integer>(Arrays.asList(3, 6)));
            String expected = "Jaguar,1,3,6";
    
            assertEquals("Result isn't same", expected, node.toString());
        } catch (Exception e) {
            System.out.println("Got exception " + e);

            fail();
        }
    }

    @Test
    public void stringToNodeTest() {
        try {
            String triescriptLine = "Jaguar,1,3,6";
            Node node = Node.stringToNode(triescriptLine);

            String product = "Jaguar";
            int frequency = 1;
            ArrayList<Integer> indexesOfChildren = new ArrayList<Integer>(Arrays.asList(3, 6));

            assertEquals("Product isn't same", product, node.getProduct());
            assertEquals("Frequency isn't same", frequency, node.getFrequency());
            assertEquals("Degree isn't same", indexesOfChildren.size(), node.getDegree());

            int size = node.getDegree();
            for (int i = 0; i < size; i++) {
                assertSame("Element different at" + Integer.toString(i), node.getChildIndexAt(i), indexesOfChildren.get(i));
            }
        } catch (Exception e) {
            System.out.println("Got exception " + e);

            fail();
        }
    }
}