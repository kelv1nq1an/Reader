package com.fierydragon.chain.reader.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fierydragon.chain.reader.App;
import com.fierydragon.chain.reader.R;
import com.fierydragon.chain.reader.data.ArticleData;

/**
 * Copyright KelvinQian
 */
public class TranslationFragment extends Fragment {
    private static final String TAG = "TranslationFragment";
    private View view;
    private TextView translationTV;

    private int position;

    private App app;
    private ArticleData.ArticleEntity articleEntity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_translation, container, false);

        app = (App) getActivity().getApplication();
        position = app.getPosition();
        articleEntity = app.getArticleData().getArticle().get(position);

        translationTV = (TextView) view.findViewById(R.id.article_translation_fragment_translation);
        translationTV.setText("    " + articleEntity.getTranslation());
        return view;
    }
}
