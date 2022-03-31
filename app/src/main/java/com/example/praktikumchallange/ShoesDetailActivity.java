package com.example.praktikumchallange;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.praktikumchallange.databinding.ActivityShoesDetailBinding;

public class ShoesDetailActivity extends AppCompatActivity {
    private ActivityShoesDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShoesDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        binding.shoesDetailImg.setImageResource(intent.getIntExtra("imageResourceId", 1));
        binding.shoesDetailType.setText(intent.getStringExtra("type"));
        binding.shoesDetailTitle.setText(intent.getStringExtra("title"));
        binding.shoesDetailPrice.setText(intent.getStringExtra("price"));
        binding.shoesDetailDescription.setText(intent.getStringExtra("description"));
    }

}
