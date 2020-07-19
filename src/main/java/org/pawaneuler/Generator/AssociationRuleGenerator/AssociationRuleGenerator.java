package org.pawaneuler.Generator.AssociationRuleGenerator;

import java.util.ArrayList;
import java.util.Iterator;

import org.pawaneuler.DataTypes.Trie.*;
import org.pawaneuler.DataTypes.Rule.*;

/**
 * @author marissanura
 */
public class AssociationRuleGenerator {
    private AssociationRule associationRule;
    private Trie trie;
    private Frequency frequency;

    /**
     * 
     * @param T trie that want to generate to Association Rule
     */
    public AssociationRuleGenerator(Trie T) {
        this.associationRule = new AssociationRule();
        this.trie = T;
        this.frequency = new Frequency(T);
    }

    /**
     * 
     * @param T trie that want to generate to Association Rule
     * @param minSup minimum support for generating Association Rule
     */
    public AssociationRuleGenerator(Trie T, int minSup) {
        this.associationRule = new AssociationRule(minSup);
        this.trie = T;
        this.frequency = new Frequency(T);
    }

    /**
     * to execute the Association Rule Generation from a Trie
     */
    public void execute() {
        ArrayList<ArrayList<String>> allFreqSet = generateFrequentItemset();

        for (ArrayList<String> subset : allFreqSet) {
            associationRule.getAssociationRules().addAll(generatRuleFromSubset(subset));
        }

        associationRule.sort();
    }

    /**
     * to print all association rule
     */
    public void printAllAssociationRules() {
        this.associationRule.printAll();
    }

    @Override
    public String toString() {
        return this.associationRule.toString();
    }

    /**
     * 
     * @return 2-dimentional ArrayList from frequent itemset
     */
    private ArrayList<ArrayList<String>> generateFrequentItemset() {
        int kItemset = 1; // Combinations start with 1-itemset
        ArrayList<ArrayList<String>> frequentItemset = new ArrayList<ArrayList<String>>();
        ArrayList<String> candidateSingleset = generateProducts(); //Unique singleset for frequent itemset
        ArrayList<ArrayList<String>> candidate = combination(candidateSingleset,kItemset); //Frequent Itemset candidate

        do {
            kItemset++;
            pruning(candidate);
            candidateSingleset = getUniqueProduct(candidate);
            candidate = combination(candidateSingleset,kItemset);
            frequentItemset.addAll(candidate);
        } while (candidate.size() > kItemset);

        return frequentItemset;
    }

    /**
     * 
     * @return ArrayList of trie products
     */
    private ArrayList<String> generateProducts() {
        ArrayList<String> products = new ArrayList<String>();
        ArrayList<Node> nodes = trie.getNodes();
        for (Node node : nodes) {
            if (node.isRoot()) {
                continue;
            }
            else {
                insertUniqueItemIntoArrayList(node.getProduct(), products);
            }
        }
         
        return products;
    }

    /**
     * 
     * @param list 2-Dimensional ArrayList contain itemlist
     * @return 1-Dimentional ArrayList
     */
    private ArrayList<String> getUniqueProduct(ArrayList<ArrayList<String>> list) {
        ArrayList<String> uniqueProduct = new ArrayList<String>();
        for (ArrayList<String> itemlist : list) {
            for (String productString : itemlist) {
                insertUniqueItemIntoArrayList(productString, uniqueProduct);
            }
        }
        return uniqueProduct;
    }
    
    private void insertUniqueItemIntoArrayList(String item, ArrayList<String> list) {
        list.remove(item);
        list.add(item);
    }

    /**
     * 
     * @param list 2-Dimentional ArrayList contain candidate of frequent itemset
     */
    private void pruning(ArrayList<ArrayList<String>> list) {
        ArrayList<ArrayList<String>> removedList = new ArrayList<>();

        for (ArrayList<String> itemset : list) {
            int itemsetfreq = frequency.getItemsetFreq(itemset);
            if (itemsetfreq < associationRule.getMinSupport())
                removedList.add(itemset);
        }
        list.removeAll(removedList);
    }

    /**
     * 
     * @param candidate
     * @param k
     * @return
     */
    private ArrayList<ArrayList<String>> combination(ArrayList<String> candidate, int k) {
        return Combinations.getCombination(candidate, k);
    }

    /**
     * 
     * @param subset
     * @return
     */
    private ArrayList<Rule> generatRuleFromSubset(ArrayList<String> subset) {
        ArrayList<Rule> subsetRule = generateItemset(subset);
        
        Double subsetSupport = generateSupport(subset);
        for (Iterator<Rule> iter = subsetRule.iterator(); iter.hasNext(); ) {
            Rule rule = iter.next();
            rule.setSupport(subsetSupport);
            generateConfidence(rule);
            if (rule.getConfidence() == 0.0) {
                iter.remove();
            }
        }
        return subsetRule;
    }

    /**
     * 
     * @param subset
     * @return
     */
    private ArrayList<Rule> generateItemset(ArrayList<String> subset) {
        ArrayList<Rule> rules = new ArrayList<Rule>();
        ArrayList<ArrayList<String>> allCombi = Combinations.getAllCombination(subset);
        rules = generateRule(allCombi, subset);
        return rules;
    }

    /**
     * 
     * @param rules
     * @param subset
     */
    private ArrayList<Rule> generateRule(ArrayList<ArrayList<String>> leftSubset, ArrayList<String> subset) {
        ArrayList<Rule> rules = new ArrayList<Rule>();
        String leftString = new String();
        String rightString = new String();
        for (ArrayList<String> sub : leftSubset) {
            leftString = arrayListToString(sub);
            rightString = generateRightString(sub, subset);
            Rule temp = new Rule(leftString,rightString);
            rules.add(temp);
        }

        return rules;
    }

    /**
     * 
     * @param list
     * @return
     */
    private String arrayListToString(ArrayList<String> list) {
        String newString = list.toString();
        newString = newString.substring(1, newString.lastIndexOf(']'));
        return newString;
    }

    /**
     * 
     * @param leftList
     * @param subset
     * @return
     */
    private String generateRightString(ArrayList<String> leftList, ArrayList<String> subset) {
        ArrayList<String> rightSubset = new ArrayList<String>();
        rightSubset.addAll(subset);
        rightSubset.removeAll(leftList);
        String rightString = arrayListToString(rightSubset);
        return rightString;
    }

    /**
     * 
     * @param subset
     * @return
     */
    private Double generateSupport(ArrayList<String> subset) {
        return (new Double(frequency.getItemsetFreq(subset)) / new Double(frequency.getAllFreq()));
    }

    /**
     * 
     * @param rule
     */
    private void generateConfidence(Rule rule) {
        int leftItemsetFreq= frequency.getItemsetFreq(rule.leftItemlistToArrayList());
        if (leftItemsetFreq == 0) {
            rule.setConfidence(0.0);
        }
        else {
            rule.setConfidence(rule.getSupport()*frequency.getAllFreq() / new Double(leftItemsetFreq));
        }
    }
}