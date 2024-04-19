package com.cm6121.countWord.toTest;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test is to evaluate the CSV that the application should have created by files.
 * This test shouldn't be changed.
 */
class CSVTestWords {
    private final String pathForFile = "/StudentCSVSaved/";
    private final String DIDDLING = "DIDDLING_allWords.csv";
    private final String ELEONORA = "ELEONORA_allWords.csv";
    private final String ATALE = "A_TALE_OF_THE_RAGGED_MOUNTAINS_allWords.csv";
    private final String APREDICAMENT = "A_PREDICAMENT_allWords.csv";
    private final String HOPFROG = "HOP-FROG_allWords.csv";

    private final String ALLWORDSCSV = "CSVAllDocuments_allWords.csv";


    @Test
    void testThatReadingMethodThrowsError () {
        final String documentToRead = System.getProperty("user.home") + pathForFile + "DIDDL_allWords.csv";
        assertThrows(IOException.class, () -> {
            UtilityReaderTest.readCSVMethod(new File(documentToRead));
        });
    }

    @Test
    void testThatNumberOfFilesIsCorrect () throws IOException, CsvException {
        final String documentToRead = System.getProperty("user.home") + pathForFile;
        File[] resourceFolderFiles = UtilityReaderTest.getResourceFolderFiles(documentToRead);
        assertEquals(resourceFolderFiles.length,6);
    }

    @Test
    void testThatReadingFilesExistsWithCorrectname () throws IOException, CsvException {
        final String documentToRead = System.getProperty("user.home") + pathForFile;
        File[] resourceFolderFiles = UtilityReaderTest.getResourceFolderFiles(documentToRead);
        for (File f : resourceFolderFiles) {
            assertTrue(f.toPath().toString().contains("allWords"));
        }
    }

    @Test
    void testThatReadingFilesExistsWithCorrectNumber () throws IOException, CsvException {
        final String documentToRead = System.getProperty("user.home") + pathForFile;
        File[] resourceFolderFiles = UtilityReaderTest.getResourceFolderFiles(documentToRead);
        assertTrue(resourceFolderFiles.length > 1);
    }

    @Test
    void readCSVMethodFirstValueForDiddling () throws IOException, CsvException {
        final String documentToRead = System.getProperty("user.home") + pathForFile + DIDDLING;
        final List<String[]> strings = UtilityReaderTest.readCSVMethod(new File(documentToRead));
        String[] strings1 = strings.get(0);
        assertEquals("DIDDLING", strings1[0]);
        assertEquals("October 1843", strings1[1]);
    }

    @Test
    void readCSVMethodValueLastValueForDiddling () throws IOException, CsvException {
        final String documentToRead = System.getProperty("user.home") + pathForFile + DIDDLING;
        final List<String[]> strings = UtilityReaderTest.readCSVMethod(new File(documentToRead));
        String[] strings1 = strings.get(strings.size() - 1);
        assertEquals("the", strings1[0]);
        assertEquals("250", strings1[1]);
    }

    @Test
    void readCSVMethodForTheStringDemenor () throws IOException, CsvException {
        final String documentToRead = System.getProperty("user.home") + pathForFile + DIDDLING;
        final List<String[]> strings = UtilityReaderTest.readCSVMethod(new File(documentToRead));
        for (String[] word : strings) {
            if (word[0].equals("demeanor")) {
                assertEquals("1", word[1]);
            }
        }
    }


    @Test
    void readCSVTestSizeOfWords () throws IOException, CsvException {
        final String documentToRead = System.getProperty("user.home") + pathForFile + DIDDLING;
        final List<String[]> strings = UtilityReaderTest.readCSVMethod(new File(documentToRead));
        for (String[] s : strings) {
            assertTrue(s[0].length() > 1);
        }
    }

    @Test
    void readCSVTestIfPunctuationLeft () throws IOException, CsvException {
        final String documentToRead = System.getProperty("user.home") + pathForFile + DIDDLING;
        final List<String[]> strings = UtilityReaderTest.readCSVMethod(new File(documentToRead));
        for (String[] s : strings) {
            Pattern pattern = Pattern.compile("\\W");
            Matcher matcher = pattern.matcher(s[0]);
            assertFalse(matcher.find());
        }
    }

