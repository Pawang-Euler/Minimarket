package org.pawaneuler.Generator.TransactionTrieGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.pawaneuler.DataTypes.Trie.Node;
import org.pawaneuler.DataTypes.Trie.NodeRoot;
import org.pawaneuler.DataTypes.Trie.Trie;

public class TransactionTrieLoaderTest {
    /**
     * The trie:
     *     root
     *       |
     *     Apple
     *       |
     *     Jaguar
     */
    @Test
    public void loadTest() {
        try {
            TransactionTrieLoader loader = new TransactionTrieLoader("./src/test/java/org/pawaneuler/Generator/TransactionTrieGenerator/basicTest.triescript");
            Trie trie = loader.load();
            NodeRoot root = new NodeRoot(new ArrayList<Integer>(Arrays.asList(1)));
            Trie expectedTrie = new Trie(root);

            Node node = new Node("apple", 0, new ArrayList<Integer>(Arrays.asList(2)));
            expectedTrie.addNode(node);

            node = new Node("jaguar", 1);
            expectedTrie.addNode(node);

            assertTrue(trie.getRootNode().isRoot());
            assertEquals("Node is not equals", root, trie.getRootNode());
            assertEquals("Trie root is not equals", expectedTrie.getRootNode(), trie.getRootNode());
            
            int size = expectedTrie.size();
            for (int i = 1; i < size; i++) {
                assertEquals("Node is not equals at index " + i, expectedTrie.getNodeAt(i), trie.getNodeAt(i));
            }
        } catch (Exception e) {
            System.out.println("Got exception " + e);
            fail();
        }
    }
}