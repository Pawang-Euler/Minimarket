package org.pawaneuler.TCSVTools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.pawaneuler.TCSVTools.Exceptions.BadExtentionException;
import org.pawaneuler.TSCV.TCSV;

public class Reader {
    private BufferedReader fileReader;

    public Reader(String filePath) throws BadExtentionException, FileNotFoundException {
        if (!this.typeChecker(filePath)) {
            throw new BadExtentionException("File is not the correct extension, path : " + filePath);
        }

        this.fileReader = new BufferedReader(new FileReader(filePath));
        try {
            this.fileReader.readLine();
        } catch (IOException e) {
            System.out.println("Cannot read the header : " + e);
        }
    }

    /**
     * Read a line in the oppened file, and return an oject of TCSV
     * 
     * @return TCSV The data from the tcsv line
     * @throws IOException Something has gone badly wrong... Cannot read the line
     */
    public TCSV readLine() throws IOException {
        String aLine = this.fileReader.readLine();

        return TCSV.createTCSVfromString(aLine);
    }

    /**
     * Check wether the inputed filepath is the correct file type or not
     * 
     * This function only checks for the filename, and not the file contents
     * 
     * @param filePath the filepath of the file
     * @return boolean true if the file is correct
     */
    private boolean typeChecker(String filePath) {
        int filePathLength = filePath.length();
        String extention = filePath.substring(filePathLength - 4, filePathLength);

        return extention.compareTo("tcsv") == 0;
    }
}