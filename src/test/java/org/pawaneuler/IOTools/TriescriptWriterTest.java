package org.pawaneuler.IOTools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.pawaneuler.DataTypes.Trie.Node;
import org.pawaneuler.DataTypes.Trie.Trie;
import org.pawaneuler.Generator.TransactionTrieGenerator.TransactionTrieLoader;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;
import org.pawaneuler.IOTools.TriescriptTools.TriescriptReader;
import org.pawaneuler.IOTools.TriescriptTools.TriescriptWriter;

public class TriescriptWriterTest {
    @Test
    public void writeTest() {
        try {
            TriescriptWriter writer = TriescriptWriter.createWriter("./writeTest.triescript");
            File checker = new File("./writeTest.triescript");

            assertTrue(checker.exists());

            String product = "A";
            Node content = new Node(product);
            writer.writeLine(content);

            TriescriptReader tcsvReader = TriescriptReader.createReader("./writeTest.triescript");
            Node real = tcsvReader.readLine();

            assertEquals("Product error!", product, real.getProduct());
            assertEquals("Freq error!", 0, real.getFrequency());
        } catch (BadExtentionException e) {
            fail("Wrong File extension reading!");
            e.printStackTrace();
        } catch (IOException e) {
            fail("Failed to read file!");
            e.printStackTrace();
        }
    }

    /**
     * The Trie:
     *              root
     *             /    \
     *            A(1)  B(1)
     */
    @Test
    public void bulkWriteLineTest() {
        try {
            String filePath = "./src/test/java/org/pawaneuler/IOTools/bulkWriteTest.triescript";
            TriescriptWriter writer = TriescriptWriter.createWriter(filePath);
            File checker = new File(filePath);

            assertTrue(checker.exists());

            Trie trie = new Trie();
            
            trie.addNode(new Node("A", 1));
            trie.getRootNode().addChildIndex(1);

            trie.addNode(new Node("B", 1));
            trie.getRootNode().addChildIndex(2);

            writer.bulkWriteLine(trie);

            Trie expectedTrie = trie;

            TransactionTrieLoader loader = new TransactionTrieLoader(filePath);
            Trie actualTrie = loader.load();

            int size = expectedTrie.size();
            assertEquals("Size isn't same", size, actualTrie.size());

            for (int i = 0; i < size; i++) {
                assertEquals("Node isn't same in index " + i, expectedTrie.getNodeAt(i), actualTrie.getNodeAt(i));
            }
        } catch (BadExtentionException e) {
            fail("Wrong File extension reading!");
            e.printStackTrace();
        } catch (IOException e) {
            fail("Failed to read file!");
            e.printStackTrace();
        }
    }
}