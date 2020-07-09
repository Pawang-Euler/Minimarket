package org.pawaneuler.IOTools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.pawaneuler.DataTypes.Trie.Node;
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
}