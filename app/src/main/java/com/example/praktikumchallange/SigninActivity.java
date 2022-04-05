package com.example.praktikumchallange;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.praktikumchallange.databinding.ActivitySigninBinding;

public class SigninActivity extends AppCompatActivity {

    private ActivitySigninBinding binding;
    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        username = binding.usernameInput;
        password = binding.passwordInput;

        if (savedInstanceState != null) {
            username.setText(savedInstanceState.getString("username"));
            password.setText(savedInstanceState.getString("password"));
        }

        binding.buttonLogin.setOnClickListener(view -> login());
        binding.signUpTxt.setOnClickListener(view -> signup());
    }

    private void login() {
        Intent intent = new Intent(SigninActivity.this, HomeActivity.class);
        intent.putExtra("username", username.getText().toString());
        startActivity(intent);
    }

    private void signup() {
        Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("username", username.getText().toString());
        outState.putString("password", password.getText().toString());
    }
}
