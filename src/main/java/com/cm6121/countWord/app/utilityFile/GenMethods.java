package com.cm6121.countWord.app.utilityFile;

import java.io.File;
import java.util.*;

public class GenMethods {

    public Fetchinformation fetchInfo(List<String> text) {
        Fetchinformation fetch = null;
        if (text.size() > 0) {
            fetch = new Fetchinformation();
            for (String i : text) {
                String[] pieces = i.split(",");
                fetch.fetchTitle(pieces[0].trim());
                fetch.fetchDate(pieces[pieces.length - 1].trim());
            }
        } else {
            System.out.println("parsing error! D:");
        }
        return fetch;
    }

    public String unp(String text) {
        if (text != null) {
            text = text.replaceAll("\\p{P}", " ");
            text = text.replaceAll("\\s{2}", " ");
            text = text.toLowerCase();
            text = text.replaceAll("true","TRUE");
            text = text.replaceAll("false","FALSE");

        }
        return text;
    }

    public HashMap<String, Integer> fetchAmount(String text) {
        HashMap<String, Integer> wordplusquantity = new HashMap<>();
        if (text != null) {
            String[] wordlist = text.split(" ");
            for (String i : wordlist) {
                if (i.length() > 1) {
                    if (wordplusquantity.containsKey(i)) {
                        wordplusquantity.put(i, wordplusquantity.get(i) + 1);
                    } else {
                        wordplusquantity.put(i, 1);
                    }
                }
            }
        }
        return wordplusquantity;
    }

    public <K, V> void mapDisp(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println("The word " + entry.getKey() + " has been used " + entry.getValue() + " times");
        }
    }

    public int specCounter(String text, String word) {
        int x = 0;
        String[] wordList = text.split(" ");
        for (String i : wordList) {
            if (i.equals(word)) {
                x++;
            }
        }
        return x;
    }

    public HashMap<String, Integer> increasingOrder(HashMap<String, Integer> hash) {
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(hash.entrySet());

        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));
        HashMap<String, Integer> mapsort = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> i : list) {
            mapsort.put(i.getKey(), i.getValue());
        }
        return mapsort;
    }


    public List decreasingOrder(HashMap<String, Integer> hash) {
        increasingOrder(hash);
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(hash.entrySet());
        Collections.reverse(list);
        return list;

    }



    public void display20List(List<String> twentyList) {
        String[] split;
        String temporary = twentyList.toString();
        if (twentyList.size() > 0) {
            temporary = temporary.replaceAll(",", "=");
            temporary = temporary.replaceAll("\\[", " ").trim();
            temporary = temporary.replaceAll("]", " ").trim();
            temporary = temporary.replaceAll("\\s{3}", " ");
            temporary = temporary.replaceAll("\\s{2}", " ");
            temporary = temporary.toLowerCase();
            split = temporary.split("=");
            int x = 0;
            for (int i = 0; i < split.length - 1; i = i + 2) {
                unp(split[i]);
                unp(split[i + 1]);
                if (x == 0) {
                    System.out.println("The word " + split[i] + " appeared " + split[i + 1] + " times");
                } else {
                    System.out.println("The word" + split[i] + " appeared " + split[i + 1] + " times");
                }
                x++;
                if (x > 19) {
                    break;
                }

            }
        }
    }

    public boolean ask(boolean co) {
        String answerInput;
        do {
            System.out.println("Would you like to select another option? Y/N");
            Scanner input = new Scanner(System.in);
            answerInput = input.nextLine();
            if (answerInput.equals("Y") | answerInput.equals("y")) {
                co = true;
            } else if (answerInput.equals("N") | answerInput.equals("n")) {
                co = false;
            }
        } while (!answerInput.equals("Y") && !answerInput.equals("y") && !answerInput.equals("N") && !answerInput.equals("n"));
        return co;
    }

    public void rename(String stackt, String directoryPoint) {
        FileReader rf = new FileReader();
        GenMethods gms = new GenMethods();
        File otherDirectoryL2 = new File(stackt);
        String fullwordL[] = otherDirectoryL2.list();
        File otherDirectoryL = new File(System.getProperty("user.home") + directoryPoint);
        String content[] = otherDirectoryL.list();
        for (int i = 0; i < fullwordL.length; i++) {
            File file = new File(stackt + fullwordL[i]);
            List<String> quefile = rf.readCSVMethod2(directoryPoint + "/" + content[i]);
            Fetchinformation fetch = gms.fetchInfo(quefile);
            String Titlename = fetch.fetchTitle();
            Titlename = Titlename.replaceAll(" ","_");
            File re = new File(stackt + Titlename + "_allWords.csv");
            boolean r = file.renameTo(re);
            while(r == false){
                new File(stackt + fullwordL[i]);
                quefile = rf.readCSVMethod2(directoryPoint + "/" + content[i]);
                fetch = gms.fetchInfo(quefile);
                Titlename = fetch.fetchTitle();
                Titlename = Titlename.replaceAll(" ","_");
                re = new File(stackt + Titlename + "_allWords.csv");
                r = file.renameTo(re);
            }
        }
    }
}

