package com.cm6121.countWord.app.utilityFile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;

public class FileReader {
    /**
     * Method that will return a List of Array of String that will contains the CSV content.
     * The method skip the first line of the CSV
     * You can implement another method, or use this one to read a CSV. This method is using OpenCSV
     * @param file, the CSV file
     * @return a List of String array
     */
    public List<String[]> readCSVMethod1 (File file) {
        List<String[]> strings = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(
                    new InputStreamReader(new FileInputStream(file), "UTF-8"));
            {
                reader.skip(1);
                strings = reader.readAll();
            }
            System.out.println(strings);
        } catch (IOException | CsvException e) {
            System.out.println(e.getStackTrace());
        }
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


    public List<String> readCSVMethod2(String... pathDocument) {
        StringBuffer strb = new StringBuffer();
        for (String i : pathDocument) {
            strb.append(i);
            strb.append("/");
        }
        Path p = Paths.get(System.getProperty("user.home"), strb.toString());
        List<String> string = null;
        try {
            string = Files.readAllLines(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }
}
