package com.fierydragon.chain.reader.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.fierydragon.chain.reader.R;

/**
 * Copyright KelvinQian
 */
public class ArticleActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private static final String TAG = "ArticleActivity";
    private Context mContext;
    private Toolbar toolbarArticleActivity;
    private TextView articleCategoryTV;
    private TextView articleNameTV;
    private TextView articleContentTV;
    private SeekBar articleWordsSeekBar;

    private String articleCategory;
    private String articleName;
    private String articleContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artical);

        mContext = this;
        getIntentData();
        initToolbar();
        initArticle();
        articleWordsSeekBar = (SeekBar) findViewById(R.id.seekbar_articleactivity);
        articleWordsSeekBar.setOnSeekBarChangeListener(this);

    }

    private void initToolbar() {
        toolbarArticleActivity = (Toolbar) findViewById(R.id.toolbar);
        toolbarArticleActivity.setTitle("Article");
        setSupportActionBar(toolbarArticleActivity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarArticleActivity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initArticle() {
        articleCategoryTV = (TextView) findViewById(R.id.article_category_articleactivity);
        articleNameTV = (TextView) findViewById(R.id.article_name_articleactivity);
        articleContentTV = (TextView) findViewById(R.id.article_content_articleactivity);

        if (!articleCategory.isEmpty()) {
            articleCategoryTV.setText(articleCategory);
        } else {
            articleCategoryTV.setText(getString(R.string.article_category));
        }

        if (!articleName.isEmpty()) {
            articleNameTV.setText(articleName);
        } else {
            articleNameTV.setText(getString(R.string.article_name));
        }

        if (!articleContent.isEmpty()) {
            articleContentTV.setText(articleContent);
        } else {
            articleContentTV.setText(getString(R.string.article_content));
        }
    }

    private void getIntentData() {
        articleCategory = getIntent().getStringExtra(getString(R.string.article_category));
        articleName = getIntent().getStringExtra(getString(R.string.article_name));
        articleContent = getIntent().getStringExtra(getString(R.string.article_content));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //seekBar = articleWordsSeekBar;
        Log.i(TAG, "seekbar" + seekBar.getProgress());
        int seekProgress = seekBar.getProgress();
        if (seekProgress < 20) {
            seekBar.setProgress(0);
        } else if (seekProgress >= 20 && seekProgress < 40) {
            seekBar.setProgress(20);
        } else if (seekProgress >= 40 && seekProgress < 60) {
            seekBar.setProgress(40);
        } else if (seekProgress >= 60 && seekProgress < 80) {
            seekBar.setProgress(60);
        } else if (seekProgress >= 80 && seekProgress < 100) {
            seekBar.setProgress(80);
        } else if (seekProgress >= 100) {
            seekBar.setProgress(100);
        }
    }
}
