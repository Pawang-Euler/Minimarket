package org.pawaneuler.Generator.AssociationRulesGenerator.ExperimentalMultiThreadFrequency;

import java.io.IOException;

import org.junit.Test;
import org.pawaneuler.DataTypes.Trie.Trie;
import org.pawaneuler.Generator.AssociationRuleGenerator.ExperimentalMultiThreadFrequency.MultiThreadedAssocationRules;
import org.pawaneuler.Generator.TransactionLogGenerator.TransactionLogGenerator;
import org.pawaneuler.Generator.TransactionTrieGenerator.TransactionTrieCreator;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;

public class ExperimentalMultiThreadFrequencyTest {
    @Test
    public void generateTest() {
        Trie testTrie = getTestTrieNTransactionMProducts(100, 10);
        MultiThreadedAssocationRules generator = new MultiThreadedAssocationRules(testTrie, 1);

        generator.execute();
    }

    public static Trie getTestTrieNTransactionMProducts(int maxTransaction, int maxProduct) {
        TransactionLogGenerator TLG = new TransactionLogGenerator(maxProduct);
        String filePath = "src/test/java/org/pawaneuler/Generator/AssociationRulesGenerator/ExperimentalMultiThreadFrequency/Test_" + maxTransaction + ".tcsv";
        try {
            TLG.generate(maxTransaction, filePath);

            TransactionTrieCreator creator = new TransactionTrieCreator(filePath);
            return creator.createTranssactionTrie();
        } catch (BadExtentionException | IOException e) {
            e.printStackTrace();
        }

        return new Trie();
    }
}