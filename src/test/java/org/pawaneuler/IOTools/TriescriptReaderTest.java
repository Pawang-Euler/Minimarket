package org.pawaneuler.IOTools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.pawaneuler.DataTypes.Trie.Node;
import org.pawaneuler.IOTools.TriescriptTools.TriescriptReader;

public class TriescriptReaderTest {
    @Test
    public void createTriescriptTest() {
        TriescriptReader reader;
        try {
            reader = TriescriptReader.createReader("./src/test/java/org/pawaneuler/IOTools/test.triescript");

            Node temp = reader.readLine();
            String product = "null";
            int freq = 0;
            int firstChild = 1;

            assertEquals(product, temp.getProduct());
            assertEquals(freq, temp.getFrequency());
            assertEquals(firstChild, temp.getChildIndexAt(0));

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