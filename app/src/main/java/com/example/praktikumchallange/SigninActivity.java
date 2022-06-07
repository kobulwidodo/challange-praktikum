package com.example.praktikumchallange;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.praktikumchallange.databinding.ActivitySigninBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class SigninActivity extends AppCompatActivity {

    private ActivitySigninBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.buttonLogin.setOnClickListener(view -> login());
        binding.signUpTxt.setOnClickListener(view -> signup());
    }

    private void login() {
        String email = binding.emailInput.getText().toString();
        String password = binding.passwordInput.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
            if (!task.isSuccessful() || task.getResult() == null) {
                Exception exception = task.getException();
                if (exception instanceof FirebaseAuthInvalidUserException || exception instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(this, "Credential not found", Toast.LENGTH_LONG).show();
                }
                return;
            }
            Toast.makeText(this, "Successfully Login", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(SigninActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }

    private void signup() {
        Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}
