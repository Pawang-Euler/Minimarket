package org.pawaneuler.DataTypes.Rule.ExperimentalFreqSearch;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

import org.pawaneuler.DataTypes.Trie.Node;
import org.pawaneuler.DataTypes.Trie.Trie;

public class MultiThreadedChildFreq extends RecursiveTask<Integer> {
    private Trie trie;
    private int currentNodeIndex;
    private final int CHILD_THRESHOLD = 3;

    public MultiThreadedChildFreq(Trie trie, int currentNodeIndex) {
        this.trie = trie;
        this.currentNodeIndex = currentNodeIndex;
    }

    private int normalRecursionAtNodeIndex(int nodeIndex) {
        Node currNode = trie.getNodeAt(nodeIndex);

        if (this.isNodeEligibleForSplitting(currNode)) {
            return this.splitRecursionStartingOnNodeIndexed(nodeIndex);
        } else {
            return this.continueRecursionOnAllChildOfNodeIndexed(nodeIndex);
        }
    }

    private int continueRecursionOnAllChildOfNodeIndexed(int nodeIndex) {
        ArrayList<Integer> childIndexes = this.getAllChildIndexOfNodeIndexed(nodeIndex);
        int totalChildFrequency = 0;

        for (Integer childIndex : childIndexes) {
            totalChildFrequency += this.normalRecursionAtNodeIndex(childIndex);
        }

        return totalChildFrequency;
    }

    private int splitRecursionStartingOnNodeIndexed(int nodeIndex) {
        this.currentNodeIndex = nodeIndex;

        return this.splitRecursion();
    }

    private int splitRecursion() {
        ArrayList<MultiThreadedChildFreq> subtasks = this.generateChildTask();

        for (MultiThreadedChildFreq subtask : subtasks) {
            subtask.fork();
        }

        int sumOfChildFrequency = 0;
        for (MultiThreadedChildFreq subtask : subtasks) {
            sumOfChildFrequency += subtask.join();
        }

        return sumOfChildFrequency;
    }

    private ArrayList<MultiThreadedChildFreq> generateChildTask() {
        ArrayList<MultiThreadedChildFreq> subtask = new ArrayList<MultiThreadedChildFreq>();
        ArrayList<Integer> childrenIndexes = this.getAllChildIndex();

        for (Integer childrenIndex : childrenIndexes) {
            subtask.add(this.generateTaksOnNodeIndex(childrenIndex));
        }

        return subtask;
    }

    private MultiThreadedChildFreq generateTaksOnNodeIndex(int nodeIndex) {
        return new MultiThreadedChildFreq(this.trie, nodeIndex);
    }

    private ArrayList<Integer> getAllChildIndexOfNodeIndexed(int nodeIndex) {
        ArrayList<Integer> childIndex = new ArrayList<Integer>();
        Node currentNode = trie.getNodeAt(nodeIndex);

        for (int i = 0; i < currentNode.getDegree(); i++) {
            childIndex.add(currentNode.getChildIndexAt(i));
        }

        return childIndex;
    }

    private ArrayList<Integer> getAllChildIndex() {
        return this.getAllChildIndexOfNodeIndexed(this.currentNodeIndex);
    }

    private boolean isNodeEligibleForSplitting(Node node) {
        return node.getDegree() >= CHILD_THRESHOLD;
    }

    @Override
    protected Integer compute() {
        Node currentNode = trie.getNodeAt(this.currentNodeIndex);
        return currentNode.getFrequency() + this.splitRecursion();
    }
}