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
    private int minSup = 1;
    private Trie testTrie;
    private File resultFile;

    @Test
    public void stressTest() {
        String resultFilePath = getResultFilePath();
        int varietiesArray[] = {5, 10};

        // clear file
        this.resultFile =  new File(resultFilePath);

        for (int variety: varietiesArray) {
            iterateWithSameVariety(variety);
        }
    }

    private StringBuilder record;

    /**
     * 
     */
    private void TransactionLogGenerator() {
        TransactionLogGenerator TLG = new TransactionLogGenerator(maxVariety);
        String filePath = (createTCSVFilePath());
        try {
            long startTime = System.currentTimeMillis();

            TLG.generate(numOfTransaction, filePath);

            long endTime = System.currentTimeMillis();
            long runTime = endTime - startTime;
            this.record.append(Long.toString(runTime) + ", ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     */
    private void createTrie() throws BadExtentionException, IOException {
        // String filePath = createFilePathFromRuntimeTest(1);
        long startTime = System.currentTimeMillis();

        String filePath = (createTCSVFilePath());
        TransactionTrieCreator creator = new TransactionTrieCreator(filePath);
        this.testTrie = creator.createTranssactionTrie();

        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        this.record.append(Long.toString(runTime) + ", ");
    }

    /**
     * 
     */
    private void writeTrieScript() throws BadExtentionException, IOException {
        try {
            long startTime = System.currentTimeMillis();

            String filePath = (createTriescriptFilePath());
            TriescriptWriter writer = TriescriptWriter.createWriter(filePath);
            writer.bulkWriteLine(this.testTrie);

            long endTime = System.currentTimeMillis();
            long runTime = endTime - startTime;
            this.record.append(Long.toString(runTime) + ", ");
        } catch (BadExtentionException | IOException e) {
            e.printStackTrace();
        }
        
    }

    private void loadTrieFromTriescript() throws BadExtentionException, IOException{
        try {
            long startTime = System.currentTimeMillis();

            TransactionTrieLoader TTL = new TransactionTrieLoader(createTriescriptFilePath());
            this.testTrie = TTL.load();
                
            long endTime = System.currentTimeMillis();
            long runTime = endTime - startTime;
            this.record.append(Long.toString(runTime) + ", ");
        } catch (BadExtentionException | IOException e) {
            e.printStackTrace();
        }
    }

    private void GenerateAssosiationRule() {
        long startTime = System.currentTimeMillis();
                
        AssociationRuleGenerator generator = new AssociationRuleGenerator(this.testTrie, minSup);
        generator.execute();

        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        this.record.append(Long.toString(runTime) + ", ");
    }

    private void iterateWithSameVariety(int maxVariety) {
        this.maxVariety = maxVariety;
        writeHeader();
        
        for (int i = 0; i < 6; i++) {
            this.numOfTransaction = (int) Math.pow(10, i + 1);
            this.iterateWithSameNumberOfTransactions();
        }
    }

    private void iterateWithSameNumberOfTransactions() {
        try {
            FileWriter resultWriter = new FileWriter(this.resultFile, true);
            this.record = new StringBuilder();
            
            TransactionLogGenerator();
            createTrie();
            writeTrieScript();
            loadTrieFromTriescript();
            GenerateAssosiationRule();

            record.append("\n");
            resultWriter.write(record.toString());
            resultWriter.flush();

            resultWriter.close();
        } catch (BadExtentionException | IOException e) {
            System.out.println(e);
            fail();
        }
    }

    private void writeHeader() {
        try {
            FileWriter resultWriter = new FileWriter(this.resultFile, true);
            resultWriter.write("TLG, CreateTrie, WriteTrieScript, LoadFromTriescript, GenerateAssociationRule\n");
            resultWriter.flush();
            resultWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String createTriescriptFilePath() {
        return "src/test/java/org/pawaneuler/Experimentation/ProgramExecution/" + this.maxVariety + "_" + this.numOfTransaction + ".triescript";
    }

    private String createTCSVFilePath() {
        return "src/test/java/org/pawaneuler/Experimentation/ProgramExecution/" + this.maxVariety + "_" + this.numOfTransaction + ".tcsv";
    }

    private String getResultFilePath() {
        return "src/test/java/org/pawaneuler/Experimentation/ProgramExecution/testResult.txt";
    }
}