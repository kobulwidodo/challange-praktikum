package com.example.praktikumchallange;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.praktikumchallange.databinding.ActivityAboutUsBinding;

public class AboutUsActivity extends AppCompatActivity {

    private ActivityAboutUsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutUsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.createdAt.setOnClickListener(view -> goToCity());
    }

    private void goToCity() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://id.wikipedia.org/wiki/Kota_Malang"));
        startActivity(intent);
    }
}
