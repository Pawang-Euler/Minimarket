package org.pawaneuler.Generator.TransactionLogGenerator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RandomGeneratorTest {
    
    @Test
    public void basicRandomGeneratorTest() {
        RandomGenerator random = new RandomGenerator();
        RandomGenerator random30 = new RandomGenerator(30);

        int expectedProductVariety = 10;
        int expectedMaxVariety = 26;
        
        assertNotNull("Random generator is NULL", random.getRandomizer());
        assertEquals("Product variety quantity didn't match expected", expectedProductVariety, random.getProductVariety().size());
        assertTrue("Product Max variety is larger than product sample", random30.getMaxVariety() <= expectedMaxVariety);
    }

    @Test
    public void randomResultTest() {
        RandomGenerator random = new RandomGenerator();
        String[] randomResult = random.randomStrings();

        int expectedRandomLength = 10;

        assertTrue("Random length is larger than expected", randomResult.length <= expectedRandomLength);
        if (randomResult.length > 1) {
            assertTrue("Result isn't ascending", randomResult[0].compareTo(randomResult[1]) < 0);
        }
    }
}