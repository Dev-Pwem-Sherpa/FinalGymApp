package com.example.gymfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.gymfitnessapp.Database.UserDB;
import com.example.gymfitnessapp.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    TextView cta_registration;
    AppCompatButton btnLogin;
    EditText editTextEmail, editTextPassword;
    UserDB userDB;
//    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progress_login);

        cta_registration = findViewById(R.id.cta_registration);

        cta_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextEmail = findViewById(R.id.editTextEmail);
                editTextPassword = findViewById(R.id.editTextPassword);
                progressBar.setVisibility(View.VISIBLE);

                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

//                mAuth.createUserWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                progressBar.setVisibility(View.GONE);
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(getBaseContext(), "Account Created", Toast.LENGTH_SHORT).show();
//                                }else {
//                                    Toast.makeText(getBaseContext(), "Auth Failed", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });


                userDB = new UserDB(getBaseContext());

                User user = userDB.getUser(email);

                if (user != null ) {
                    if (user.getPassword().equals(editTextPassword.getText().toString())) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(getBaseContext(), "Password is wrong, Please Try Again" , Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getBaseContext(), "User with email : " + email + " doesn't exist.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
////        updateUI(currentUser);
//    }
}