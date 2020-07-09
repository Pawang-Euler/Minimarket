package org.pawaneuler.IOTools.TCSVTools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.pawaneuler.IOTools.Reader;
import org.pawaneuler.DataTypes.TCSV.TCSV;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;

public class TCSVReader extends Reader {

    private TCSVReader(BufferedReader fileReader) {
        super(fileReader);
    }

    public static TCSVReader createReader(String filePath) throws BadExtentionException, IOException {
        TCSVReader temp = new TCSVReader(new BufferedReader(new FileReader(filePath)));

        if (!temp.typeChecker(filePath)) {
            throw new BadExtentionException("File is not the correct extension, path : " + filePath);
        }

        // Read the header
        temp.reader.readLine();
        return temp;
    }

    /**
     * Read a line in the opened file, and return an object of TCSV
     *
     * @return TCSV The data from the tcsv line
     * @throws IOException Something has gone badly wrong... Cannot read the line
     */
    public TCSV readLine() throws IOException {
        String aLine = super.reader.readLine();

        return TCSV.createTCSVfromString(aLine);
    }

    /**
     * Check whether the inputted filepath is the correct file type or not
     * <p>
     * This function only checks for the filename, and not the file contents
     *
     * @param filePath the filepath of the file
     * @return boolean true if the file is correct
     */
    @Override
    public boolean typeChecker(String filePath) {
        int filePathLength = filePath.length();
        String extension = filePath.substring(filePathLength - 4, filePathLength);

        return extension.compareTo("tcsv") == 0;
    }

}