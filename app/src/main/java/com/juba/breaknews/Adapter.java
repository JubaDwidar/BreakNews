package com.juba.breaknews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.juba.breaknews.Models.Article;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ApiHolder> {

    private List<Article> articleList;
    private Context context;

    public Adapter(List<Article> articleList)
    {
        this.articleList=articleList;
                this.context=context;

    }
    @NonNull
    @Override
    public ApiHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false) ;
    return new ApiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApiHolder apiHolder, int i) {
final ApiHolder holder=apiHolder;
Article model=articleList.get(i);

        Glide.with(context)
                .load(model.getUrlToImage())
                .into(holder.imageView);
        holder.source.setText(model.getSource().getName());
        holder.author.setText(model.getAuthor());
        holder.desc.setText(model.getDescription());
        holder.title.setText(model.getTitle());
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ApiHolder extends RecyclerView.ViewHolder {

        TextView title,desc,author,published_at,source;
        ImageView imageView;
        ProgressBar progressBar;
        public ApiHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title1);
            desc=itemView.findViewById(R.id.desc);
            author=itemView.findViewById(R.id.author);
            source=itemView.findViewById(R.id.source);
            imageView=itemView.findViewById(R.id.img);
            progressBar=itemView.findViewById(R.id.progress_load_photo);

        }
    }
}
