package com.fierydragon.chain.reader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fierydragon.chain.reader.R;
import com.fierydragon.chain.reader.data.ArticleData;

import java.util.List;

/**
 * Copyright KelvinQian
 */
public class RecyclerViewWordsFragmentAdapter extends RecyclerView.Adapter<RecyclerViewWordsFragmentAdapter.ViewHolder> {
    private Context mContext;
    private List<ArticleData.ArticleEntity.WordsEntity> wordsEntityList;

    public RecyclerViewWordsFragmentAdapter(Context context, List<ArticleData.ArticleEntity.WordsEntity> wordsEntityList) {
        this.mContext = context;
        this.wordsEntityList = wordsEntityList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_wordsfragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.wordTV.setText(wordsEntityList.get(position).getWord());
        holder.categoryTV.setText(wordsEntityList.get(position).getCategory());
        holder.translationTV.setText(wordsEntityList.get(position).getTranslation());
    }

    @Override
    public int getItemCount() {
        return wordsEntityList == null ? 0 : wordsEntityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView wordTV;
        private TextView categoryTV;
        private TextView translationTV;

        public ViewHolder(View itemView) {
            super(itemView);
            wordTV = (TextView) itemView.findViewById(R.id.word_item_wordsfragment);
            categoryTV = (TextView) itemView.findViewById(R.id.category_item_wordsfragment);
            translationTV = (TextView) itemView.findViewById(R.id.translation_item_wordsfragment);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, wordsEntityList.get(getLayoutPosition()).getWord(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
