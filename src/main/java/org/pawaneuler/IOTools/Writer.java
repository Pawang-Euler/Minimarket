package org.pawaneuler.IOTools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Writer implements ExtensionChecker {
    protected File fileChecker;
    protected FileWriter fileWriter;

    public Writer(File fileChecker, FileWriter fileWriter) {
        this.fileChecker = fileChecker;
        this.fileWriter = fileWriter;
    }

    protected boolean isExist() {
        return this.fileChecker.exists();
    }

    public abstract void writeHeader() throws IOException;
}