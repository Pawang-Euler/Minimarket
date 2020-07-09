package org.pawaneuler.IOTools;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.pawaneuler.DataTypes.TCSV.TCSV;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;
import org.pawaneuler.IOTools.TCSVTools.TCSVReader;
import org.pawaneuler.IOTools.TCSVTools.TCSVWriter;

public class TCSVWriterTest {
    @Test
    public void writeTest() {
        try {
            TCSVWriter writer = TCSVWriter.createReader("./writeTest.tcsv");
            File checker = new File("./writeTest.tcsv");

            assertTrue(checker.exists());

            String id = "1";
            String[] products = { "A", "B", "C" };
            TCSV content = new TCSV(id, products);
            writer.writeLine(content);

            TCSVReader tcsvReader = TCSVReader.createReader("./writeTest.tcsv");
            TCSV real = tcsvReader.readLine();

            assertEquals(id, real.getId());
            assertArrayEquals(products, real.getProducts());
        } catch (BadExtentionException e) {
            fail("Wrong File extension reading!");
            e.printStackTrace();
        } catch (IOException e) {
            fail("Failed to read file!");
            e.printStackTrace();
        }
    }

}
