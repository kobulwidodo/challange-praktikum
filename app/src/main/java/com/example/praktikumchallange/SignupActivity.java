package com.example.praktikumchallange;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.praktikumchallange.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signInTxt.setOnClickListener(view -> signin());
        binding.btnSignup.setOnClickListener(view -> signup());
    }

    private void signup() {
        String username = binding.formUsername.getText().toString();
        Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    private void signin() {
        Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
        startActivity(intent);
    }
}
