package org.pawaneuler.IOTools;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.pawaneuler.IOTools.TCSVTools.TCSVReader;
import org.pawaneuler.DataTypes.TCSV.TCSV;

/**
 * JUnit test for TCSVToolsTest
 */
public class TCSVReaderTest {

    @Test
    public void createTCSVTest() {
        try {
            TCSVReader reader;
            reader = org.pawaneuler.IOTools.TCSVTools.TCSVReader.createReader("./src/test/java/org/pawaneuler/IOTools/test.tcsv");

            TCSV temp = reader.readLine();

            String[] prod = { "A", "B", "C" };
            String id = "1";

            assertEquals(id, temp.getId());
            assertArrayEquals(prod, temp.getProducts());

        } catch (Exception e) {
            System.out.println("Got exception " + e);

            fail();
        }
    }

    @Test
    public void endOfFileTest() {
        try {
            TCSVReader reader;
            reader = org.pawaneuler.IOTools.TCSVTools.TCSVReader.createReader("./src/test/java/org/pawaneuler/IOTools/test.tcsv");
            TCSV temp = reader.readLine();

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