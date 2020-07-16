package org.pawaneuler.Generator.TransactionTrieGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.pawaneuler.DataTypes.Trie.Node;
import org.pawaneuler.DataTypes.Trie.Trie;

/**
 * @author ReyRizki
 */
public class TransactionTrieCreatorTest {
    /**
     * The Trie:
     *              root
     *               |
     *               A (0)
     *               |
     *               X (1)
     */
    @Test
    public void leafNodeTest() {
        try {
            TransactionTrieCreator creator = new TransactionTrieCreator("./src/test/java/org/pawaneuler/Generator/TransactionTrieGenerator/leafNodeTest.tcsv");
            Trie trie = creator.createTranssactionTrie();

            Trie expectedTrie = new Trie();
            expectedTrie.addNode(new Node("A"));
            expectedTrie.getNodeAt(0).addChildIndex(1);

            expectedTrie.addNode(new Node("X", 1));
            expectedTrie.getNodeAt(1).addChildIndex(2);

            int size = expectedTrie.size();

            assertEquals("Root is not equal", expectedTrie.getRootNode(), trie.getRootNode());

            for (int i = 0; i < size; i++) {
                assertEquals("Node different in index" + i, expectedTrie.getNodeAt(i), trie.getNodeAt(i));
            }
        } catch (Exception e) {
            System.out.println("Got exception " + e);

            fail();
        }
    }

    /**
     * The Trie:
     *          root
     *           |
     *           A (2)
     *           |
     *           W (1)
     */
    @Test
    public void sameProductTest() {
        try {
            TransactionTrieCreator creator = new TransactionTrieCreator("./src/test/java/org/pawaneuler/Generator/TransactionTrieGenerator/sameProductTest.tcsv");
            Trie trie = creator.createTranssactionTrie();

            Trie expectedTrie = new Trie();
            expectedTrie.addNode(new Node("A", 2));
            expectedTrie.getNodeAt(0).addChildIndex(1);

            expectedTrie.addNode(new Node("W", 1));
            expectedTrie.getNodeAt(1).addChildIndex(2);

            int size = expectedTrie.size();

            assertEquals("Root is not equal", expectedTrie.getRootNode(), trie.getRootNode());
            // assertEquals("Leaf is not equal", expectedTrie.getNodeAt(1), trie.getNodeAt(1));

            for (int i = 0; i < size; i++) {
                assertEquals("Node different in index " + i, expectedTrie.getNodeAt(i), trie.getNodeAt(i));
            }
        } catch (Exception e) {
            System.out.println("Got exception " + e);

            fail();
        }
    }

    /**
     * The Trie:
     *              root
     *            /      \   
     *           A (1)    E (1)
     *                    | 
     *                    Z (1)
     */
    @Test
    public void greaterProductTest() {
        try {
            TransactionTrieCreator creator = new TransactionTrieCreator("./src/test/java/org/pawaneuler/Generator/TransactionTrieGenerator/greaterProductTest.tcsv");
            Trie trie = creator.createTranssactionTrie();

            Trie expectedTrie = new Trie();
            expectedTrie.addNode(new Node("A", 1));
            expectedTrie.getNodeAt(0).addChildIndex(1);

            expectedTrie.addNode(new Node("E", 1));
            expectedTrie.getNodeAt(0).addChildIndex(2);

            expectedTrie.addNode(new Node("Z", 1));
            expectedTrie.getNodeAt(2).addChildIndex(3);

            int size = expectedTrie.size();

            assertEquals("Root is not equal", expectedTrie.getRootNode(), trie.getRootNode());
            for (int i = 0; i < size; i++) {
                assertEquals("Node different in index " + i, expectedTrie.getNodeAt(i), trie.getNodeAt(i));
            }
            // assertEquals("Leaf is not equal", expectedTrie.getNodeAt(1), trie.getNodeAt(1));
        } catch (Exception e) {
            System.out.println("Got exception " + e);

            fail();
        }
    }

    /**
     * The Trie:
     *              root
     *            /      \   
     *           A (1)    E (1)
     *           |
     *           W (1)
     */
    @Test
    public void smallerProductTest() {
        try {
            TransactionTrieCreator creator = new TransactionTrieCreator("./src/test/java/org/pawaneuler/Generator/TransactionTrieGenerator/smallerProductTest.tcsv");
            Trie trie = creator.createTranssactionTrie();

            Trie expectedTrie = new Trie();
            expectedTrie.addNode(new Node("A", 1));
            expectedTrie.getNodeAt(0).addChildIndex(1);

            expectedTrie.addNode(new Node("E", 1));
            expectedTrie.getNodeAt(0).addChildIndex(2);

            expectedTrie.addNode(new Node("W", 1));
            expectedTrie.getNodeAt(1).addChildIndex(3);

            int size = expectedTrie.size();

            System.out.println(trie.getNodeAt(2));

            assertSame("Size isn't same", size, trie.size());
            assertSame("Size isn't same", 4, trie.size());

            assertEquals("Root is not equal", expectedTrie.getRootNode(), trie.getRootNode());
            for (int i = 0; i < size; i++) {
                assertEquals("Node different in index " + i, expectedTrie.getNodeAt(i), trie.getNodeAt(i));
            }
        } catch (Exception e) {
            System.out.println("Got exception " + e);

            fail();
        }
    }

    @Test
    public void randomTest() {
        try {
            TransactionTrieCreator creator = new TransactionTrieCreator("./src/test/java/org/pawaneuler/Generator/TransactionTrieGenerator/randomTest.tcsv");
            Trie trie = creator.createTranssactionTrie();

            assertSame("Size isn't same", 23, trie.size());

        } catch (Exception e) {
            System.out.println("Got exception " + e);

            fail();
        }
    }
}