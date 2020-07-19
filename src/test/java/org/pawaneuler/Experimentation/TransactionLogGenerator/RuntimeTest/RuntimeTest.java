package org.pawaneuler.Experimentation.TransactionLogGenerator.RuntimeTest;

import static org.junit.Assert.fail;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;
import org.pawaneuler.Generator.TransactionLogGenerator.TransactionLogGenerator;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;

public class RuntimeTest {
    String runtimeResultFilePath = "src/test/java/org/pawaneuler/Experimentation/TransactionLogGenerator/RuntimeTest/runtimeTestResult.txt";
    private TransactionLogGenerator generator;

    @Test
    public void mainTest() {
        int varietiesArray[] = {5, 10, 15, 20, 26};

        try {
            // clear file
            new FileWriter(runtimeResultFilePath, false).close();

            // this.iterateWithSameVariety(5);
            for (int variety: varietiesArray) {
                iterateWithSameVariety(variety);
            }
        } catch (IOException e) {
            System.out.println(e);
            fail();
        }
    }

    private void iterateWithSameVariety(int maxVariety) {
        this.generator = new TransactionLogGenerator(maxVariety);

        for (int i = 0; i < 5; i++) {
            this.iterateWithSameNumberOfTransactions(maxVariety, (int) Math.pow(10, i + 1));
        }
    }

    private void iterateWithSameNumberOfTransactions(int maxVariety, int numberOfTransactions) {
        try {
            FileWriter runtimeResultWriter = new FileWriter(runtimeResultFilePath, true);
            StringBuilder record = new StringBuilder();
            
            for (int i = 0; i < 5; i++) {
                long startTime = System.currentTimeMillis();

                runGenerator(maxVariety, numberOfTransactions, i + 1);

                long endTime = System.currentTimeMillis();

                long runTime = endTime - startTime;

                record.append(Long.toString(runTime) + " ");
            }

            record.append("\n");
            runtimeResultWriter.write(record.toString());
            runtimeResultWriter.flush();

            runtimeResultWriter.close();
        } catch (IOException e) {
            System.out.println(e);
            fail();
        }
    }

    private void runGenerator(int maxVariety, int numberOfTransactions, int testNumber) {
        String filePath = createFilePath(maxVariety, numberOfTransactions, testNumber);

        try {
            generator.generate(numberOfTransactions, filePath);
        } catch (BadExtentionException e) {
            System.out.println(e);
            fail();
        } catch (IOException e) {
            System.out.println(e);
            fail();
        }
    }

    private String createFilePath(int maxVariety, int numberOfTransactions, int testNumber) {
        return "src/test/java/org/pawaneuler/Experimentation/TransactionLogGenerator/RuntimeTest/TransactionLogs/" + maxVariety + "/" + numberOfTransactions + "/" + testNumber + ".tcsv";
    }
}