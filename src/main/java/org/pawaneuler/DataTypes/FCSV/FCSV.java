package org.pawaneuler.DataTypes.FCSV;

public class FCSV {
    private String data;
    private int frek;
    // TODO : Use some kind dynamic array for products

    public FCSV(String data, int frek) {
        this.data = data;
        this.frek = frek;
    }

    public void setFrek(int frek) {
        this.frek = frek;
    }

    public String getData() {
        return data;
    }

    public int getFrek() {
        return frek;
    }
}