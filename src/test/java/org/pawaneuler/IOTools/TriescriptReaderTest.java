package org.pawaneuler.IOTools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.pawaneuler.DataTypes.Trie.Node;
import org.pawaneuler.DataTypes.Trie.NodeRoot;
import org.pawaneuler.IOTools.TriescriptTools.TriescriptReader;

public class TriescriptReaderTest {
    @Test
    public void createTriescriptTest() {
        TriescriptReader reader;
        try {
            reader = TriescriptReader.createReader("./src/test/java/org/pawaneuler/IOTools/test.triescript");

            Node temp = reader.readLine();
            Node expected = new NodeRoot(new ArrayList<Integer>(Arrays.asList(1, 2, 3)));

            assertEquals("Node is not same", expected, temp);

        } catch (Exception e) {
            System.out.println("Got exception " + e);

            fail();
        }

    }

    @Test
    public void leafNodeTest() {
        try {
            TriescriptReader reader;
            reader = TriescriptReader.createReader("./src/test/java/org/pawaneuler/IOTools/leafTest.triescript");
            Node temp = reader.readLine();

            ArrayList<Integer> children = new ArrayList<Integer>();

            assertSame("Size isn't same", children.size(), temp.getDegree());
        } catch (Exception e) {
            System.out.println("Got exception " + e);

            fail();
        }
    }

    @Test
    public void endOfFileTest() {
        try {
            TriescriptReader reader;
            reader = TriescriptReader.createReader("./src/test/java/org/pawaneuler/IOTools/test.triescript");
            Node temp = reader.readLine();

            while(!temp.isNull()) {
                // Do Something, nothing as a test

                temp = reader.readLine();
            }

        } catch (Exception e) {
            System.out.println("Got exception " + e);

            fail();
        }
    }
}