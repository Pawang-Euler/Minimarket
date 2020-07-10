package org.pawaneuler.IOTools.TriescriptTools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.pawaneuler.DataTypes.Trie.Node;
import org.pawaneuler.IOTools.Writer;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;

/**
 * Writer for triescript™ type file
 * 
 * @author fauh45
 */
public class TriescriptWriter extends Writer {

    private TriescriptWriter(File fileChecker, FileWriter fileWriter) {
        super(fileChecker, fileWriter);
    }

    /**
     * Factory constructor of TCSVWriter
     * 
     * @param filePath the filepath the file is at, or path where new file is wanted
     * @return TriescriptWriter new object of TriescriptWriter opened at filePath
     * @throws IOException           file cannot be opened, or filePath is not a
     *                               file
     * @throws BadExtentionException wrong file extension for the writer type
     */
    public static TriescriptWriter createWriter(String filePath) throws IOException, BadExtentionException {
        TriescriptWriter temp = new TriescriptWriter(new File(filePath), new FileWriter(filePath));

        if (!temp.typeChecker(filePath)) {
            throw new BadExtentionException(
                    "File is not the correct extension expect '.triescript', path : " + filePath);
        }

        if (!temp.fileChecker.isFile()) {
            throw new IOException("Path is not a file!, path : " + filePath);
        }

        if (!temp.isHeaderPresent(filePath)) {
            temp.writeHeader();
        }

        return temp;
    }

    /**
     * Write a line in the file, will append into the last row of the file
     * 
     * @param aRecord Node object
     * @throws IOException Cannot write file
     */
    public void writeLine(Node aRecord) throws IOException {
        this.fileWriter.write(aRecord.generateRecord() + "\n");
        this.fileWriter.flush();
    }

    /**
     * Check if the header is present in the file
     * 
     * @param filePath file location
     * @return boolean true if the header is present
     * @throws IOException file cannot be opened
     */
    public boolean isHeaderPresent(String filePath) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String header = reader.readLine();
            reader.close();

            if (header == null) {
                return false;
            }

            header = header.trim();
            if (header == "data,frek,child") {
                return true;
            } else {
                throw new IOException("File header is not standard : " + header);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found, " + e);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Reading error, " + e);
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Check whether the inputted filepath is the correct file type or not
     * <p>
     * This function only checks for the filename, and not the file contents
     * 
     * @param filePath the filePath going to be checked
     * @return boolean true if the extension is correct
     */
    @Override
    public boolean typeChecker(String filePath) {
        int filePathLength = filePath.length();
        String extension = filePath.substring(filePathLength - 10, filePathLength);

        return extension.compareTo("triescript") == 0;
    }

    /**
     * Writes header in the file
     * 
     * @throws IOException cannot write in the file
     */
    @Override
    public void writeHeader() throws IOException {
        this.fileWriter.write("data,frek,child\n");
        this.fileWriter.flush();
    }

}