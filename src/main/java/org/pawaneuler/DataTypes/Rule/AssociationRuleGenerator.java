import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Node;

import org.pawaneuler.DataTypes.Trie.Trie;

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

        for (Iterator<ArrayList<String>> iter = allSubSet.iterator(); iter.hasNext(); ) {
            ArrayList<String> subset = iter.next();
            AssociationRule.addAll(generatRuleFromSubset(subset));
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
        List<ArrayList<String>> candidate = new List<ArrayList<String>>();
        List<String> candidateSingleset = new ArrayList<String>();
        candidateSingleset = generateSingleSet();
        candidate = combination(candidateSingleset,kItemset);
        do {
            kItemset++;
            pruning(candidate);
            candidateSingleset = getUniqueList(candidate);
            candidate = combination(candidateSingleset,kItemset);
            itemList.addAll(candidate);
        } while (candidate.size() <= kItemset);
        return itemList;
    }

    /**
     * 
     * @return
     */
    public ArrayList<String> generateSingleSet() {
        HashSet<String> uniqSet = new HashSet<String>();
        for (Iterator<ArrayList<Node>> iter = (T.getNodes()).iterator(); iter.hasNext(); ) {
            ArrayList<Node> nodes = iter.next();
            uniqSet.add(nodes.getProduct());
        }
        ArrayList<String> nodeSingleSet = new ArrayList<String>(uniqSet);
        return nodeSingleSet;
    }

    public List<String> getUniqueList(List<ArrayList<String>> list) {
        Set<String> uniqSet = new HashSet<String>(nodes);
        for (Iterator<ArrayList<String>> iter = list.iterator(); iter.hasNext(); ) {
            ArrayList<String> itemset = iter.next();
            for (Iterator<String> it = itemset.iterator(); it.hasNext(); ) {
                String string = it.next();
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
    public void pruning(List<ArrayList<String>> list) {
        for (Iterator<List<ArrayList<String>>> iter = list.iterator(); iter.hasNext(); ) {
            ArrayList<String> itemset = iter.next();
            if (getItemsetFreq(itemset) < AR.getMinSupport())
                iter.remove();
        }
    }

    /**
     * 
     * @param candidate
     * @param k
     * @return
     */
    public ArrayList<ArrayList<String>> combination(List<String> candidate, int k)
    {
        ArrayList<ArrayLIst<String>> allCombi = new ArrayList<String>();
        allCombi = Combinations.getCombination(result, candidate, candidate.size(), k);
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
        Combinations.getAllCombination(allCombi, candidate);
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
        for (Iterator<ArrayList<String>> iter = leftSubset.iterator(); iter.hasNext(); ) {
            ArrayList<String> sub = iter.next();
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
        return T.getItemsetFreq(subset) / T.getAllFreq();
    }

    /**
     * 
     * @param rule
     */
    public void generateConfidence(Rule rule) {
        rule.setConfidence(rule.getSupport() / T.getItemsetFreq(rule.leftItemlistToArrayList()));
    }
}