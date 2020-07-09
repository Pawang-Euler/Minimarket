package org.pawaneuler.DataTypes.TCSV;

public class TCSVNull extends TCSV {

    public TCSVNull() {
        super(null, null);
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
