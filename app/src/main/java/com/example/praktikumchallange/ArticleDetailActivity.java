package com.example.praktikumchallange;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.praktikumchallange.dao.ArticleDAO;
import com.example.praktikumchallange.databinding.ActivityArticleDetailBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ArticleDetailActivity extends AppCompatActivity {
    private ActivityArticleDetailBinding binding;
    private String publisher;
    private String key;
    private final ArticleDAO articleDAO = new ArticleDAO();
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArticleDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        Glide.with(binding.articleDetailImg)
                .load(intent.getStringExtra("imageResourceId"))
                .into(binding.articleDetailImg);
        binding.articleDetailTitle.setText(intent.getStringExtra("title"));
        binding.articleDetailDescription.setText(intent.getStringExtra("description"));
        publisher = intent.getStringExtra("publisher");

        policy();
        binding.edtBtn.setOnClickListener(view -> edtBtn());
        binding.dltBtl.setOnClickListener(view -> dltBtn());
    }

    private void policy() {
        if (!publisher.equals(firebaseUser.getEmail())) {
            binding.edtBtn.setVisibility(View.INVISIBLE);
            binding.dltBtl.setVisibility(View.INVISIBLE);
        }
    }

    private void edtBtn() {
        Intent intent = new Intent(this, UpdateArticleActivity.class);
        intent.putExtra("key", key);
        startActivity(intent);
    }

    private void dltBtn() {
        articleDAO.delete(key)
                .addOnSuccessListener(unused -> {
                    Intent intent = new Intent(this, HomeActivity.class);
                    Toast.makeText(this, "Successfully delete article", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                });
    }

}
