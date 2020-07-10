package org.pawaneuler.IOTools;

import java.io.BufferedReader;

/**
 * Abstract class for all reader
 * 
 * @author fauh45
 */
public abstract class Reader implements ExtensionChecker {
    protected BufferedReader reader;

    public Reader(BufferedReader fileReader) {
        this.reader = fileReader;
    }

    public abstract boolean typeChecker(String filePath);
}
