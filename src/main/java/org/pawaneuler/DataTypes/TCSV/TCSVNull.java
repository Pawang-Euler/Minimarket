package org.pawaneuler.DataTypes.TCSV;

/**
 * Null TCSV representation (ADT)
 * 
 * @author fauh45
 */
public class TCSVNull extends TCSV {

    public TCSVNull() {
        super(null, null);
    }

    /**
     * Override the superclass, as the class is null class
     * 
     * @return boolean true
     */
    @Override
    public boolean isNull() {
        return true;
    }
}
