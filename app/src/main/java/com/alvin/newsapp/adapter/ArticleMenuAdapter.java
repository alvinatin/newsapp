package com.alvin.newsapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alvin.newsapp.R;
import com.alvin.newsapp.model.Article;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.fragment;

/**
 * Created by Nakama on 12/6/2017.
 */

public class ArticleMenuAdapter extends RecyclerView.Adapter<ArticleMenuAdapter.ArticleViewHolder> {

    private List<Article> articleList;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    private Context getContext() {
        return mContext;
    }

    public ArticleMenuAdapter(Context context, List<Article> list, OnItemClickListener itemClickListener) {
        this.articleList = list;
        this.mContext = context;
        this.onItemClickListener = itemClickListener;
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvArticle;
        ImageView ivArticle;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            tvArticle = (TextView) itemView.findViewById(R.id.tv_article);
            ivArticle = (ImageView) itemView.findViewById(R.id.iv_article);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = this.getLayoutPosition();
            onItemClickListener.onItemClick(v, position);
        }
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleViewHolder((LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false)));
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);
        String imageUrl = article.getUrlToImage();
        if (imageUrl != null) {
            Glide.with(mContext)
                    .load(imageUrl)
                    .error(R.drawable.placeholder_image)
                    .into(holder.ivArticle);
        }
        else{
            Glide.with(mContext)
                    .load(R.drawable.placeholder_image)
                    .into(holder.ivArticle);
        }
        holder.tvArticle.setText(article.getTitle());
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void addDataArticle(List<Article> articles) {
        this.articleList = articles;
    }

    public void setFilter(List<Article> articleList) {
        this.articleList = new ArrayList<>();
        this.articleList.addAll(articleList);
        notifyDataSetChanged();
    }

    public List<Article> getArticleList() {
        return articleList;
    }
}
