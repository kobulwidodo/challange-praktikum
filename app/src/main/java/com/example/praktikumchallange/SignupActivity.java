package com.example.praktikumchallange;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.praktikumchallange.databinding.ActivitySignupBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.concurrent.Executors;

public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.signInTxt.setOnClickListener(view -> signin());
        binding.btnSignup.setOnClickListener(view -> signup());
    }

    private void signup() {
        String email = binding.formEmail.getText().toString();
        String password = binding.formPassword.getText().toString();
        String passwordConfirm = binding.formPasswordConfirm.getText().toString();

        if (!passwordConfirm.equals(password)) {
            Toast.makeText(this, "Password doesnt match", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (!task.isSuccessful() || task.getResult() == null) {
                Exception exception = task.getException();
                if (exception instanceof FirebaseAuthUserCollisionException) {
                    Toast.makeText(this, "Email has been registered", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Failed to register user", Toast.LENGTH_LONG).show();
                }
                return;
            }
            Toast.makeText(this, "Successfully registered", Toast.LENGTH_LONG).show();
        });
        Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private void signin() {
        Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
        startActivity(intent);
    }
}
