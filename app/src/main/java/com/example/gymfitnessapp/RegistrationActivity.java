package com.example.gymfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.gymfitnessapp.Database.UserDB;
import com.example.gymfitnessapp.Model.User;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    AppCompatButton buttonRegister;
    EditText editTextName, editTextEmail, editTextPassword, editTextConfirmPassword;
    UserDB userDB;
    ProgressBar progressBar;
//    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        buttonRegister = findViewById(R.id.buttonRegister);
//        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progress_registration);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextName = findViewById(R.id.editTextName);
                editTextEmail = findViewById(R.id.editTextEmail);
                editTextPassword = findViewById(R.id.editTextPassword);
                editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);

                progressBar.setVisibility(View.VISIBLE);

                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String c_password = editTextConfirmPassword.getText().toString();


                if (password.equals(c_password)) {
                    User user = new User(name, email, password);
                    Toast.makeText(getBaseContext(), "User : " + user.getName() + " Is Sucessfully Registered", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, BMiActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getBaseContext(), "Password not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}