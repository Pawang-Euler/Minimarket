package org.pawaneuler.DataTypes.Rule.ExperimentalFreqSearch;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

import org.pawaneuler.DataTypes.Trie.Node;
import org.pawaneuler.DataTypes.Trie.Trie;

public class MultiThreadedTrieSearch extends RecursiveTask<Integer> {
    private Trie trie;
    private ArrayList<String> itemset;
    private int currentItemsetIndex;
    private int currentNodeIndex;

    public MultiThreadedTrieSearch(Trie trie, ArrayList<String> itemset) {
        this.trie = trie;
        this.itemset = itemset;
        this.currentItemsetIndex = 0;
        this.currentNodeIndex = 0;
    }

    private int getChildFreq() {
        MultiThreadedChildFreq childFreq = new MultiThreadedChildFreq(this.trie, this.currentItemsetIndex);

        return childFreq.invoke();
    }

    private int searchTrieFurther() {
        Node currentNode = trie.getNodeAt(this.currentNodeIndex);

        if (currentNode.isNull() || this.isCurrentNodeProductGreaterThanCurrentItem()) {
            return 0;
        } else {
            return this.furtherSearch();
        }
    }

    private int furtherSearch() {

        if (this.isCurrentNodeProductSameAsCurrentItem()) {
            return this.searchNextItemset();
        } else {
            return this.searchAllChildern();
        }
    }

    private int searchNextItemset() {
        this.currentItemsetIndex++;

        return this.doNextSearch();
    }

    private int doNextSearch() {
        MultiThreadedTrieSearch nextJob = this.generateNextJob();

        return nextJob.invoke();
    }

    private int searchAllChildern() {
        ArrayList<MultiThreadedTrieSearch> childJobs = this.generateChildJob();

        for (MultiThreadedTrieSearch childJob : childJobs) {
            childJob.fork();
        }

        int totalChildFrequency = 0;
        for (MultiThreadedTrieSearch childJob : childJobs) {
            totalChildFrequency += childJob.join();
        }

        return totalChildFrequency;
    }

    private MultiThreadedTrieSearch generateNextJobAtNodeIndex(int nodeIndex) {
        MultiThreadedTrieSearch nextJobObject = new MultiThreadedTrieSearch(this.trie, this.itemset);
        nextJobObject.setCurrentItemsetIndex(this.currentItemsetIndex);
        nextJobObject.setCurrentNodeIndex(nodeIndex);

        return nextJobObject;
    }

    private MultiThreadedTrieSearch generateNextJob() {
        return this.generateNextJobAtNodeIndex(this.currentNodeIndex);
    }

    private ArrayList<MultiThreadedTrieSearch> generateChildJob() {
        ArrayList<Integer> childIndexes = this.getChildIndexes();
        ArrayList<MultiThreadedTrieSearch> childJobs = new ArrayList<MultiThreadedTrieSearch>();

        for (Integer childIndex : childIndexes) {
            childJobs.add(this.generateNextJobAtNodeIndex(childIndex));
        }

        return childJobs;
    }

    private boolean isCurrentNodeProductSameAsCurrentItem() {
        return itemset.get(this.currentItemsetIndex).compareTo(trie.getNodeAt(this.currentNodeIndex).getProduct()) == 0;
    }

    private boolean isCurrentNodeProductGreaterThanCurrentItem() {
        Node currentNode = trie.getNodeAt(this.currentNodeIndex);

        if (currentNode.isRoot()) {
            return false;
        }

        boolean isNodeProductGreaterThanCurrentItem = currentNode.getProduct()
                .compareTo(itemset.get(this.currentItemsetIndex)) > 0;

        return isNodeProductGreaterThanCurrentItem;
    }

    private boolean isAllItemsetFound() {
        return this.currentItemsetIndex == this.itemset.size();
    }

    public void setCurrentItemsetIndex(int currentItemsetIndex) {
        this.currentItemsetIndex = currentItemsetIndex;
    }

    public void setCurrentNodeIndex(int currentNodeIndex) {
        this.currentNodeIndex = currentNodeIndex;
    }

    private ArrayList<Integer> getChildIndexes() {
        ArrayList<Integer> childIndex = new ArrayList<Integer>();
        Node currentNode = trie.getNodeAt(this.currentNodeIndex);

        for (int i = 0; i < currentNode.getDegree(); i++) {
            childIndex.add(currentNode.getChildIndexAt(i));
        }

        return childIndex;
    }

    @Override
    protected Integer compute() {
        if (isAllItemsetFound()) {
            return this.getChildFreq();
        }

        return this.searchTrieFurther();
    }

}