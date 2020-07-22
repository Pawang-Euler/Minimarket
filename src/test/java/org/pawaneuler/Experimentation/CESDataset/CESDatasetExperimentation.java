package org.pawaneuler.Experimentation.CESDataset;

import static org.junit.Assert.fail;

import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

import org.junit.BeforeClass;
import org.junit.Test;
import org.pawaneuler.Converter.CESDatasetToTCSVConverter.CESDatasetToTCSVConverter;
import org.pawaneuler.DataTypes.Trie.Trie;
import org.pawaneuler.Generator.AssociationRuleGenerator.AssociationRuleGenerator;
import org.pawaneuler.Generator.TransactionTrieGenerator.TransactionTrieCreator;
import org.pawaneuler.Generator.TransactionTrieGenerator.TransactionTrieLoader;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;
import org.pawaneuler.IOTools.TriescriptTools.TriescriptWriter;

/**
 * @author ReyRizki
 */
public class CESDatasetExperimentation {
    private static String CESDatasetCSV = "src/test/java/org/pawaneuler/Experimentation/CESDataset/ces_hybrid.csv";
    private static String CESDatasetTCSV = "src/test/java/org/pawaneuler/Experimentation/CESDataset/CESDataset.tcsv";
    private static String CESDatasetTrie = "src/test/java/org/pawaneuler/Experimentation/CESDataset/CESDatasetTrie.triescript";

    /**
     * Dataset preparation. Include convert CES Dataset to TCSV and make triescript from the TCSV file
     */
    @BeforeClass
    public static void prepareExperimentation() {
        try {
            // convert CES Dataset to TCSV
            CESDatasetToTCSVConverter converter = new CESDatasetToTCSVConverter(CESDatasetCSV, CESDatasetTCSV);
            converter.convertCESDatasetToTCSV();
            System.out.println("Convertion is completed");

            // make trie
            TransactionTrieCreator creator = new TransactionTrieCreator(CESDatasetTCSV);
            Trie trie = creator.createTranssactionTrie();
            System.out.println("Transaction Trie Creation is completed");

            // write trie to triescript
            TriescriptWriter writer = TriescriptWriter.createWriter(CESDatasetTrie);
            writer.bulkWriteLine(trie);
            System.out.println("Transaction Trie is written");
            
            System.out.println("Preparation is done, proceed to the experimentation");
        } catch (BadExtentionException e) {
            fail("Wrong File extension reading!");
            e.printStackTrace();
        } catch (IOException e) {
            fail("Failed to read file!");
            e.printStackTrace();
        }
    }

    @Test
    public void runExperimentation() {
        try {
            TransactionTrieLoader loader = new TransactionTrieLoader(CESDatasetTrie);
            Trie trie = loader.load();
            System.out.println("Transaction Trie is loaded");

            long startTime = System.currentTimeMillis();

            AssociationRuleGenerator generator = new AssociationRuleGenerator(trie, 3);
            generator.execute();

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            System.out.println("Association rules generated");

            // get memory used
            Runtime runtime = Runtime.getRuntime();
            runtime.gc();
            long memoryUsed = runtime.totalMemory() - runtime.freeMemory();

            writeExperimentResult(executionTime, memoryUsed);

            System.out.println("Experimentation is done");
        } catch (BadExtentionException e) {
            fail("Wrong File extension reading!");
            e.printStackTrace();
        } catch (IOException e) {
            fail("Failed to read file!");
            e.printStackTrace();
        }
    }

    public void writeExperimentResult(long executionTime, long memoryUsed) {
        String experimentResultFilePath = "src/test/java/org/pawaneuler/Experimentation/CESDataset/experimentResult.csv";
        
        // create a record to write
        String[] result = new String[2];
        result[0] = Long.toString(executionTime);
        result[1] = Long.toString(memoryUsed);

        try {
            CSVWriter writer = new CSVWriter(new FileWriter(experimentResultFilePath, true));
            
            writer.writeNext(result);

            writer.close();
        } catch (IOException e) {
            fail("Failed to read file!");
            e.printStackTrace();
        }
    }
}