    @Test
    void readCSVMethodFirstValueForEleonora_words () throws IOException, CsvException {
        final String documentToRead = System.getProperty("user.home") + pathForFile + ELEONORA;
        final List<String[]> strings = UtilityReaderTest.readCSVMethod(new File(documentToRead));
        String[] strings1 = strings.get(0);
        assertEquals("ELEONORA", strings1[0]);
        assertEquals("April 1841", strings1[1]);
    }

    @Test
    void readCSVMethodValueLastValueForEleonora_words () throws IOException, CsvException {
        final String documentToRead = System.getProperty("user.home") + pathForFile + ELEONORA;
        final List<String[]> strings = UtilityReaderTest.readCSVMethod(new File(documentToRead));
        String[] strings2 = strings.get(strings.size() - 1);
        assertEquals("the", strings2[0]);
        assertEquals("217", strings2[1]);
    }

    @Test
    void readCSVMethodForEleonora_words_wordMountains () throws IOException, CsvException {
        final String documentToRead = System.getProperty("user.home") + pathForFile + ELEONORA;
        final List<String[]> strings = UtilityReaderTest.readCSVMethod(new File(documentToRead));
        for (String[] word : strings) {
            if (word[0].equals("mountains")) {
                assertEquals("4", word[1]);
            }
        }

    }

    //the value of the test could change
    @Test
    void readCSVATALE_words () throws IOException, CsvException {
        final String documentToRead = System.getProperty("user.home") + pathForFile + ATALE;
        final List<String[]> strings = UtilityReaderTest.readCSVMethod(new File(documentToRead));
        String[] strings1 = strings.get(0);
        assertEquals("A TALE OF THE RAGGED MOUNTAINS", strings1[0]);
        assertEquals("April 1844", strings1[1]);
        for (String[] word : strings) {
            if (word[0].equals("the")) {
                assertEquals("256", word[1]);
            } else if (word[0].equals("about")) {
                assertEquals("13", word[1]);
            } else if (word[0].equals("character")) {
                assertEquals("4", word[1]);
            }

        }
    }

    //the value of the test could change
    @Test
    void readPredicament_words () throws IOException, CsvException {
        final String documentToRead = System.getProperty("user.home") + pathForFile + APREDICAMENT;
        final List<String[]> strings = UtilityReaderTest.readCSVMethod(new File(documentToRead));
        String[] strings1 = strings.get(0);
        assertEquals("A PREDICAMENT", strings1[0]);
        assertEquals("November 1838", strings1[1]);
        for (String[] word : strings) {
            if (word[0].equals("the")) {
                assertEquals("242", word[1]);
            } else if (word[0].equals("pompey")) {
                assertEquals("24", word[1]);
            } else if (word[0].equals("stretched")) {
                assertEquals("1", word[1]);
            }

        }
    }

    @Test
    void readHOPFROG_words () throws IOException, CsvException {
        final String documentToRead = System.getProperty("user.home") + pathForFile + HOPFROG;
        final List<String[]> strings = UtilityReaderTest.readCSVMethod(new File(documentToRead));
        String[] strings1 = strings.get(0);
        assertEquals("HOP-FROG", strings1[0]);
        assertEquals("March 1849", strings1[1]);
        for (String[] word : strings) {
            if (word[0].equals("dwarf")) {
                assertEquals("19", word[1]);
            } else if (word[0].equals("fifty")) {
                assertEquals("1", word[1]);
            } else if (word[0].equals("the")) {
                assertEquals("302", word[1]);
            } else if (word[0].equals("king")) {
                assertEquals("37", word[1]);
            }

        }
    }

    @Test
    void readCSVAllWordsFirst () throws IOException, CsvException {
        final String documentToRead = System.getProperty("user.home") + pathForFile + ALLWORDSCSV;
        final List<String[]> strings = UtilityReaderTest.readCSVMethod(new File(documentToRead));
        for (String[] word : strings) {
            if (word[0].equals("dwarf")) {
                assertEquals("19", word[1]);
            } else if (word[0].equals("large")) {
                assertEquals("18", word[1]);
            } else if (word[0].equals("the")) {
                assertEquals("1267", word[1]);
            } else if (word[0].equals("little")) {
                assertEquals("21", word[1]);
            } else if (word[0].equals("which")) {
                assertEquals("122", word[1]);
            } else if (word[0].equals("king")) {
                assertEquals("38", word[1]);
            }else if(word[0].equals("bouche")){
                assertEquals("1", word[1]);
            }
        }
    }

}