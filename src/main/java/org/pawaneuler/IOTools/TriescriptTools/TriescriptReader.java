package org.pawaneuler.IOTools.TriescriptTools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.pawaneuler.DataTypes.Trie.Node;
import org.pawaneuler.IOTools.Reader;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;

public class TriescriptReader extends Reader {

    private TriescriptReader(BufferedReader fileReader) {
        super(fileReader);
    }

    public static TriescriptReader createReader(String filePath) throws BadExtentionException, IOException {
        TriescriptReader temp = new TriescriptReader(new BufferedReader(new FileReader(filePath)));

        if (!temp.typeChecker(filePath)) {
            throw new BadExtentionException("File is not the correct extension, path : " + filePath);
        }

        // Read the header
        temp.reader.readLine();
        return temp;
    }

    public Node readLine() throws IOException {
        String aLine = super.reader.readLine();

        return Node.stringToNode(aLine);
    }

    @Override
    public boolean typeChecker(String filePath) {
        int filePathLength = filePath.length();
        String extension = filePath.substring(filePathLength - 10, filePathLength);

        return extension.compareTo("triescript") == 0;
    }
    
}