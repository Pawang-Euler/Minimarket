package org.pawaneuler.IOTools.TCSVTools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.pawaneuler.DataTypes.TCSV.TCSV;
import org.pawaneuler.IOTools.Writer;
import org.pawaneuler.IOTools.Exceptions.BadExtentionException;

public class TCSVWriter extends Writer {

    private TCSVWriter(File fileChecker, FileWriter fileWriter) {
        super(fileChecker, fileWriter);
    }

    public static TCSVWriter createReader(String filePath) throws BadExtentionException, IOException {
        TCSVWriter temp = new TCSVWriter(new File(filePath), new FileWriter(filePath));

        if (!temp.typeChecker(filePath)) {
            throw new BadExtentionException("File is not the correct extension, path : " + filePath);
        }

        if (!temp.fileChecker.isFile()) {
            throw new IOException("Path is not a file!, path : " + filePath);
        }

        if (!temp.isHeaderPresent(filePath)) {
            System.out.println("Creating...");
            // temp.fileChecker.createNewFile();
            temp.writeHeader();
        }

        return temp;
    }

    public void writeLine(TCSV aRecord) throws IOException {
        this.fileWriter.write(aRecord.generateRecord() + "\n");
        this.fileWriter.flush();
    }

    public boolean isHeaderPresent(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String header = reader.readLine();
            reader.close();

            if (header == null) {
                return false;
            }

            header = header.strip();
            if (header == "id,products") {
                return true;
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
    public void writeHeader() throws IOException {
        this.fileWriter.write("id,products\n");
        this.fileWriter.flush();
    }

    @Override
    public boolean typeChecker(String filePath) {
        int filePathLength = filePath.length();
        String extension = filePath.substring(filePathLength - 4, filePathLength);

        return extension.compareTo("tcsv") == 0;
    }
}
