package com.fierydragon.chain.reader.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fierydragon.chain.reader.App;
import com.fierydragon.chain.reader.R;
import com.fierydragon.chain.reader.data.ArticleData;
import com.fierydragon.chain.reader.data.WordData;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Copyright KelvinQian
 */
public class ArticleFragment extends Fragment {
    private static final String TAG = "ArticleFragment";
    private View view;
    private TextView articleCategoryTV;
    private TextView articleNameTV;
    private TextView articleContentTV;
    private LinearLayout slidebarLayout;
    private TextView articleWordsSeekBarText;
    private DiscreteSeekBar articleWordsSeekBar;

    private int wordLevel;
    private int position;

    private SpannableString spannableString;

    private App app;
    private WordData wordData;
    private ArticleData.ArticleEntity articleEntity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_article, container, false);

        app = (App) getActivity().getApplication();
        position = app.getPosition();
        articleEntity = app.getArticleData().getArticle().get(position);
        wordData = app.getWordData();

        initArticle();
        initSlideBar();

        return view;
    }

    private void initSlideBar() {
        wordLevel = 0;

        slidebarLayout = (LinearLayout) view.findViewById(R.id.slidebar_layout_article_fragment);
        slidebarLayout.setVisibility(View.GONE);
        articleWordsSeekBarText = (TextView) view.findViewById(R.id.slidebar_text_article_fragment);
        articleWordsSeekBarText.setText(getString(R.string.slidebar_text) + 0);
        articleWordsSeekBar = (DiscreteSeekBar) view.findViewById(R.id.seekbar_article_fragment);

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
    }

    private void highLightWords(int level) {
        int number = wordData.getLevel(level).size();

        spannableString = new SpannableString(articleEntity.getContent());
        String word = "";
        for (int i = 0; i < number; i++) {
            int wordStartIndex = 0;
            int wordEndIndex = 0;
            char preChar = ' ';
            char nextChar = ' ';
            word = wordData.getLevel(level).get(i);
            while (wordStartIndex != -1) {
                wordStartIndex = articleEntity.getContent().indexOf(word, wordEndIndex);
                if (wordStartIndex != -1) {
                    wordEndIndex = wordStartIndex + word.length();

                    if (wordStartIndex != 0) {
                        preChar = articleEntity.getContent().charAt(wordStartIndex - 1);
                    }

                    if (wordEndIndex != articleEntity.getContent().length()) {
                        nextChar = articleEntity.getContent().charAt(wordEndIndex);
                    }

                    if (!(Character.isLetter(preChar) || Character.isLetter(nextChar))) {
                        spannableString.setSpan(new BackgroundColorSpan(Color.YELLOW), wordStartIndex, wordEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        WordClickableSpan wordClickableSpan = new WordClickableSpan(getActivity(), word);
                        spannableString.setSpan(wordClickableSpan, wordStartIndex, wordEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }

                }
            }

            Log.i(TAG, "level = " + level + " word = " + word + " start = " + wordStartIndex + " end = " + wordEndIndex);
        }

        articleContentTV.setText(spannableString);
        articleContentTV.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initArticle() {
        articleCategoryTV = (TextView) view.findViewById(R.id.article_category_fragment_article);
        articleNameTV = (TextView) view.findViewById(R.id.article_name_fragment_article);
        articleContentTV = (TextView) view.findViewById(R.id.article_content_article_fragment);

        articleCategoryTV.setText(articleEntity.getLesson());
        articleNameTV.setText(articleEntity.getTitle());
        articleContentTV.setText(articleEntity.getContent());

        /*
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
        }*/
    }


    public void doHighlight() {
        if (slidebarLayout.getVisibility() == View.VISIBLE) {
            slidebarLayout.setVisibility(View.GONE);
            articleContentTV.setText(articleEntity.getContent());
        } else {
            slidebarLayout.setVisibility(View.VISIBLE);
            highLightWords(wordLevel);
        }
    }
 /*   @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_article, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.article_highlight:
                if (slidebarLayout.getVisibility() == View.VISIBLE) {
                    slidebarLayout.setVisibility(View.GONE);
                    articleContentTV.setText(articleEntity.getContent());
                } else {
                    slidebarLayout.setVisibility(View.VISIBLE);
                    highLightWords(wordLevel);
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }*/
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