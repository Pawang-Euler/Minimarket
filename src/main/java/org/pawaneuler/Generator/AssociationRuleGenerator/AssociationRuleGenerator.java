package org.pawaneuler.Generator.AssociationRuleGenerator;

import java.util.ArrayList;
import java.util.Iterator;

import org.pawaneuler.DataTypes.Trie.*;
import org.pawaneuler.DataTypes.Rule.*;

/**
 * @author marissanura
 */
public class AssociationRuleGenerator {
    private AssociationRule AR;
    private Trie T;
    private Frequency frequency;

    /**
     * 
     * @param T trie that want to generate to Association Rule
     */
    public AssociationRuleGenerator(Trie T) {
        this.AR = new AssociationRule();
        this.T = T;
        this.frequency = new Frequency(T);
    }

    /**
     * 
     * @param T trie that want to generate to Association Rule
     * @param minSup minimum support for generating Association Rule
     */
    public AssociationRuleGenerator(Trie T, int minSup) {
        this.AR = new AssociationRule(minSup);
        this.T = T;
        this.frequency = new Frequency(T);
    }

    /**
     * to execute the Association Rule Generation from a Trie
     */
    public void execute() {
        T.generateAllFreq();
        ArrayList<ArrayList<String>> allFreqSet = generateFrequentItemset();

        for (ArrayList<String> subset : allFreqSet) {
            AR.getAssociationRules().addAll(generatRuleFromSubset(subset));
        }

        AR.sort();
    }

    /**
     * to print all association rule
     */
    public void printAllAssociationRules() {
        this.AR.printAll();
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
        ArrayList<Node> nodes = T.getNodes();
        for (Node node : nodes) {
            if (node.isRoot()) {
                continue;
            }
            else {
            //Make sure there's no duplicate
            products.remove(node.getProduct());
            products.add(node.getProduct());
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
            for (String string : itemlist) {
                //Make sure there's no duplicate
                uniqueProduct.remove(string);
                uniqueProduct.add(string);
            }
        }
        return uniqueProduct;
    }

    /**
     * 
     * @param list 2-Dimentional ArrayList contain candidate of frequent itemset
     */
    private void pruning(ArrayList<ArrayList<String>> list) {
        ArrayList<ArrayList<String>> removedList = new ArrayList<>();

        for (ArrayList<String> itemset : list) {
            int itemsetfreq = frequency.getItemsetFreq(itemset);
            if (itemsetfreq < AR.getMinSupport())
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
        return (new Double(frequency.getItemsetFreq(subset)) / new Double(T.getAllFreq()));
    }

    /**
     * 
     * @param rule
     */
    private void generateConfidence(Rule rule) {
        rule.setConfidence(rule.getSupport()*T.getAllFreq() / new Double(frequency.getItemsetFreq(rule.leftItemlistToArrayList())));
    }
}