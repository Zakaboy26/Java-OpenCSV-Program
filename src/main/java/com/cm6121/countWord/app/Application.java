package com.cm6121.countWord.app;

import com.cm6121.countWord.app.utilityFile.FileReader;
import com.cm6121.countWord.app.utilityFile.WriterCSV;
import com.cm6121.countWord.app.utilityFile.Fetchinformation;
import com.cm6121.countWord.app.utilityFile.GenMethods;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        String readDocs = ClassLoader.getSystemClassLoader().getResource("FolderDocumentsToRead").getPath();
        System.out.println("Hi! welcome to the counting words application\n");


        FileReader rf = new FileReader();
        Scanner input = new Scanner(System.in);
        GenMethods hms = new GenMethods();
        WriterCSV wcsv = new WriterCSV();
        boolean CO = true;
        int result;
        String bodyText = null;
        String[] pointer = null;
        String directoryPoint = "";
        String[] directoryWords = readDocs.split("/");
        for (int x = 4; x < directoryWords.length; x++) {
            directoryPoint = directoryPoint + "/" + directoryWords[x];
        }
        directoryPoint = directoryPoint + "/";
        directoryPoint = directoryPoint.replaceAll("%20", " ");
        wcsv.wipeDir();
        String stacktr = wcsv.makeDir();
        try {
            wcsv.make(stacktr, directoryPoint);
        } catch (IOException e) {
            e.printStackTrace();
        }
        wcsv.writeSingle(stacktr, directoryPoint);
        hms.rename(stacktr, directoryPoint);
        wcsv.maketotalWords(stacktr);
        wcsv.writeTotalWords(stacktr, directoryPoint);
        do {
            result = 0;
            do {
                try {
                    System.out.println("Let's get started!\n" +
                            "1.) Shows the number of files in the folder + the names and the publishing dates of the documents\n" +
                            "2.) Shows the number of occurrences of all the words inside each document\n" +
                            "3.) Shows the number of occurrences of a specific word inside each document\n" +
                            "4.) Shows the top 20 words with the most occurrences");
                    String menulist = input.nextLine();
                    int menulistNo = Integer.valueOf(menulist);
                    if (menulistNo < 1 | menulistNo > 4) {
                        System.out.println("Please enter a number between 1 and 4 :)");
                    } else {
                        result = menulistNo;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a number between 1 and 4 :)");
                }
            } while (result == 0);

            //Given the user selects 1, this option will;
            // let the user read the number of documents and the names
            if (result == 1) {
                File Pathdir = new File(System.getProperty("user.home") + directoryPoint);
                String content[] = Pathdir.list();
                System.out.println("There are " + content.length + " files in this directory :O");
                for (int i = 0; i < content.length; i++) {
                    List<String> file = rf.readCSVMethod2(directoryPoint + "/" + content[i]);
                    Fetchinformation fetch = hms.fetchInfo(file);
                    System.out.println("Document name: " + content[i] + ", The title: " + fetch.fetchTitle() + ", And the make: " + fetch.fetchDate());
                }
                CO = hms.ask(CO);
            }



            //Given the user selects 2, this option will;
            // let the user read the number of occurrences of all words for each document
            if (result == 2) {
                File Pathdir = new File(System.getProperty("user.home") + directoryPoint);
                String content[] = Pathdir.list();
                for (int i = 0; i < content.length; i++) {
                    List<String> file = rf.readCSVMethod2(directoryPoint + "/" + content[i]);
                    for (String j : file) {
                        pointer = j.split(",");
                    }
                    for (int k = 1; k < pointer.length - 1; k++) {
                        bodyText = bodyText + pointer[k];
                    }
                    String textUnp = hms.unp(bodyText);
                    HashMap<String, Integer> words = hms.fetchAmount(textUnp);
                    HashMap<String, Integer> wordsOrg = hms.increasingOrder(words);
                    System.out.println("In " + content[i] + ":");
                    hms.mapDisp(wordsOrg);
                    bodyText = "";
                }
                CO = hms.ask(CO);
            }



            //Given the user selects 3, this option will;
            // let the user read the number of instances a specific word occurrs
            if (result == 3) { //display the number of occurrences of a specified word in each document
                int compl = 0;
                File directoryPath = new File(System.getProperty("user.home") + directoryPoint);
                String content[] = directoryPath.list();
                System.out.println("Please enter the specific word you want the number of instances to");
                String instanceWord = input.nextLine();
                for (int i = 0; i < content.length; i++) {
                    List<String> file = rf.readCSVMethod2(directoryPoint + "/" + content[i]);
                    for (String j : file) {
                        String pointer2[];
                        pointer2 = j.split(",");
                        pointer2[0] = "";
                        pointer2[pointer2.length - 1] = "";
                        pointer = pointer2;

                    }
                    for (int k = 0; k < pointer.length; k++) {
                        bodyText = bodyText + pointer[k];
                    }
                    String textUnp = hms.unp(bodyText);
                    int answer = hms.specCounter(textUnp, instanceWord);
                    compl = compl + answer;
                    System.out.println("The number of instances the word '" + instanceWord + "' pops up in the file: " +
                            content[i] + " = " + answer);
                    bodyText = "";
                }
                System.out.println("The number of instances the word '" + instanceWord + "' pops up in the whole corpus= " + compl);
                CO = hms.ask(CO);
            }



            //Given the user selects 4, this option will;
            // let the user read the top 20 most used words
            if (result == 4) {
                File pathDir = new File(System.getProperty("user.home") + directoryPoint);
                String contents[] = pathDir.list();
                for (int i = 0; i < contents.length; i++) {
                    List<String> file = rf.readCSVMethod2(directoryPoint + "/" + contents[i]);
                    for (String j : file) {
                        String pieces2[];
                        pieces2 = j.split(",");
                        pieces2[0] = "";
                        pieces2[pieces2.length - 1] = "";
                        pointer = pieces2;
                        for (int k = 0; k < pointer.length; k++) {
                            bodyText = bodyText + pointer[k];

                        }
                    }
                }
                            //When the top 20 most used words are displayed to the user
                            String textUnp = hms.unp(bodyText);
                            HashMap<String, Integer> words = hms.fetchAmount(textUnp);
                            HashMap<String, Integer> wordsOrg = hms.increasingOrder(words);
                            List Backwards = hms.decreasingOrder(wordsOrg);
                            System.out.println("The top 20 words with most occurrences");
                            hms.display20List(Backwards);
                            CO = hms.ask(CO);
                            bodyText = "";
            }

        } while (CO == true);
    }
}