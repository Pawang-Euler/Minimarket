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

    final int VARIETY_LIMIT = products.length;

    public RandomGenerator() {
        this.randomizer = new Random();
        this.maxVariety = 10;
        this.productVariety = getNRandomStringFrom(this.maxVariety, products);     
    }

    /**
     * 
     * @param maxVariety user input to generate how many product variety
     */
    public RandomGenerator(int maxVariety) {
        this.randomizer = new Random();
        this.maxVariety = (maxVariety > VARIETY_LIMIT) ? VARIETY_LIMIT : maxVariety;
        this.productVariety = getNRandomStringFrom(this.maxVariety, products);
        
    }

    public int getMaxVariety() {
        return this.maxVariety;
    }

    public String[] getProductVariety() {
        return this.productVariety;
    }

    public Random getRandomizer() {
        return this.randomizer;
    }

    /**
     * to get random number of String
     * 
     * @return random strings with the length that passed in parameter
     */
    public String[] randomStrings() {
        int randomNumOfList = randomizer.nextInt(maxVariety);
        return getNRandomStringFrom(randomNumOfList, productVariety);
    }

    /**
     * to get random and sorted string with the length that passed in parameter
     * 
     * @param strings strings to get the random
     * @param numOfList the number of list 
     * @return random list with the length that passed in poarameter
     */
    public String[] getNRandomStringFrom(int numOfList, String[] strings) {
        String[] randomString = new String[numOfList];
        ArrayList<String> randomList = randomNListofString(strings,numOfList);
        
        for (int i = 0; i < randomList.size(); i++) {
            randomString[i] = randomList.get(i);
        }
        return randomString;
    }

    /**
     * 
     * @param strings strings to get the random list 
     * @param numOfList
     * @return random and sorted ArrayList of String with the size that passed in parameter
     */
    public ArrayList<String> randomNListofString(String[] strings, int numOfList) {
        ArrayList<String> random = new ArrayList<String>();
        int randomIndex;
        while (random.size() < numOfList) {
            randomIndex = randomizer.nextInt(maxVariety);
            
            //to make sure there's no duplicate 
            random.remove(strings[randomIndex]);
            random.add(strings[randomIndex]);
            
            Collections.sort(random);
        }
        return random;
    }
}