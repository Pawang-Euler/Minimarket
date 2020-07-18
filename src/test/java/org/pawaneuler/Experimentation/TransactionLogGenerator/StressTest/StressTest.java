package org.pawaneuler.Experimentation.TransactionLogGenerator.StressTest;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.pawaneuler.Generator.TransactionLogGenerator.TransactionLogGenerator;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;

public class StressTest {
    private int maxVariety = 26;
    private int numOfTransaction = 11062;
    private TransactionLogGenerator generator;

    @Test
    public void mainTest() {
        if (maxVariety > 26)
            maxVariety = 26;

        this.generator = new TransactionLogGenerator(maxVariety);

        this.doStressTest();
    }

    private void doStressTest() {
        String filePath = this.createFilePath();

        try {
            this.generator.generate(numOfTransaction, filePath);
        } catch (BadExtentionException e) {
            System.out.println(e);
            fail();
        } catch (IOException e) {
            System.out.println(e);
            fail();
        }
    }

    private String createFilePath() {
        return "src/test/java/org/pawaneuler/Experimentation/TransactionLogGenerator/StressTest/TransactionLogs/" + maxVariety + "-" + numOfTransaction + ".tcsv";
    }
}