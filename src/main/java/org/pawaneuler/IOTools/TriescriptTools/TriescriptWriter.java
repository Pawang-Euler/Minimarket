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

public class TriescriptWriter extends Writer {

    public TriescriptWriter(File fileChecker, FileWriter fileWriter) {
        super(fileChecker, fileWriter);
    }

    public static TriescriptWriter createWriter(String filePath) throws IOException, BadExtentionException {
        TriescriptWriter temp = new TriescriptWriter(new File(filePath), new FileWriter(filePath));

        if (!temp.typeChecker(filePath)) {
            throw new BadExtentionException("File is not the correct extension expect '.triescript', path : " + filePath);
        }

        if (!temp.fileChecker.isFile()) {
            throw new IOException("Path is not a file!, path : " + filePath);
        }

        if (!temp.isHeaderPresent(filePath)) {
            temp.writeHeader();
        }

        return temp;
    }

    public void writeLine(Node aRecord) throws IOException {
        this.fileWriter.write(aRecord.generateRecord() + "\n");
        this.fileWriter.flush();
    }

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

    @Override
    public boolean typeChecker(String filePath) {
        int filePathLength = filePath.length();
        String extension = filePath.substring(filePathLength - 10, filePathLength);

        return extension.compareTo("triescript") == 0;
    }

    @Override
    public void writeHeader() throws IOException {
        this.fileWriter.write("data,frek,child\n");
        this.fileWriter.flush();
    }

}