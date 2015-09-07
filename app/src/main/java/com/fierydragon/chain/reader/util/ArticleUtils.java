package com.fierydragon.chain.reader.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.fierydragon.chain.reader.data.ArticleData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    public int categoryIndex;
    public int categoryEndIndex;
    public int nextCategoryIndex;
    public int nameStartIndex;
    public int nameEndIndex;
    public int contentIndex;

    public ArticleData mArticleData;

    public ArticleUtils() {
        categoryIndex = 0;
        categoryEndIndex = 0;
        nextCategoryIndex = 0;
        nameStartIndex = 0;
        nameEndIndex = 0;
        contentIndex = 0;
    }

    public List<ArticleData> divideArticle(Context context, String fileContent) {
        articleDataList = new ArrayList<>();

        categoryIndex = fileContent.indexOf("Lesson");
        while (categoryIndex != fileContent.length()) {
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

            articleCategory = fileContent.substring(categoryIndex, categoryEndIndex).trim();
            articleName = fileContent.substring(nameStartIndex, nameEndIndex).trim();
            articleContent = fileContent.substring(contentIndex, nextCategoryIndex).trim();
            //articleContent = articleContent.substring(articleContent.lastIndexOf(articleContent.trim()));
            mArticleData = new ArticleData(articleCategory, articleName, articleContent);
            articleDataList.add(mArticleData);

            categoryIndex = nextCategoryIndex;

            //Log.i(TAG, "" + articleCategory + "\n" + articleName + "\n" + articleContent);
        }
        return articleDataList;
    }
}
