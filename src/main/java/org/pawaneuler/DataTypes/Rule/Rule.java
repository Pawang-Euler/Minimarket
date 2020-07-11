package org.pawaneuler.DataTypes.Rule;

import java.util.ArrayList;
import org.pawaneuler.DataTypes.Trie.*;

/**
 * @author Marissa
 */
public class Rule implements Comparable<Rule> {
    private String leftItemset;
    private String rightItemset;
    private double support;
    private double confidence;


    /**
     * 
     * @param leftItemset
     * @param rightItemset
     */
    public Rule(String leftItemset, String rightItemset) {
        this.leftItemset = leftItemset;
        this.rightItemset = rightItemset;
        this.support = 0;
        this.confidence = 0;
    }

    /**
     * 
     * @return left itemset
     */
    public String getLeftItemset() {
        return leftItemset;
    }

    /**
     * 
     * @return right itemset
     */
    public String getRightItemset() {
        return rightItemset;
    }

    /**
     * 
     * @return support
     */
    public double getSupport() {
        return support;
    }

    /**
     * 
     * @return confidence
     */
    public double getConfidence() {
        return confidence;
    }

    /**
     * 
     * @param leftItemset
     */
    public void setLeftItemset(String leftItemset) {
        this.leftItemset = leftItemset;
    }

    /**
     * 
     * @param rightItemset
     */
    public void setRightItemset(String rightItemset) {
        this.rightItemset = rightItemset;
    }

    /**
     * 
     * @param support
     */
    public void setSupport(double support) {
        this.support = support;
    }

    /**
     * 
     * @param confidence
     */
    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    /**
     * 
     * @return String Array List from subset of rule
     */
    public ArrayList<String> subsetToArrayList() {
        ArrayList<String> subsetList = new ArrayList<String>();
        subsetList = leftItemlistToArrayList();
        subsetList.addAll(rightItemlistToArrayList());
        return subsetList;
    }

    /**
     * 
     * @return String Array List from left itemset of rule
     */
    public ArrayList<String> leftItemlistToArrayList() {
        ArrayList<String> leftList = new ArrayList<String>();

        String[] temp = this.leftItemset.split(",");

        for (int i = 0; i < temp.length; i++) {
            leftList.add(temp[i]);
        }

        return leftList;
    }

    /**
     * 
     * @return String Array List from right itemset of rule
     */
    public ArrayList<String> rightItemlistToArrayList() {
        ArrayList<String> rightList = new ArrayList<String>();

        String[] temp = this.rightItemset.split(",");

        for (int i = 0; i < temp.length; i++) {
            rightList.add(temp[i]);
        }

        return rightList;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        
        result.append(this.leftItemset + "----->" + this.rightItemset + ",");
        result.append(" support: " + this.support + ",");
        result.append(" confidence: " + this.confidence + ",");
        
        return result.toString();
    }

    @Override
    public int compareTo(Rule rule) {
        if (this.support == rule.getSupport()) {
            return new Double(rule.getConfidence()).compareTo(this.confidence);
        }
        else {
            return new Double(rule.getSupport()).compareTo(this.support);
        }
    }
}