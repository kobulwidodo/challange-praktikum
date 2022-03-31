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

import com.example.praktikumchallange.HomeActivity;
import com.example.praktikumchallange.R;
import com.example.praktikumchallange.ShoesDetailActivity;
import com.example.praktikumchallange.data.DataSource;
import com.example.praktikumchallange.model.Shoes;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class ShoesCardAdapter extends RecyclerView.Adapter<ShoesCardAdapter.ShoesCardViewHolder> {
    private Context context;
    ArrayList<Shoes> dataset;

    public ShoesCardAdapter(Context context) {
        this.context = context;
        dataset = new DataSource().shoes;
    }

    public static class ShoesCardViewHolder extends RecyclerView.ViewHolder {
        ImageView imageResourceId;
        TextView type;
        TextView title;
        TextView price;
        MaterialCardView card;

        public ShoesCardViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageResourceId = itemView.findViewById(R.id.shoes_img);
            this.type = itemView.findViewById(R.id.shoes_type);
            this.title = itemView.findViewById(R.id.shoes_title);
            this.price = itemView.findViewById(R.id.shoes_price);
            this.card = itemView.findViewById(R.id.shoes_item);
        }
    }

    @NonNull
    @Override
    public ShoesCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoes_card_item, parent, false);

        return new ShoesCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoesCardViewHolder holder, int position) {
        Shoes item = dataset.get(position);
        holder.imageResourceId.setImageResource(item.imageResourceId);
        holder.type.setText(item.type);
        holder.title.setText(item.title);
        holder.price.setText(item.price);
        holder.card.setOnClickListener(view -> {
            Intent intent = new Intent(context, ShoesDetailActivity.class);
            intent.putExtra("imageResourceId", item.imageDetailId);
            intent.putExtra("type", item.type);
            intent.putExtra("title", item.title);
            intent.putExtra("price", item.price);
            intent.putExtra("description", item.description);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
