package org.pawaneuler.Converter.CESDatasetToTCSVConverter;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVReader;

import org.pawaneuler.IOTools.Exceptions.BadExtentionException;
import org.pawaneuler.IOTools.TCSVTools.TCSVWriter;

/**
 * Class to convert CES Dataset To TCSV Converter For more information, read the
 * README.md
 * 
 * @author ReyRizki
 */
public class CESDatasetToTCSVConverter {
    private CSVReader reader;
    private TCSVWriter writer;

    public CESDatasetToTCSVConverter(String sourceFilePath, String resultFilePath)
            throws BadExtentionException, IOException {
        this.reader = new CSVReader(new FileReader(sourceFilePath));
        this.writer = TCSVWriter.createWriter(resultFilePath);
    }

    public void convertCESDatasetToTCSV() throws IOException {
        String[] transactionInformation; // to store a line from CES Dataset CSV, index 0 = id, index 1 = item

        // read the first product
        transactionInformation = reader.readNext();

        while (transactionInformation != null) {
            // read information of the family
            transactionInformation = reader.readNext();
            transactionInformation = reader.readNext();

            // read the first product
            transactionInformation = reader.readNext();

            String currentId = transactionInformation[0];

            ArrayList<String> products = new ArrayList<String>(); // to store products

            while (transactionInformation != null && currentId.equals(transactionInformation[0])) {
                products.add(transactionInformation[1]);

                // read the next product or it can be the next information if the ids aren't
                // same
                transactionInformation = reader.readNext();
                // System.out.println(transactionInformation[1]);
            }

            int id = Integer.parseInt(currentId);
            String[] productsArray = getStringArray(products);
            writer.writeLine(id, productsArray);
        }
    }

    public static String[] getStringArray(ArrayList<String> arr) {

        // Convert ArrayList to object array
        Object[] objArr = arr.toArray();

        // convert Object array to String array
        String[] str = Arrays.copyOf(objArr, objArr.length, String[].class);

        return str;
    }
}