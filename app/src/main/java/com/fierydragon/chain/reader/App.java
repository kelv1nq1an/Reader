package com.fierydragon.chain.reader;

import android.app.Application;


/**
 * Copyright KelvinQian
 */
public class App extends Application {
    private static final String TAG = "App";

    public String[][] getWords() {
        return words;
    }

    public void setWords(String[][] words) {
        this.words = words;
    }

    private String[][] words;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
