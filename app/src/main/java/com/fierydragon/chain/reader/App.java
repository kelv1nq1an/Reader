package com.fierydragon.chain.reader;

import android.app.Application;

import com.fierydragon.chain.reader.data.ArticleData;
import com.fierydragon.chain.reader.data.WordData;

/**
 * Copyright KelvinQian
 */
public class App extends Application {
    private static final String TAG = "App";
    private ArticleData articleData;
    private WordData wordData;
    public int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ArticleData getArticleData() {
        return articleData;
    }

    public void setArticleData(ArticleData articleData) {
        this.articleData = articleData;
    }

    public WordData getWordData() {
        return wordData;
    }

    public void setWordData(WordData wordData) {
        this.wordData = wordData;
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }
}
