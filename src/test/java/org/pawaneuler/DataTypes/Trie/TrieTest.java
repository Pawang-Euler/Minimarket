package org.pawaneuler.DataTypes.Trie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author ReyRizki
 */
public class TrieTest {
    /**
     * The trie:
     *                  root
     *                 /    \
     *              Apple  Jaguar
     */
    @Test
    public void basicTrieTest() {
        try {
            Trie trie = new Trie();
            Node newNode = new Node("Jaguar");
            String product = "Jaguar";

            trie.addNode(newNode);

            newNode = new Node("Apple");
            trie.addNode(1, newNode);

            assertSame("Size isn't same", 3, trie.size());
            assertEquals("Product isn't same", product, trie.getNodeAt(2).getProduct());
        } catch (Exception e) {
            System.out.println("Got exception " + e);

            fail();
        }
    }

    @Test
    public void rootNodeTest() {
        Trie trie = new Trie();
        int size = 1;

        assertSame("Size is not 1", size, trie.size());
        assertTrue("Root node is not null", trie.getNodeAt(0).isRoot());
        // assertEquals("Root node is not null node", nullNode, trie.getNodeAt(0));
    }


    /**
     * The trie:
     *     root
     *       |
     *     Apple
     *       |
     *     Jaguar
     */
    @Test
    public void grandChildTest() {
        try {
            Trie trie = new Trie();
            Node parent = new Node("Apple");
            Node child = new Node("Jaguar", 1);

            trie.addNode(parent);
            parent.addChildIndex(2);
            
            trie.addNode(child);
            
            Node result = trie.getNodeAt(parent.getChildIndexAt(0));

            String product = "Jaguar";
            int frequency = 1;

            assertEquals("Product isn't same", product, result.getProduct());
            assertSame("Frequency isn't same", frequency, result.getFrequency());
        } catch (Exception e) {
            System.out.println("Got exception " + e);

            fail();
        }
    } 
}