package com.example.praktikumchallange;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.praktikumchallange.dao.ArticleDAO;
import com.example.praktikumchallange.databinding.ActivityCreateArticleBinding;
import com.example.praktikumchallange.model.Article;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Date;

public class CreateArticleActivity extends AppCompatActivity {
    private ActivityCreateArticleBinding binding;
    private FirebaseUser firebaseUser;
    private final ArticleDAO articleDAO = new ArticleDAO();
    private Uri file;
    private StorageReference storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateArticleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        storage = FirebaseStorage.getInstance().getReference().child("article").child(new Date().getTime() + "");

        binding.fileInput.setOnClickListener(view -> fileInput());
        binding.buttonCreate.setOnClickListener(view -> createArticle());
    }

    private void createArticle() {
        storage.putFile(file).addOnSuccessListener(taskSnapshot -> storage.getDownloadUrl().addOnSuccessListener(uri -> {
            String title = binding.titleInput.getText().toString();
            String description = binding.bodyInput.getText().toString();
            articleDAO
                    .insert(new Article(title, description, firebaseUser.getEmail(), uri.toString()))
                    .addOnSuccessListener(success -> {
                        Toast.makeText(this, "Successfully add article", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(this, HomeActivity.class);
                        startActivity(intent);
                    });
        })).addOnFailureListener(e -> {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        });
    }

    private void fileInput() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Image"), 20);
    }

    @SuppressLint("Range")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20 && resultCode == RESULT_OK && data != null){
            file = data.getData();
            String filename = "";
            try (Cursor cursor = getContentResolver().query(
                    file, null, null, null, null
            )) {
                if (cursor != null && cursor.moveToFirst()) {
                    filename = cursor.getString(
                            cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    );
                }
            }
            binding.fileName.setText(filename);
        }
    }
}
