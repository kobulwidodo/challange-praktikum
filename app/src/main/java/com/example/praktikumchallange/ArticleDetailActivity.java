package com.example.praktikumchallange;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.praktikumchallange.databinding.ActivityArticleDetailBinding;

public class ArticleDetailActivity extends AppCompatActivity {
    private ActivityArticleDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArticleDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        binding.articleDetailImg.setImageResource(intent.getIntExtra("imageResourceId", 1));
        binding.articleDetailTitle.setText(intent.getStringExtra("title"));
        binding.articleDetailDescription.setText(intent.getStringExtra("description"));
    }

}
