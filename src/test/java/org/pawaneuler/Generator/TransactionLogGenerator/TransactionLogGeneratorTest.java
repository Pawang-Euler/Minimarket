package org.pawaneuler.Generator.TransactionLogGenerator;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;

public class TransactionLogGeneratorTest {
    @Test
    public void writeTransactionLogTest() {
        try {
            TransactionLogGenerator TLG = new TransactionLogGenerator();
        
            TLG.generate(100, "./src/test/java/org/pawaneuler/Generator/TransactionLogGenerator/transactionLogTest.tcsv");
            TLG.generate(1000, "./src/test/java/org/pawaneuler/Generator/TransactionLogGenerator/transactionLogTest2.tcsv");
        } catch (BadExtentionException e) {
            System.out.println(e);
            fail();
        } catch (IOException e) {
            System.out.println(e);
            fail();
        }
    }

}