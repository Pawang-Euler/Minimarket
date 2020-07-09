package org.pawaneuler.IOTools;

import java.io.BufferedReader;

public abstract class Reader implements ExtensionChecker {
    protected BufferedReader reader;

    public Reader(BufferedReader fileReader) {
        this.reader = fileReader;
    }

    public abstract boolean typeChecker(String filePath);
}
