package org.pawaneuler.TSCV;

import java.util.Arrays;

public class TCSV {
    private String id;
    private String[] products;

    public TCSV(String id, String[] products) {
        this.id = id;
        this.products = products;
    }

    /**
     * Creates an object of TCSV, from a string of a line in tcsv file.
     * 
     * @param aLine a line from tcsv file
     * @return TCSV the new oject with the data from the line
     */
    public static TCSV createTCSVfromString(String aLine) {
        String[] temp = aLine.split(",", 0);

        return new TCSV(temp[0], Arrays.copyOfRange(temp, 1, temp.length));
    }

    /**
     * Getter for Id.
     * 
     * @return String the id of the tscv line.
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for the list of products.
     * 
     * @return String[] the list of products.
     */
    public String[] getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TCSV) {
            boolean idEq = this.id.equals(((TCSV) obj).getId());
            boolean prodEq = this.products.equals(((TCSV) obj).getProducts());

            return idEq && prodEq;
        }

        return false;
    }
}