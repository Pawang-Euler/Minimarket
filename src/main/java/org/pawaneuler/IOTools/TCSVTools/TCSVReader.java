package org.pawaneuler.IOTools.TCSVTools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.pawaneuler.IOTools.Reader;
import org.pawaneuler.DataTypes.TCSV.TCSV;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;

/**
 * Reader for TCSV type file
 * 
 * @author fauh45
 */
public class TCSVReader extends Reader {

    private TCSVReader(BufferedReader fileReader) {
        super(fileReader);
    }

    /**
     * Factory constructor for TCSVReader
     * 
     * @param filePath the full path the tcsv file is at
     * @return TCSVReader new object of TCSVReader opened at the filePath
     * @throws BadExtentionException thrown at wrong file extension
     * @throws IOException           file cannot be opened
     */
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
     * @return TCSV New object of TCSV using the data from the file
     * @throws IOException Cannot read the line
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