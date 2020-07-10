package org.pawaneuler.DataTypes.TCSV;

import org.pawaneuler.DataTypes.Writeable;

import java.util.Arrays;

/**
 * TCSV representation (ADT)
 * 
 * @author fauh45
 */
public class TCSV implements Writeable {
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
        if (aLine == null) {
            return new TCSVNull();
        }

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

    
    /** 
     * Return if the class a type of null
     * 
     * @return boolean
     */
    public boolean isNull() {
        return false;
    }

    
    /** 
     * Overriding the equals
     * 
     * @param obj object to be compared
     * @return boolean true if equal, false if not
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TCSV) {
            boolean idEq = this.id.equals(((TCSV) obj).getId());
            boolean prodEq = this.products.equals(((TCSV) obj).getProducts());

            return idEq && prodEq;
        }

        return false;
    }

    
    /** 
     * Implementation of Writeable
     * 
     * @return String a Record to write into file
     */
    @Override
    public String generateRecord() {
        String temp = "";

        for (String item : this.products) {
            temp += item;
            temp += ",";
        }

        String finalString = this.id + "," + temp.substring(0, temp.lastIndexOf(','));
        return finalString;
    }
}