package com.fierydragon.chain.reader.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.fierydragon.chain.reader.App;
import com.fierydragon.chain.reader.R;
import com.fierydragon.chain.reader.adapter.RecyclerViewMainActivityAdapter;
import com.fierydragon.chain.reader.data.ArticleData;
import com.fierydragon.chain.reader.data.WordData;
import com.fierydragon.chain.reader.util.ArticleUtils;
import com.fierydragon.chain.reader.util.FileUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright KelvinQian
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context mContext;
    private App app;
    private Toolbar toolbarMainActivity;
    private RecyclerView recyclerViewMainActivity;

    private ArticleData articleData;
    private WordData wordData;
    private RecyclerViewMainActivityAdapter mainActivityAdapter;

    private TextView articletesttext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        initToolbar();

        String data = "";
        try {
            data = FileUtils.readFileFromAssets(mContext, "articles.json");

        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson articleGson = new Gson();
        articleData = articleGson.fromJson(data, ArticleData.class);

        try {
            data = FileUtils.readFileFromAssets(mContext, "words.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson wordsGson = new Gson();
        wordData = wordsGson.fromJson(data, WordData.class);

        app = (App) getApplication();
        app.setArticleData(articleData);
        app.setWordData(wordData);

        initRecyclerView();
        /*articletesttext = (TextView) findViewById(R.id.articletext);

        ArticleUtils articleUtils = new ArticleUtils();
        String articleContent = "";
        try {
            articleContent = FileUtils.readFileFromAssets(mContext, getString(R.string.article_file_name));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!articleContent.equals("")) {
            articleDataList = articleUtils.divideArticle(mContext, articleContent);
        } else {
            articleDataList = new ArrayList<>();
        }

        articletesttext.setText(articleUtils.getText());
*/


        //WordsUtils wordsUtils = new WordsUtils();
        /*String wordsContent = "";
        try {
            wordsContent = FileUtils.readFileFromAssets(mContext, getString(R.string.words_file_name));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!wordsContent.equals("")) {
            //words = wordsUtils.loadWords(wordsContent);
            app = (App) getApplication();
            app.setWords(words);
        }*/

    }

    private void initToolbar() {
        toolbarMainActivity = (Toolbar) findViewById(R.id.toolbar);
        toolbarMainActivity.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbarMainActivity);
    }

    private void initRecyclerView() {
        recyclerViewMainActivity = (RecyclerView) findViewById(R.id.recyclerview_activity_main);
        recyclerViewMainActivity.setLayoutManager(new LinearLayoutManager(mContext));
        mainActivityAdapter = new RecyclerViewMainActivityAdapter(mContext, articleData);
        recyclerViewMainActivity.setAdapter(mainActivityAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
