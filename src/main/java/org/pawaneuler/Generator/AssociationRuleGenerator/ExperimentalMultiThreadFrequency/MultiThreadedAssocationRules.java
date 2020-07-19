package org.pawaneuler.Generator.AssociationRuleGenerator.ExperimentalMultiThreadFrequency;

import org.pawaneuler.DataTypes.Rule.ExperimentalFreqSearch.MultiThreadedFrequency;
import org.pawaneuler.DataTypes.Trie.Trie;
import org.pawaneuler.Generator.AssociationRuleGenerator.AssociationRuleGenerator;

public class MultiThreadedAssocationRules extends AssociationRuleGenerator {

    public MultiThreadedAssocationRules(Trie T) {
        super(T);

        this.frequency = new MultiThreadedFrequency(T);
    }

    public MultiThreadedAssocationRules(Trie T, int minSup) {
        super(T, minSup);

        this.frequency = new MultiThreadedFrequency(T);
    }
}