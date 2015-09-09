package com.fierydragon.chain.reader.activity;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.fierydragon.chain.reader.App;
import com.fierydragon.chain.reader.R;
import com.fierydragon.chain.reader.adapter.TabFragmentAdapter;
import com.fierydragon.chain.reader.fragment.ArticleFragment;
import com.fierydragon.chain.reader.fragment.TranslationFragment;
import com.fierydragon.chain.reader.fragment.WordsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright KelvinQian
 */
public class ArticleActivity extends AppCompatActivity {
    private static final String TAG = "ArticleActivity";
    private Context mContext;
    private Toolbar toolbarArticleActivity;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private int clickPosition;
    private int position;
    private App app;


    private TabFragmentAdapter tabFragmentAdapter;
    private List<Fragment> fragmentList;
    private ArticleFragment articleFragment;
    private WordsFragment wordsFragment;
    private TranslationFragment translationFragment;
    private List<String> tabTextList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artical);

        mContext = this;

        getIntentData();
        initToolbar();
        initPagers();

    }

    private void initPagers() {
        fragmentList = new ArrayList<>();

        articleFragment = new ArticleFragment();
        wordsFragment = new WordsFragment();
        translationFragment = new TranslationFragment();

        fragmentList.add(articleFragment);
        fragmentList.add(wordsFragment);
        fragmentList.add(translationFragment);
        tabFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList, getTabTextList());

        viewPager = (ViewPager) findViewById(R.id.viewpager_article_activity);
        viewPager.setAdapter(tabFragmentAdapter);

        tabLayout.setTabsFromPagerAdapter(tabFragmentAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initToolbar() {
        app = (App) getApplication();
        position = app.getPosition();
        toolbarArticleActivity = (Toolbar) findViewById(R.id.toolbar);
        toolbarArticleActivity.setTitle(app.getArticleData().getArticle().get(position).getLesson());
        setSupportActionBar(toolbarArticleActivity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarArticleActivity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getIntentData() {
        clickPosition = getIntent().getIntExtra(mContext.getString(R.string.position), 0);
        App app = (App) getApplication();
        app.setPosition(clickPosition);
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
            articleFragment.doHighlight();
            /*if (articleFragment.slidebarLayout.getVisibility() == View.VISIBLE) {
                slidebarLayout.setVisibility(View.GONE);
                articleContentTV.setText(articleContent);
            } else {
                slidebarLayout.setVisibility(View.VISIBLE);
                highLightWords(wordLevel);
            }*/
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public List<String> getTabTextList() {
        tabLayout = (TabLayout) findViewById(R.id.tab_article_activity);

        tabTextList = new ArrayList<>();
        tabTextList.add("文章");
        tabTextList.add("新词生词");
        tabTextList.add("译文");
        for (int i = 0; i < tabTextList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabTextList.get(i)));
        }
        return tabTextList;
    }
}

