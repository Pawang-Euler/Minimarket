package org.pawaneuler.IOTools.TriescriptTools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.pawaneuler.DataTypes.Trie.Node;
import org.pawaneuler.IOTools.Reader;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;

/**
 * Reader for triescript™ type file
 * 
 * @author fauh45
 */
public class TriescriptReader extends Reader {

    private TriescriptReader(BufferedReader fileReader) {
        super(fileReader);
    }

    /**
     * Factory constructor for TriescriptReader
     * 
     * @param filePath the full path the triescript file is at
     * @return TriescriptReader new object of TriescriptReader opened at the
     *         filePath
     * @throws BadExtentionException thrown at wrong file extension
     * @throws IOException           file cannot be opened
     */
    public static TriescriptReader createReader(String filePath) throws BadExtentionException, IOException {
        TriescriptReader temp = new TriescriptReader(new BufferedReader(new FileReader(filePath)));

        if (!temp.typeChecker(filePath)) {
            throw new BadExtentionException("File is not the correct extension, path : " + filePath);
        }

        // Read the header
        temp.reader.readLine();
        return temp;
    }

    /**
     * Read a line in the opened file, and return an object of Node
     * 
     * @return Node A new Object of Node with data from the file
     * @throws IOException Cannot read the file
     */
    public Node readLine() throws IOException {
        String aLine = super.reader.readLine();

        return Node.stringToNode(aLine);
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
        String extension = filePath.substring(filePathLength - 10, filePathLength);

        return extension.compareTo("triescript") == 0;
    }

}