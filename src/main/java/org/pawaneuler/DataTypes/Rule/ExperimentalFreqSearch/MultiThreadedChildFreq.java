package org.pawaneuler.DataTypes.Rule.ExperimentalFreqSearch;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

import org.pawaneuler.DataTypes.Trie.Node;
import org.pawaneuler.DataTypes.Trie.Trie;

public class MultiThreadedChildFreq extends RecursiveTask<Integer> {
    private Trie trie;
    private int currentNodeIndex;

    public MultiThreadedChildFreq(Trie trie, int currentNodeIndex) {
        this.trie = trie;
        this.currentNodeIndex = currentNodeIndex;
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

    private ArrayList<Integer> getAllChildIndex() {
        ArrayList<Integer> childIndex = new ArrayList<Integer>();
        Node currentNode = trie.getNodeAt(this.currentNodeIndex);

        for (int i = 0; i < currentNode.getDegree(); i++) {
            childIndex.add(currentNode.getChildIndexAt(i));
        }

        return childIndex;
    }

    @Override
    protected Integer compute() {
        Node currentNode = trie.getNodeAt(this.currentNodeIndex);
        return currentNode.getFrequency() + this.splitRecursion();
    }
}