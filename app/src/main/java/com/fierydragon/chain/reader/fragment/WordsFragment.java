package com.fierydragon.chain.reader.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fierydragon.chain.reader.App;
import com.fierydragon.chain.reader.R;
import com.fierydragon.chain.reader.adapter.RecyclerViewWordsFragmentAdapter;
import com.fierydragon.chain.reader.data.ArticleData;

import java.util.List;

/**
 * Copyright KelvinQian
 */
public class WordsFragment extends Fragment {
    private static final String TAG = "WordsFragment";
    private View view;
    private RecyclerView recyclerViewWordsFragment;

    private int position;
    private App app;

    private List<ArticleData.ArticleEntity.WordsEntity> wordsEntityList;
    private RecyclerViewWordsFragmentAdapter wordsFragmentAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_words, container, false);

        app = (App) getActivity().getApplication();
        position = app.getPosition();
        wordsEntityList = app.getArticleData().getArticle().get(position).getWords();

        recyclerViewWordsFragment = (RecyclerView) view.findViewById(R.id.wordslist_fragment_words);
        recyclerViewWordsFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        wordsFragmentAdapter = new RecyclerViewWordsFragmentAdapter(getActivity(), wordsEntityList);
        recyclerViewWordsFragment.setAdapter(wordsFragmentAdapter);
        return view;
    }
}
