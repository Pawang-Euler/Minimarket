package org.pawaneuler.DataTypes.Rule;

import java.io.IOException;

import org.junit.Test;
import org.pawaneuler.DataTypes.Trie.Trie;
import org.pawaneuler.Generator.AssociationRuleGenerator.AssociationRuleGenerator;
import org.pawaneuler.Generator.TransactionLogGenerator.TransactionLogGenerator;
import org.pawaneuler.Generator.TransactionTrieGenerator.TransactionTrieCreator;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;

/**
 * RuleGenerationTest
 */
public class RuleGenerationTest {

    @Test
    public void generateTest() {
        Trie testTrie = getTestTrieNTransactionMProducts(100,10);
        AssociationRuleGenerator generator = new AssociationRuleGenerator(testTrie, 1);

        generator.execute();
    }

    public static Trie getTestTrieNTransactionMProducts(int maxTransaction, int maxProduct) {
        TransactionLogGenerator TLG = new TransactionLogGenerator(maxProduct);
        String filePath = "src/test/java/org/pawaneuler/DataTypes/Rule/Test_" + maxTransaction + ".tcsv";
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