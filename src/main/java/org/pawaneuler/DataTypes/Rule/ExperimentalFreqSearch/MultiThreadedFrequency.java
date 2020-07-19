package org.pawaneuler.DataTypes.Rule.ExperimentalFreqSearch;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

import org.pawaneuler.DataTypes.Rule.Frequency;
import org.pawaneuler.DataTypes.Trie.Trie;

public class MultiThreadedFrequency extends Frequency {
    private ForkJoinPool pool = null;
    private final int MAX_THREAD = 3;

    public MultiThreadedFrequency(Trie T) {
        super(T);
    }

    @Override
    protected int generateAllFreq() {
        this.checkForPool();
        // Get all frequency from the root of it
        MultiThreadedChildFreq frequencySearcher = new MultiThreadedChildFreq(this.T, 0);

        return pool.invoke(frequencySearcher);
    }

    @Override
    public int getItemsetFreq(ArrayList<String> itemset) {
        this.checkForPool();
        this.itemset = itemset;
        MultiThreadedTrieSearch itemsetSearcher = new MultiThreadedTrieSearch(this.T, this.itemset);

        return pool.invoke(itemsetSearcher);
    }

    private void checkForPool() {
        if (this.isPoolNull()) {
            this.pool = new ForkJoinPool(MAX_THREAD);
        }
    }

    private boolean isPoolNull() {
        return pool == null;
    }
}