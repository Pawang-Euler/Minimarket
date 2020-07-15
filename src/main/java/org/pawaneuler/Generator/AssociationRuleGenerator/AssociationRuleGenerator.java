package org.pawaneuler.Generator.AssociationRuleGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.pawaneuler.DataTypes.Trie.*;
import org.pawaneuler.DataTypes.Rule.*;

/**
 * @author marissanura
 */
public class AssociationRuleGenerator {
    private AssociationRule AR;
    private Trie T;

    /**
     * 
     * @param T trie that want to generate to Association Rule
     */
    public AssociationRuleGenerator(Trie T) {
        this.AR = new AssociationRule();
        this.T = T;
    }

    /**
     * 
     * @param T trie that want to generate to Association Rule
     * @param minSup minimum support for generating Association Rule
     */
    public AssociationRuleGenerator(Trie T, int minSup) {
        this.AR = new AssociationRule(minSup);
        this.T = T;
    }

    /**
     * to execute the Association Rule Generation from a Trie
     */
    public void execute() {
        T.generateAllFreq();
        ArrayList<ArrayList<String>> allSubSet = generateSet();

        for (ArrayList<String> subset : allSubSet) {
            AR.getAssociationRules().addAll(generatRuleFromSubset(subset));
        }

        AR.sort();
    }

    /**
     * 
     * @return Array
     */
    public ArrayList<ArrayList<String>> generateSet() {
        int kItemset = 1; // Combinations start with 1-itemset
        ArrayList<ArrayList<String>> itemList = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> candidate = new ArrayList<ArrayList<String>>();
        ArrayList<String> candidateSingleset = new ArrayList<String>();
        candidateSingleset = generateSingleSet();
        candidate = combination(candidateSingleset,kItemset);
        do {
            kItemset++;
            pruning(candidate);
            candidateSingleset = getUniqueList(candidate);
            candidate = combination(candidateSingleset,kItemset);
            itemList.addAll(candidate);
        } while (candidate.size() > kItemset);
        return itemList;
    }

    /**
     * 
     * @return
     */
    public ArrayList<String> generateSingleSet() {
        HashSet<String> uniqSet = new HashSet<String>();
        ArrayList<Node> node = T.getNodes();
        for (Node nodes : node) {
            uniqSet.add(nodes.getProduct());
        }

        ArrayList<String> nodeSingleSet = new ArrayList<String>(uniqSet);
        return nodeSingleSet;
    }

    /**
     * 
     */
    public ArrayList<String> getUniqueList(ArrayList<ArrayList<String>> list) {
        HashSet<String> uniqSet = new HashSet<String>();
        for (ArrayList<String> itemset : list) {
            for (String string : itemset) {
                uniqSet.add(string);
            }
        }
        ArrayList<String> singleSet = new ArrayList<String>(uniqSet);
        return singleSet;
    }

    /**
     * 
     * @param list
     */
    public void pruning(ArrayList<ArrayList<String>> list) {
        ArrayList<ArrayList<String>> removedList = new ArrayList<>();
        for (ArrayList<String> itemset : list) {
            int itemsetfreq = T.getItemsetFreq(itemset,0,0);
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
    public ArrayList<ArrayList<String>> combination(ArrayList<String> candidate, int k)
    {
        ArrayList<ArrayList<String>> allCombi = new ArrayList<ArrayList<String>>();
        Combinations.getCombination(allCombi, candidate, candidate.size(), k);
        return allCombi;
    }

    /**
     * 
     * @param subset
     * @return
     */
    public ArrayList<Rule> generatRuleFromSubset(ArrayList<String> subset) {
        ArrayList<Rule> subsetRule = generateItemset(subset);
        
        double subsetSupport = generateSupport(subset);
        for (Iterator<Rule> iter = subsetRule.iterator(); iter.hasNext(); ) {
            Rule rule = iter.next();
            generateConfidence(rule);
            rule.setSupport(subsetSupport);
        }
        return subsetRule;
    }

    /**
     * 
     * @param subset
     * @return
     */
    public ArrayList<Rule> generateItemset(ArrayList<String> subset) {
        ArrayList<Rule> rules = new ArrayList<Rule>();
        ArrayList<ArrayList<String>> allCombi = new ArrayList<ArrayList<String>>();
        Combinations.getAllCombination(allCombi, subset);
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
            leftString = sub.toString();
            rightString = generateRightString(sub, subset);
            Rule temp = new Rule(leftString,rightString);
            rules.add(temp);
        }

        return rules;
    }

    public String generateRightString(ArrayList<String> leftList, ArrayList<String> subset) {
        if (subset.removeAll(leftList)) {
            return subset.toString();
        } else {
            return subset.toString();
        }
    }

    /**
     * 
     * @param subset
     * @return
     */
    public double generateSupport(ArrayList<String> subset) {
        return T.getItemsetFreq(subset,0,0) / T.getAllFreq();
    }

    /**
     * 
     * @param rule
     */
    public void generateConfidence(Rule rule) {
        rule.setConfidence(rule.getSupport() / T.getItemsetFreq(rule.leftItemlistToArrayList(),0,0));
    }
}