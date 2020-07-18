package org.pawaneuler.Generator.TransactionLogGenerator;

import java.util.*;

/**
 * @author marissanura
 */
public class RandomGenerator {
    private String[] productVariety;
    private int maxVariety;
    private Random randomizer;

    final String[] products = {"Apple","Beer","Coke","Dumpling","Eggs","Fanta","Granola",
    "Ham","Ice Cream","Jelly","Kelp","Lime","Mayonaise","Nugget","Oskadon",
    "Pear","Quinoa","Red Bean","Soursoup","Tartar","Udon","Vanilla","Whipped Cream",
    "Xavier","Yellow Radish","Zest"};

    public RandomGenerator() {
        this.randomizer = new Random();
        this.maxVariety = 10;
        this.productVariety = getStrings(products, maxVariety);      
    }

    /**
     * 
     * @param maxVariety user input to generate how many product variety
     */
    public RandomGenerator(int maxVariety) {
        this.randomizer = new Random();
        this.maxVariety = (maxVariety > 26) ? 26 : maxVariety;
        this.productVariety = getStrings(products, this.maxVariety);
        
        
    }

    public int getMaxVariety() {
        return maxVariety;
    }

    public String[] getProductVariety() {
        return productVariety;
    }

    public Random getRandomizer() {
        return randomizer;
    }

    /**
     * to get random number of String
     * 
     * @return
     */
    public String[] randomStrings() {
        int randomNumOfList = randomizer.nextInt(maxVariety);
        return getStrings(productVariety, randomNumOfList);
    }

    /**
     * 
     * @param numOfList number of list
     * @return random list with the length that passed in poarameter
     */
    public String[] getStrings(String[] strings, int numOfList) {
        String[] randomString = new String[numOfList];
        ArrayList<String> random = randomList(strings,numOfList);
        
        for (int i = 0; i < random.size(); i++) {
            randomString[i] = random.get(i);
        }
        return randomString;
    }

    /**
     * to get random list with random generator
     * 
     * @return random list of product with no duplicate and sorted
     */
    public ArrayList<String> randomList(String[] strings, int randomNumOfList) {
        ArrayList<String> random = new ArrayList<String>();
        int randomIndex;
        while (random.size() < randomNumOfList) {
            randomIndex = randomizer.nextInt(maxVariety);
            
            //to make sure there's no duplicate 
            random.remove(strings[randomIndex]);
            random.add(strings[randomIndex]);
            
            Collections.sort(random);
        }
        return random;
    }
}