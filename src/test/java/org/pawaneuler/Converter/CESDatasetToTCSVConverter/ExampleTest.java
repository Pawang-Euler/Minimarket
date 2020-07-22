package org.pawaneuler.Converter.CESDatasetToTCSVConverter;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;

public class ExampleTest {
    @Test
    public void exampleTest() {
        String sourceFilePath = "src/test/java/org/pawaneuler/Converter/CESDatasetToTCSVConverter/example.csv";
        String resultFilePath = "src/test/java/org/pawaneuler/Converter/CESDatasetToTCSVConverter/exampleResult.tcsv";

        try {
            CESDatasetToTCSVConverter converter = new CESDatasetToTCSVConverter(sourceFilePath, resultFilePath);
            
            converter.convertCESDatasetToTCSV();
        } catch (BadExtentionException e) {
            fail("Wrong File extension reading!");
            e.printStackTrace();
        } catch (IOException e) {
            fail("Failed to read file!");
            e.printStackTrace();
        }
    }
}