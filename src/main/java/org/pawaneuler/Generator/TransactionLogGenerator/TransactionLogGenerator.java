package org.pawaneuler.Generator.TransactionLogGenerator;

import java.io.*;

import org.pawaneuler.IOTools.Exceptions.BadExtentionException;
import org.pawaneuler.IOTools.TCSVTools.TCSVWriter;

public class TransactionLogGenerator {
    private RandomGenerator randomGenerator;
    private TCSVWriter fileWriter;

    public TransactionLogGenerator() {
        this.randomGenerator = new RandomGenerator();
    }

    public TransactionLogGenerator(int maxVariety) {
        this.randomGenerator = new RandomGenerator(maxVariety);
    }

    public void generate(int numOfTransaction, String filePath) throws BadExtentionException, IOException{
        try {
            fileWriter = TCSVWriter.createWriter(filePath);
            String[] singleTransaction;

            int lineOftransacion = 0;
            while(lineOftransacion < numOfTransaction) {
                singleTransaction = randomGenerator.randomStrings();
                if (singleTransaction.length != 0) {
                    fileWriter.writeLine(lineOftransacion, singleTransaction);
                    lineOftransacion++;
                }
            } 
        } catch (BadExtentionException | IOException e) {
            System.out.println(e);
        }
    }
}
