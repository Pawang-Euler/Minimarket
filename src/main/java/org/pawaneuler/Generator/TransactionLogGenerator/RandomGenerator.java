package org.pawaneuler.Generator.TransactionLogGenerator;

import java.util.*;

/**
 * @author marissanura
 */
public class RandomGenerator {
    private ArrayList<String> productVariety;
    private int maxVariety;
    private Random randomizer;

    final String[] products = {"Apple","Beer","Coke","Dumpling","Eggs","Fanta","Granola",
    "Ham","Ice Cream","Jelly","Kelp","Lime","Mayonaise","Nugget","Oskadon",
    "Pear","Quinoa","Red Bean","Soursoup","Tartar","Udon","Vanilla","Whipped Cream",
    "Xavier","Yellow Radish","Zest"};

    public RandomGenerator() {
        this.randomizer = new Random();
        this.productVariety = randomProducts(10);
        this.maxVariety = productVariety.size();
        
    }

    /**
     * 
     * @param maxVariety user input to generate how many product variety
     */
    public RandomGenerator(int maxVariety) {
        this.randomizer = new Random();
        this.productVariety = randomProducts((maxVariety > 26) ? 26 : maxVariety);
        this.maxVariety = productVariety.size();
        
    }

    public int getMaxVariety() {
        return maxVariety;
    }

    public ArrayList<String> getProductVariety() {
        return productVariety;
    }

    public Random getRandomizer() {
        return randomizer;
    }

    /**
     * 
     * @return
     */
    public String[] randomStrings() {
        int randomNumOfList = randomizer.nextInt(maxVariety);
        String[] randomString = new String[randomNumOfList];
        ArrayList<String> random = randomList(randomNumOfList);
        
        for (int i = 0; i < randomNumOfList; i++) {
            randomString[i] = random.get(i);
        }
        return randomString;
    }

    /**
     * 
     * @return random list of product with no duplicate and sorted
     */
    public ArrayList<String> randomList(int randomNumOfList) {
        ArrayList<String> random = new ArrayList<String>();
        int randomIndex;
        while (random.size() < randomNumOfList) {
            randomIndex = randomizer.nextInt(maxVariety);
            
            //to make sure there's no duplicate 
            random.remove(productVariety.get(randomIndex));
            random.add(productVariety.get(randomIndex));
            
            Collections.sort(random);
        }
        return random;
    }

    /**
     * 
     * @return random list of product with no duplicate and sorted
     */
    public ArrayList<String> randomProducts(int randomNumOfList) {
        ArrayList<String> random = new ArrayList<String>();

        int randomIndex;
        while (random.size() < randomNumOfList) {
            randomIndex = randomizer.nextInt(randomNumOfList);
            
            //to make sure there's no duplicate 
            random.remove(products[randomIndex]);
            random.add(products[randomIndex]);
            
            Collections.sort(random);
        }
        return random;
    }
}