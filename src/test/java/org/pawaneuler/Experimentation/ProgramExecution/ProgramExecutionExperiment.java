package org.pawaneuler.Experimentation.ProgramExecution;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;
import org.pawaneuler.DataTypes.Trie.Trie;
import org.pawaneuler.Generator.AssociationRuleGenerator.AssociationRuleGenerator;
import org.pawaneuler.Generator.TransactionLogGenerator.TransactionLogGenerator;
import org.pawaneuler.Generator.TransactionTrieGenerator.*;
import org.pawaneuler.IOTools.TriescriptTools.*;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;

/**
 * Stress experimentation for TransactionTrieCreator
 * 
 * @author ReyRizki
 */
public class ProgramExecutionExperiment {
    private int maxVariety;
    private int numOfTransaction;
    private int minSup = 5;
    private Trie testTrie;
    private File resultFile;

    @Test
    public void stressTest() {
        String resultFilePath = getResultFilePath();
        int varietiesArray[] = {5, 10};

            // clear file
            this.resultFile =  new File(resultFilePath);

            // this.iterateWithSameVariety(5);
            for (int variety: varietiesArray) {
                iterateWithSameVariety(variety);
            }
    }

    private void TransactionLogGenerator() {
        TransactionLogGenerator TLG = new TransactionLogGenerator(maxVariety);
        String filePath = createFilePath();
        try {
           TLG.generate(numOfTransaction, filePath); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to create a trie from a source.
     * The source could be from RuntimeTest or StressTest of TransactionLogGenerator
     * 
     * @return
     * @throws BadExtentionException
     * @throws IOException
     */
    private void createTrie() throws BadExtentionException, IOException {
        // String filePath = createFilePathFromRuntimeTest(1);
        String filePath = createFilePath();
        TransactionTrieCreator creator = new TransactionTrieCreator(filePath);
        this.testTrie = creator.createTranssactionTrie();
    }

    /**
     * Method to write triescript from trie
     * 
     * @param trie
     * @throws BadExtentionException
     * @throws IOException
     */
    private void writeTrieScript() throws BadExtentionException, IOException {
        try {
            String filePath = createFilePath();
            TriescriptWriter writer = TriescriptWriter.createWriter(filePath);
            writer.bulkWriteLine(this.testTrie);
        } catch (BadExtentionException | IOException e) {
            e.printStackTrace();
        }
        
    }

    private void loadTrieFromTriescript() throws BadExtentionException, IOException{
        try {
            TransactionTrieLoader TTL = new TransactionTrieLoader(createFilePath());
            this.testTrie = TTL.load();
        } catch (BadExtentionException | IOException e) {
            e.printStackTrace();
        }
    }

    private void GenerateAssosiationRule() {
        AssociationRuleGenerator generator = new AssociationRuleGenerator(this.testTrie, minSup);
        generator.execute();
    }

    private void iterateWithSameVariety(int maxVariety) {
        this.maxVariety = maxVariety;

        for (int i = 0; i < 5; i++) {
            this.numOfTransaction = (int) Math.pow(10, i + 1);
            this.iterateWithSameNumberOfTransactions();
        }
    }
    
    private void iterateWithSameNumberOfTransactions() {
        try {
            FileWriter resultWriter = new FileWriter(this.resultFile, true);
            StringBuilder record = new StringBuilder();
            
            for (int i = 0; i < 5; i++) {
                long startTime = System.currentTimeMillis();

                TransactionLogGenerator();
                createTrie();
                writeTrieScript();
                loadTrieFromTriescript();
                GenerateAssosiationRule();

                long endTime = System.currentTimeMillis();

                long runTime = endTime - startTime;

                record.append(Long.toString(runTime) + " ");
            }

            record.append("\n");
            resultWriter.write(record.toString());
            resultWriter.flush();

            resultWriter.close();
        } catch (BadExtentionException | IOException e) {
            System.out.println(e);
            fail();
        }
    }

    private String createFilePath() {
        return "src/test/java/org/pawaneuler/Experimentation/ProgramExecutionExperiment/" + this.maxVariety + "_" + this.numOfTransaction +".tcsv";
    }

    private String getResultFilePath() {
        return "src/test/java/org/pawaneuler/Experimentation/ProgramExecutionExperiment/testResult.txt";
    }
}