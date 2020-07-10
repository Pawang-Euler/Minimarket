package org.pawaneuler.IOTools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Abstract class for all writer
 * 
 * @author fauh45
 */
public abstract class Writer implements ExtensionChecker {
    protected File fileChecker;
    protected FileWriter fileWriter;

    protected Writer(File fileChecker, FileWriter fileWriter) {
        this.fileChecker = fileChecker;
        this.fileWriter = fileWriter;
    }
    
    /**
     * Check for the existance of the file
     *  
     * @return boolean true if exist
     */
    protected boolean isExist() {
        return this.fileChecker.exists();
    }

    public abstract void writeHeader() throws IOException;
}