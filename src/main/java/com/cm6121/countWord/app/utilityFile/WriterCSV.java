package com.cm6121.countWord.app.utilityFile;


import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Method that will create a directory in your root folder and return the directory as a String.
 * The CSV files that you are saving are expected to be in this path.
 */

public class WriterCSV {

    public String makeDir() {
        String fileP = System.getProperty("user.home") + "/StudentCSVSaved/";
        File direc = new File(fileP);
        if (!direc.exists()) {
            direc.mkdirs();
        }
        return fileP;
    }

    public void wipeDir() {
        File fileP = new File(System.getProperty("user.home") + "/StudentCSVSaved/");
        File listF[] = fileP.listFiles();
        for (File file : listF) {
            if (file.isFile()) {
                file.delete();
            }
        }
    }

    public void make(String stackt, String dirAlt) throws IOException {
        File dirAltList = new File(System.getProperty("user.home") + dirAlt);
        String content[] = dirAltList.list();
        for (int i = 0; i < content.length; i++) {
            int x = 0;
            String[] docname = content[i].split("\\.");
            File file = new File(stackt + docname[x] + "_allWords.csv");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void writeSingle(String stacktSave, String directoryPoint) {
        File otherDirectoryL = new File(System.getProperty("user.home") + directoryPoint);
        String content[] = otherDirectoryL.list();
        FileReader rf = new FileReader();
        GenMethods hms = new GenMethods();
        String bodyText = null;
        String[] pointer = null;
        CSVWriter csvw;
        File otherDirectoryL2 = new File(stacktSave);
        String allWordsList[] = otherDirectoryL2.list();
        try {
            for (int i = 0; i < allWordsList.length; i++) {
                csvw = new CSVWriter(new FileWriter(stacktSave + allWordsList[i]));
                List<String> file = rf.readCSVMethod2(directoryPoint + "/" + content[i]);
                Fetchinformation info = hms.fetchInfo(file);
                String line1[] = {info.fetchTitle(), info.fetchDate()};
                csvw.writeNext(line1);
                csvw.flush();
                for (String j : file) {
                    pointer = j.split(",");
                }
                for (int k = 1; k < pointer.length - 1; k++) {
                    bodyText = bodyText + pointer[k];
                }
                String textUnpunc = hms.unp(bodyText);
                HashMap<String, Integer> words = hms.fetchAmount(textUnpunc);
                HashMap<String, Integer> wordsOrg = hms.increasingOrder(words);
                for (Map.Entry<String, Integer> entry : wordsOrg.entrySet()) {
                    String[] line2 = {entry.getKey(), entry.getValue().toString()};
                    csvw.writeNext(line2);
                    csvw.flush();
                }
                bodyText = "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void maketotalWords(String stackt) {
        File allWords = new File(stackt + "CSVAllDocuments_allWords.csv");
        if (!allWords.exists()) {
            try {
                allWords.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeTotalWords(String stackt, String directoryPoint) {
        File otherDirectoryL = new File(System.getProperty("user.home") + directoryPoint);
        String content[] = otherDirectoryL.list();
        FileReader rf = new FileReader();
        String pointer2[] = null;
        String bodyText = null;
        GenMethods gms = new GenMethods();
        File allWords = new File(stackt + "CSVAllDocuments_allWords.csv");
        try {
            CSVWriter write = new CSVWriter(new FileWriter(allWords));
            for (int i = 0; i < content.length; i++) {
                List<String> file = rf.readCSVMethod2(directoryPoint + "/" + content[i]);
                for (String j : file) {
                    pointer2 = j.split(",");
                }
                pointer2[0] = "";
                pointer2[pointer2.length -1] = "";
                for (int k = 0; k < pointer2.length; k++) {
                    bodyText = bodyText + pointer2[k];
                }
                pointer2 = null;
            }
            String textUnp = gms.unp(bodyText);
            HashMap<String, Integer> words = gms.fetchAmount(textUnp);
            HashMap<String, Integer> wordsOrg = gms.increasingOrder(words);
            List Backwards = gms.decreasingOrder(wordsOrg);
            String[] split;
            String temporary = Backwards.toString();
            if (Backwards.size() > 0) {
                temporary = temporary.replaceAll(",", "=");
                temporary = temporary.replaceAll("\\[", " ").trim();
                temporary = temporary.replaceAll("]", " ").trim();
                temporary = temporary.replaceAll("\\s{3}", " ");
                temporary = temporary.replaceAll("\\s{2}", " ");
                temporary = temporary.toLowerCase();
                split = temporary.split("=");

                for (int i = 0; i < split.length - 1; i = i + 2) {
                    split[i] = split[i].trim();
                    String[] line3 = {split[i], split[i + 1]};
                    write.writeNext(line3);
                    write.flush();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}