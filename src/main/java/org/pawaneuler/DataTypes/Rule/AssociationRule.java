package org.pawaneuler.DataTypes.Rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class AssociationRule 
{
    private ArrayList<Rule> associationRules;
    private int minSupport;
    
    public AssociationRule() {
        this.associationRules = new ArrayList<Rule>();
        this.minSupport = 2;
    }

    /**
     * 
     * @param minSupport to costum minimun support
     */
    public AssociationRule(int minSupport) {
        this.associationRules = new ArrayList<Rule>();
        this.minSupport = minSupport;
    }

    /**
     * 
     * @return min support
     */
    public int getMinSupport() {
        return minSupport;
    }

    /**
     * 
     * @return Array List of Rule from Association Rule 
     */
    public ArrayList<Rule> getAssociationRules() {
        return associationRules;
    }

    public void sort() {
        Collections.sort(this.associationRules);
    }
}