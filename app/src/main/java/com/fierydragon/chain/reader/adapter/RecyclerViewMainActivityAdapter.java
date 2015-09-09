package com.fierydragon.chain.reader.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fierydragon.chain.reader.R;
import com.fierydragon.chain.reader.activity.ArticleActivity;
import com.fierydragon.chain.reader.data.ArticleData;

/**
 * Copyright KelvinQian
 */
public class RecyclerViewMainActivityAdapter extends RecyclerView.Adapter<RecyclerViewMainActivityAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewMainActivityAdapter";
    private Context mContext;
    private ArticleData articleData;

    public RecyclerViewMainActivityAdapter(Context context, ArticleData data) {
        this.mContext = context;
        this.articleData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mainactivity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewMainActivityAdapter.ViewHolder holder, final int position) {
        holder.articleCategory.setText(articleData.getArticle().get(position).getLesson());
        holder.articleName.setText(articleData.getArticle().get(position).getTitle());
        holder.articleContent.setText(articleData.getArticle().get(position).getContent());
        //holder.articleImage.setImageResource(articleList.get(position).getArticleImageUrl());
        holder.readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent articleIntent = new Intent(mContext, ArticleActivity.class);
                articleIntent.putExtra(mContext.getString(R.string.position), position);
                mContext.startActivity(articleIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleData == null ? 0 : articleData.getArticle().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView articleCategory;
        private TextView articleName;
        private TextView articleContent;
        private TextView readMore;
        private ImageView articleImage;

        public ViewHolder(final View itemView) {
            super(itemView);
            articleCategory = (TextView) itemView.findViewById(R.id.article_category_item_mainactivity);
            articleName = (TextView) itemView.findViewById(R.id.article_name_item_mainactivity);
            articleContent = (TextView) itemView.findViewById(R.id.article_content_item_mainactivity);
            readMore = (TextView) itemView.findViewById(R.id.read_more_item_mainactivity);
            articleImage = (ImageView) itemView.findViewById(R.id.article_image_item_mainactivity);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent articleIntent = new Intent(mContext, ArticleActivity.class);
                    articleIntent.putExtra(mContext.getString(R.string.position), getLayoutPosition());
                    mContext.startActivity(articleIntent);
                }
            });
        }
    }
}
