package com.fierydragon.chain.reader.util;

import android.content.Context;

import com.fierydragon.chain.reader.data.ArticleData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright KelvinQian
 */
public class ArticleUtils {
    private static final String TAG = "ArticleUtils";

    public List<ArticleData> articleDataList;
    public String articleCategory;
    public String articleName;
    public String articleContent;
    public String articleWords;
    public String articleTranslation;
    public int categoryIndex;
    public int categoryEndIndex;
    public int nextCategoryIndex;
    public int nameStartIndex;
    public int nameEndIndex;
    public int contentIndex;
    public int wordsStartIndex;
    public int translationStartIndex;

    public String logText = "";
    public String words;
    public int singleWordStartIndex = 0;
    public int singleWordEndIndex = 0;


    public ArticleData mArticleData;

    public ArticleUtils() {
        categoryIndex = 0;
        categoryEndIndex = 0;
        nextCategoryIndex = 0;
        nameStartIndex = 0;
        nameEndIndex = 0;
        contentIndex = 0;
        wordsStartIndex = 0;
        translationStartIndex = 0;
    }


    public String getText() {
        return logText;
    }



    public List<ArticleData> divideArticle(Context context, String fileContent) {
        articleDataList = new ArrayList<>();

        logText = "";
        words = "";
        categoryIndex = fileContent.indexOf("Lesson");
        while (categoryIndex != fileContent.length()) {
            words = "";
            categoryEndIndex = fileContent.indexOf("\n", categoryIndex);
            if (fileContent.indexOf("Lesson", categoryIndex + 1) != -1) {
                nextCategoryIndex = fileContent.indexOf("Lesson", categoryIndex + 1);
            } else {
                nextCategoryIndex = fileContent.length();
            }
            nameStartIndex = fileContent.indexOf("\n", categoryIndex);
            nameEndIndex = fileContent.indexOf("\n", nameStartIndex + 1);

            contentIndex = fileContent.indexOf("\n", nameEndIndex + 1);
            for (int i = 0; i < 5; i++) {
                contentIndex = fileContent.indexOf("\n", contentIndex + 1);
            }

            wordsStartIndex = fileContent.indexOf("New words and expression", contentIndex);

            translationStartIndex = fileContent.indexOf("参考译文", wordsStartIndex);

            articleCategory = fileContent.substring(categoryIndex, categoryEndIndex).trim();
            articleName = fileContent.substring(nameStartIndex, nameEndIndex).trim();
            articleContent = fileContent.substring(contentIndex, wordsStartIndex).trim();
            wordsStartIndex = fileContent.indexOf("\n", wordsStartIndex);
            articleWords = fileContent.substring(wordsStartIndex, translationStartIndex).trim();
            translationStartIndex = fileContent.indexOf("\n", translationStartIndex);
            articleTranslation = fileContent.substring(translationStartIndex, nextCategoryIndex).trim();
            //articleContent = articleContent.substring(articleContent.lastIndexOf(articleContent.trim()));
            //mArticleData = new ArticleData(articleCategory, articleName, articleContent);
            //articleDataList.add(mArticleData);

            categoryIndex = nextCategoryIndex;

            singleWordStartIndex = 0;
            singleWordEndIndex = 0;
            int flag = 1;
            while (flag == 1) {
                singleWordEndIndex = articleWords.indexOf("\n", singleWordStartIndex);
                words += "{\"word\": \"" + articleWords.substring(singleWordStartIndex, singleWordEndIndex).trim() + "\",";
                singleWordStartIndex = articleWords.indexOf(".", singleWordEndIndex);
                words += "\"category\": \"" + articleWords.substring(articleWords.lastIndexOf("\n", singleWordEndIndex), singleWordStartIndex + 1).trim().replace("\r\n", " ") + "\",";
                if ((singleWordEndIndex = articleWords.indexOf("\n", singleWordStartIndex)) == -1) {
                    flag = 0;
                    words += "\"translation\": \"" + articleWords.substring(singleWordStartIndex + 1, articleWords.length()).trim().replace("\r\n", " ") + "\"}";
                    singleWordStartIndex = singleWordEndIndex;
                } else {
                    words += "\"translation\": \"" + articleWords.substring(singleWordStartIndex + 1, singleWordEndIndex).trim().replace("\r\n", " ") + "\"},";
                    singleWordStartIndex = singleWordEndIndex + 1;
                }
            }

            articleContent = articleContent.replace("\r\n", "\\n").replace("\"", "\\\"");
            articleTranslation = articleTranslation.replace("\r\n", "\\n").replace("\"", "\\\"");
            words = words.replace("\r\n", " ");

            logText += "{\n" +
                    "\"lesson\": \"" + articleCategory
                    + "\",\n\"title\": \"" + articleName
                    + "\",\n\"content\": \"" + articleContent
                    + "\",\n\"words\": [\n" + words + "\n]"
                    + ",\n\"translation\": \"" + articleTranslation
                    + "\"\n" +
                    "},\n";
            //Log.i(TAG, "" + articleCategory + "\n" + articleName + "\n" + articleContent + "\n" + articleWords + "\n" + articleTranslation);
            //Log.i(TAG, logText);
        }
        logText = "{\n\"article\": [\n    " + logText + "\n]\n}";
        //Log.i(TAG, "{\n\"article\": [\n" + text + "\n]\n}");


        File path = context.getExternalFilesDir(null);
        File file = new File(path, "article.txt");
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
            stream.write(logText.getBytes());
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("article.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(logText);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }*/
/*
        File file = new File("article.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
                FileOutputStream stream = new FileOutputStream(file);
                byte[] buf = logText.getBytes();
                stream.write(buf);
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
*/

        return articleDataList;
    }
}
