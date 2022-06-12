package com.example.praktikumchallange;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.praktikumchallange.dao.ArticleDAO;
import com.example.praktikumchallange.databinding.ActivityCreateArticleBinding;
import com.example.praktikumchallange.model.Article;

public class UpdateArticleActivity extends AppCompatActivity {
    private ActivityCreateArticleBinding binding;
    private final ArticleDAO articleDao = new ArticleDAO();
    private String key;
    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateArticleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        key = intent.getStringExtra("key");

        binding.file.setVisibility(View.GONE);
        binding.fileName.setVisibility(View.GONE);
        binding.fileInput.setVisibility(View.GONE);
        binding.titleSection.setText("Update Article");
        binding.buttonCreate.setText("update");

        getData();

        binding.buttonCreate.setOnClickListener(view -> update());
    }

    private void getData() {
        articleDao.getByKey(key)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        article = task.getResult().getValue(Article.class);
                        assert article != null;
                        binding.titleInput.setText(article.title);
                        binding.bodyInput.setText(article.description);
                    }
                });
    }

    private void update() {
        String title = binding.titleInput.getText().toString();
        String body = binding.bodyInput.getText().toString();
        article.title = title;
        article.description = body;
        articleDao.update(key, article)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(this, "Successfully updated article", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                }).addOnFailureListener(e -> {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        });
    }
}
