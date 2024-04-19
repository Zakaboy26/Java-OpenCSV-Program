package com.cm6121.countWord.toTest;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class UtilityReaderTest {
    /**
     * This method will return an List of String array that contains the content of a CSV using CSVReader
     * This method is used in several tests do not change it.
     * @param file
     * @return
     * @throws IOException
     * @throws CsvException
     */
    public static List<String[]> readCSVMethod (File file) throws IOException, CsvException {
        List<String[]> strings ;
        CSVReader reader = new CSVReader((new FileReader(file)));
        strings = reader.readAll();
        return strings;
    }

    /**
     * This method is used in several tests to return a list of files that is contained in a folder
     * This method should not be changed.
     * @param folder String that represent the path of the folder that we want the list of files
     * @return a array of files
     */
    public static File[] getResourceFolderFiles (String folder) {
        return new File(folder).listFiles();
    }
}
