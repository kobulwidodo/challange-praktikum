package com.example.praktikumchallange;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.praktikumchallange.adapter.ArticleCardAdapter;
import com.example.praktikumchallange.databinding.ActivityHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        binding.emailDisplay.setText(firebaseUser.getEmail());

        binding.popularRecyclerview.setHasFixedSize(true);
        binding.popularRecyclerview.setAdapter(new ArticleCardAdapter(this));

        binding.aboutUsClick.setOnClickListener(view -> aboutus());
        binding.logout.setOnClickListener(view -> logout());
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
    }
}
