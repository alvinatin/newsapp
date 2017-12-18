package com.alvin.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alvin.newsapp.ArticleActivity;
import com.alvin.newsapp.R;
import com.alvin.newsapp.model.Source;

import java.util.List;

/**
 * Created by Nakama on 12/4/2017.
 */

public class SourceMenuAdapter extends RecyclerView.Adapter<SourceMenuAdapter.MyViewHolder>{

    private List<Source> sourceList;
    private Context mContext;
    OnItemClickListener onItemClickListener;

    private Context getContext() {
        return mContext;
    }

    public SourceMenuAdapter(Context context, List<Source> list){
        this.sourceList = list;
        this.mContext = context;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvTitle;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            onItemClickListener.onItemClick(position);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_source, parent, false));
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Source source = sourceList.get(position);
        holder.tvTitle.setText(source.getName());

    }

    @Override
    public int getItemCount() {
        return sourceList.size();
    }

    public void addDataSource(List<Source> sources){
        this.sourceList = sources;
    }
}
