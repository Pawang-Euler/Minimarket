package org.pawaneuler.Experimentation.TransactionTrieCreator;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;
import org.pawaneuler.DataTypes.Trie.Trie;
import org.pawaneuler.Generator.TransactionTrieGenerator.TransactionTrieCreator;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;
import org.pawaneuler.IOTools.TriescriptTools.TriescriptWriter;

/**
 * Stress experimentation for TransactionTrieCreator
 * 
 * @author ReyRizki
 */
public class TransactionTrieCreatorExperiment {
    private int maxVariety = 26;
    private int numOfTransaction = 11062;

    @Test
    public void stressTest() {
        if (maxVariety > 26) {
            maxVariety = 26;
        }

        try {
            // create transaction trie and get time spent for the creation
            long startTime = System.currentTimeMillis();

            Trie trie = createTrie();

            long endTime = System.currentTimeMillis();

            long executionTime = endTime - startTime;

            // get total memory used for trie creation
            Runtime runtime = Runtime.getRuntime();
            runtime.gc();

            long memory = runtime.totalMemory() - runtime.freeMemory();

            System.out.println(Integer.toString(maxVariety) + " " + Integer.toString(numOfTransaction) + " " + Long.toString(executionTime) + " " + Long.toString(memory));

            this.writeTrieScript(trie);
        } catch (BadExtentionException e) {
            System.out.println(e);
            fail();
        } catch (IOException e) {
            System.out.println(e);
            fail();
        }
    }

    private String createFilePathFromRuntimeTest(int testNumber) {
        return "src/test/java/org/pawaneuler/Experimentation/TransactionLogGenerator/RuntimeTest/TransactionLogs/" + maxVariety + "/" + numOfTransaction + "/" + testNumber + ".tcsv";
    }

    private String createFilePathFromStressTest() {
        return "src/test/java/org/pawaneuler/Experimentation/TransactionLogGenerator/StressTest/TransactionLogs/" + maxVariety + "-" + numOfTransaction + ".tcsv";
    }

    private String createResultFilePath() {
        return "src/test/java/org/pawaneuler/Experimentation/TransactionTrieCreator/TriescriptResult/" + maxVariety + "-" + numOfTransaction + ".triescript";
    }

    /**
     * Method to create a trie from a source.
     * The source could be from RuntimeTest or StressTest of TransactionLogGenerator
     * 
     * @return
     * @throws BadExtentionException
     * @throws IOException
     */
    private Trie createTrie() throws BadExtentionException, IOException {
        // String filePath = createFilePathFromRuntimeTest(1);
        String filePath = createFilePathFromStressTest();

        TransactionTrieCreator creator = new TransactionTrieCreator(filePath);

        return creator.createTranssactionTrie();
    }

    /**
     * Method to write triescript from trie
     * 
     * @param trie
     * @throws BadExtentionException
     * @throws IOException
     */
    private void writeTrieScript(Trie trie) throws BadExtentionException, IOException {
        String filePath = createResultFilePath();

        TriescriptWriter writer = TriescriptWriter.createWriter(filePath);

        writer.bulkWriteLine(trie);
    }
}