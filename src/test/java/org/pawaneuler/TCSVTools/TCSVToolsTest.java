package org.pawaneuler.TCSVTools;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.pawaneuler.TSCV.TCSV;

/**
 * JUnit test for TCSVToolsTest
 */
public class TCSVToolsTest {

    @Test
    public void createTCSVTest() {
        try {
            Reader reader = new Reader("./src/test/java/org/pawaneuler/TCSVTools/test.tcsv");

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
            Reader reader = new Reader("./src/test/java/org/pawaneuler/TCSVTools/test.tcsv");
            TCSV temp = reader.readLine();

            while(temp != null) {
                // Do Something, nothing as a test

                temp = reader.readLine();
            }

        } catch (Exception e) {
            System.out.println("Got exception " + e);

            fail();
        }
    }
}