package com.example.praktikumchallange;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.praktikumchallange.adapter.ShoesCardAdapter;
import com.example.praktikumchallange.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        binding.greetingName.setText(this.getResources().getString(R.string.hallo_username, intent.getStringExtra("username")));

        binding.popularRecyclerview.setHasFixedSize(true);
        binding.popularRecyclerview.setAdapter(new ShoesCardAdapter(this));
        binding.newArrivalRecyclerview.setHasFixedSize(true);
        binding.newArrivalRecyclerview.setAdapter(new ShoesCardAdapter(this));

        binding.aboutUsClick.setOnClickListener(view -> aboutus());
    }

    private void aboutus() {
        Intent intent = new Intent(HomeActivity.this, AboutUsActivity.class);
        startActivity(intent);
    }
}
