package com.example.praktikumchallange;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.praktikumchallange.databinding.ActivitySigninBinding;

public class SigninActivity extends AppCompatActivity {

    private ActivitySigninBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonLogin.setOnClickListener(view -> login());
        binding.signUpTxt.setOnClickListener(view -> signup());
    }

    private void login() {
        String username = binding.usernameInput.getText().toString();
        Intent intent = new Intent(SigninActivity.this, HomeActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    private void signup() {
        Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}
