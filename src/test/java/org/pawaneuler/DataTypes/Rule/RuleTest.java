package org.pawaneuler.DataTypes.Rule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Marissa Nur A
 */
public class RuleTest {
    /**
     * Basic node testing for constructor, getter, and setter.
     */
    @Test
    public void basicRuleTest() {
        Rule rule = new Rule("Bread,Cereal,Milk","Coke,Diaper");
        rule.setSupport(0.3);
        rule.setConfidence(0.6);

        String expectedLeftString = "Bread,Cereal,Milk";
        
        assertEquals("Left String isn't same", expectedLeftString, rule.getLeftItemset());

        Double expectedConfidence = 0.6;
        assertEquals("Confidence isn't same", expectedConfidence, rule.getConfidence());

        Double expectedSupport = 0.3;
        assertEquals("Support isn't same", expectedSupport, rule.getSupport());
    }

    @Test
    public void toStringTest() {
        try {
            Rule rule = new Rule("Bread,Cereal,Milk","Coke,Diaper");
            rule.setSupport(0.3);
            rule.setConfidence(0.6);
            String expectedString = "Bread,Cereal,Milk -----> Coke,Diaper, support: 0.3, confidence: 0.6";

            assertEquals("String isn't same ", expectedString, rule.toString());
        } catch (Exception e) {
            System.err.println(e);

            fail();
        }
    }

    @Test
    public void stringItemListToArrayListTest() {
        try {
            Rule rule = new Rule("Bread, Cereal, Milk","Coke, Diaper");
            ArrayList<String> expectedLeftArrayList = new ArrayList<String>();
            ArrayList<String> expectedRightArrayList = new ArrayList<String>();
            
            expectedLeftArrayList.add("Bread");
            expectedLeftArrayList.add("Cereal");
            expectedLeftArrayList.add("Milk");

            assertEquals("Left Array List isn't same", expectedLeftArrayList, rule.leftItemlistToArrayList());

            expectedRightArrayList.add("Coke");
            expectedRightArrayList.add("Diaper");

            assertEquals("Right Array List isn't same", expectedRightArrayList, rule.rightItemlistToArrayList());
        } catch (Exception e) {
            System.err.println(e);

            fail();
        }
    }

    @Test
    public void compareToTest() {
        Rule rule = new Rule("Bread,Cereal,Milk","Coke,Diaper");
        rule.setSupport(0.3);
        rule.setConfidence(0.6);

        Rule rule2 = new Rule("Bread,Cereal,Milk","Coke,Diaper");
        rule.setSupport(0.3);
        rule.setConfidence(0.7); 

        assertTrue("compareTo Method isn't work", rule.compareTo(rule2) < 0);
    }
}