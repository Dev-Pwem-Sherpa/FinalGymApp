package com.example.gymfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.gymfitnessapp.Model.User;

public class ProfileActivity extends AppCompatActivity {

    User user;
    TextView name, email;
    CardView logout, about;
    ImageView edit_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        user = getIntent().getParcelableExtra("user");

        if (user != null) {
            Toast.makeText(getBaseContext(), "User : " + user.getEmail(), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getBaseContext(), "User Not Found ", Toast.LENGTH_SHORT).show();
        }

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        logout = findViewById(R.id.logout);
        about = findViewById(R.id.about);
        edit_profile = findViewById(R.id.edit_profile);

        name.setText(user.getName());
        email.setText(user.getEmail());

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ProfileDetail.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "About Application", Toast.LENGTH_SHORT).show();
            }
        });
    }
}