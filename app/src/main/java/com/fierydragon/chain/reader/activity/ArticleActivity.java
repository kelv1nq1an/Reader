package com.fierydragon.chain.reader.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fierydragon.chain.reader.App;
import com.fierydragon.chain.reader.R;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Copyright KelvinQian
 */
public class ArticleActivity extends AppCompatActivity {
    private static final String TAG = "ArticleActivity";
    private Context mContext;
    private Toolbar toolbarArticleActivity;
    private TextView articleCategoryTV;
    private TextView articleNameTV;
    private TextView articleContentTV;
    private LinearLayout slidebarLayout;
    private TextView articleWordsSeekBarText;
    private DiscreteSeekBar articleWordsSeekBar;

    private String articleCategory;
    private String articleName;
    private String articleContent;
    private int wordLevel;

    private SpannableString spannableString;

    private App app;
    private String[][] words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artical);

        mContext = this;
        wordLevel = 0;
        getIntentData();
        initToolbar();
        initArticle();
        slidebarLayout = (LinearLayout) findViewById(R.id.slidebar_articleactvity);
        slidebarLayout.setVisibility(View.GONE);
        articleWordsSeekBarText = (TextView) findViewById(R.id.slidebar_text_articleactivity);
        articleWordsSeekBarText.setText(getString(R.string.slidebar_text) + 0);
        articleWordsSeekBar = (DiscreteSeekBar) findViewById(R.id.seekbar_articleactivity);
        articleWordsSeekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar discreteSeekBar, int i, boolean b) {
                wordLevel = i;
                articleWordsSeekBarText.setText(getString(R.string.slidebar_text) + wordLevel);
                switch (i) {
                    case 0:
                        highLightWords(0);
                        break;
                    case 1:
                        highLightWords(1);
                        break;
                    case 2:
                        highLightWords(2);
                        break;
                    case 3:
                        highLightWords(3);
                        break;
                    case 4:
                        highLightWords(4);
                        break;
                    case 5:
                        highLightWords(5);
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar discreteSeekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar discreteSeekBar) {

            }
        });

        app = (App) getApplication();
        words = app.getWords();

    }

    private void highLightWords(int level) {
        int number = words[level].length;
        spannableString = new SpannableString(articleContent);
        String word = "";
        for (int i = 0; i < number; i++) {
            int wordStartIndex = 0;
            int wordEndIndex = 0;
            char preChar;
            char nextChar;
            word = words[level][i];
            while (wordStartIndex != -1) {
                wordStartIndex = articleContent.indexOf(word, wordEndIndex);
                if (wordStartIndex != -1) {
                    wordEndIndex = wordStartIndex + word.length();
                    preChar = articleContent.charAt(wordStartIndex - 1);
                    nextChar = articleContent.charAt(wordEndIndex);
                    if (!(Character.isLetter(preChar) || Character.isLetter(nextChar))) {
                        spannableString.setSpan(new BackgroundColorSpan(Color.YELLOW), wordStartIndex, wordEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                        WordClickableSpan wordClickableSpan = new WordClickableSpan(mContext, word);
                        spannableString.setSpan(wordClickableSpan, wordStartIndex, wordEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
            }

            Log.i(TAG, "level = " + level + " word = " + word + " start = " + wordStartIndex + " end = " + wordEndIndex);
        }

        articleContentTV.setText(spannableString);
        articleContentTV.setMovementMethod(LinkMovementMethod.getInstance());
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.article_highlight) {
            if (slidebarLayout.getVisibility() == View.VISIBLE) {
                slidebarLayout.setVisibility(View.GONE);
                articleContentTV.setText(articleContent);
            } else {
                slidebarLayout.setVisibility(View.VISIBLE);
                highLightWords(wordLevel);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

class WordClickableSpan extends ClickableSpan {

    String wordClicked;
    Context mContext;

    public WordClickableSpan(Context context, String text) {
        super();
        this.mContext = context;
        wordClicked = text;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(mContext, wordClicked, Toast.LENGTH_SHORT).show();
    }
}