package com.fierydragon.chain.reader.util;


import java.util.ArrayList;
import java.util.List;

/**
 * Copyright KelvinQian
 */
public class WordsUtils {
    private static final String TAG = "WordsUtils";

    private String[][] words;
    private List<String> wordsList;
    private String word;
    private String wordsLevelText;
    private int wordsLevel;
    private int startIndex;
    private int endIndex;

    private String logtext;

    public WordsUtils() {
        wordsLevel = 0;
        startIndex = 0;
        endIndex = 0;
    }

    public String[][] loadWords(String wordsContent) {
        words = new String[6][];
        wordsList = new ArrayList<String>();

        for (wordsLevel = 0; wordsLevel < 6; wordsLevel++) {
            wordsLevelText = "" + wordsLevel;
            startIndex = 0;
            endIndex = 0;
            wordsList.clear();
            logtext = "";

            while (wordsContent.indexOf(wordsLevelText, startIndex) != -1) {
                endIndex = wordsContent.indexOf(wordsLevelText, startIndex);
                startIndex = wordsContent.substring(0, endIndex).lastIndexOf("\n");
                word = wordsContent.substring(startIndex, endIndex).trim();
                wordsList.add(word);

                logtext += "\"" + word + "\",";

                startIndex = wordsContent.indexOf("\n", endIndex);
                //Log.i(TAG, word);
            }
            words[wordsLevel] = wordsList.toArray(new String[wordsList.size()]);
            //Log.i(TAG, "Level" + wordsLevelText + ": [" + logtext + "]");
        }
        return words;
    }

}
