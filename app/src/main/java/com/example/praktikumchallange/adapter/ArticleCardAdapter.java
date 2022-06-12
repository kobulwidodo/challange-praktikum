package com.example.praktikumchallange.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.praktikumchallange.R;
import com.example.praktikumchallange.ArticleDetailActivity;
import com.example.praktikumchallange.dao.ArticleDAO;
import com.example.praktikumchallange.model.Article;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ArticleCardAdapter extends RecyclerView.Adapter<ArticleCardAdapter.ArticleCardViewHolder> {
    private Context context;
    ArrayList<Article> dataset = new ArrayList<>();
    private final ArticleDAO articleDAO;

    public ArticleCardAdapter(Context context, ArticleDAO articleDAO) {
        this.context = context;
        this.articleDAO = articleDAO;
    }

    public void setListArticle(ArrayList<Article> listArticle) {
        Collections.reverse(listArticle);
        this.dataset = listArticle;
    }

    public static class ArticleCardViewHolder extends RecyclerView.ViewHolder {
        ImageView imageResourceId;
        TextView publisher;
        TextView title;
        MaterialCardView card;

        public ArticleCardViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageResourceId = itemView.findViewById(R.id.article_img);
            this.title = itemView.findViewById(R.id.title);
            this.publisher = itemView.findViewById(R.id.publisher);
            this.card = itemView.findViewById(R.id.shoes_item);
        }
    }

    @NonNull
    @Override
    public ArticleCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_card_item, parent, false);

        return new ArticleCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleCardViewHolder holder, int position) {
        Article item = dataset.get(position);
        Glide.with(holder.imageResourceId).load(item.image).into(holder.imageResourceId);
        holder.title.setText(item.title);
        holder.publisher.setText(item.publisher);
        holder.card.setOnClickListener(view -> {
            Intent intent = new Intent(context, ArticleDetailActivity.class);
            intent.putExtra("imageResourceId", item.image);
            intent.putExtra("title", item.title);
            intent.putExtra("publisher", item.publisher);
            intent.putExtra("description", item.description);
            intent.putExtra("key", item.key);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
