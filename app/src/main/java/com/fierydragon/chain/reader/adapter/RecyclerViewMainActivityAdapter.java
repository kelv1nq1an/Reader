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
import com.fierydragon.chain.reader.data.articleData;

import java.util.List;

public class RecyclerViewMainActivityAdapter extends RecyclerView.Adapter<RecyclerViewMainActivityAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewMainActivityAdapter";
    private Context mContext;
    private List<articleData> articleList;

    public RecyclerViewMainActivityAdapter(Context context, List<articleData> list) {
        this.articleList = list;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mainactivity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewMainActivityAdapter.ViewHolder holder, final int position) {
        holder.articleCategory.setText(articleList.get(position).getArticleCategory());
        holder.articleName.setText(articleList.get(position).getArticleName());
        holder.articleContent.setText(articleList.get(position).getArticleContent());
        //holder.articleImage.setImageResource(articleList.get(position).getArticleImageUrl());
        holder.readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
                Intent articleIntent = new Intent(mContext, ArticleActivity.class);
                articleIntent.putExtra(mContext.getString(R.string.article_name), articleList.get(position).getArticleName());
                articleIntent.putExtra(mContext.getString(R.string.article_category), articleList.get(position).getArticleCategory());
                articleIntent.putExtra(mContext.getString(R.string.article_content), articleList.get(position).getArticleContent());
                mContext.startActivity(articleIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList == null ? 0 : articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView articleCategory;
        private TextView articleName;
        private TextView articleContent;
        private TextView readMore;
        private ImageView articleImage;

        public ViewHolder(View itemView) {
            super(itemView);
            articleCategory = (TextView) itemView.findViewById(R.id.article_category_item_mainactivity);
            articleName = (TextView) itemView.findViewById(R.id.article_name_item_mainactivity);
            articleContent = (TextView) itemView.findViewById(R.id.article_content_item_mainactivity);
            readMore = (TextView) itemView.findViewById(R.id.read_more_item_mainactivity);
            articleImage = (ImageView) itemView.findViewById(R.id.article_image_item_mainactivity);
        }
    }
}
