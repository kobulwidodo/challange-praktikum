package com.example.praktikumchallange;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.praktikumchallange.adapter.ArticleCardAdapter;
import com.example.praktikumchallange.dao.ArticleDAO;
import com.example.praktikumchallange.databinding.ActivityHomeBinding;
import com.example.praktikumchallange.model.Article;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private FirebaseUser firebaseUser;
    private ArticleCardAdapter articleCardAdapter;
    private final ArticleDAO articleDAO = new ArticleDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        binding.emailDisplay.setText(firebaseUser.getEmail());

        binding.popularRecyclerview.setHasFixedSize(true);
        articleCardAdapter = new ArticleCardAdapter(this, articleDAO);
        binding.popularRecyclerview.setAdapter(articleCardAdapter);
        getLiveData();

        binding.aboutUsClick.setOnClickListener(view -> aboutus());
        binding.logout.setOnClickListener(view -> logout());
        binding.addArticle.setOnClickListener(view -> addArticle());
    }

    private void getLiveData() {
        articleDAO.get().addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Article> articles = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Article article = dataSnapshot.getValue(Article.class);
                    assert article != null;
                    article.key = dataSnapshot.getKey();
                    articles.add(article);
                }
                articleCardAdapter.setListArticle(articles);
                articleCardAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addArticle() {
        Intent intent = new Intent(this, CreateArticleActivity.class);
        startActivity(intent);
    }

    private void aboutus() {
        Intent intent = new Intent(HomeActivity.this, AboutUsActivity.class);
        startActivity(intent);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "Successfullt sign out", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, SigninActivity.class);
        startActivity(intent);
        finish();
    }
}
