package com.example.gymfitnessapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gymfitnessapp.Database.UserDB;
import com.example.gymfitnessapp.Model.User;

public class ProfileDetail extends AppCompatActivity {
    User user;
    EditText edit_name;
    Button btn_save;
    UserDB userDB;
    TextView profile_name, profile_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_detail);

        user = getIntent().getParcelableExtra("user");
        userDB = new UserDB(getBaseContext());

        if (user != null) {
            Toast.makeText(getBaseContext(), "User: " + user.getEmail(), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getBaseContext(), "User Not Found", Toast.LENGTH_SHORT).show();
        }

        edit_name = findViewById(R.id.edit_name);
        edit_name.setText(user.getName());

        btn_save = findViewById(R.id.btn_save);
        profile_name = findViewById(R.id.profile_name);
        profile_email = findViewById(R.id.profile_email);

        profile_name.setText(user.getName());
        profile_email.setText(user.getEmail());

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setName(edit_name.getText().toString());
                userDB.updateUser(user);

                finish();
                startActivity(getIntent());
            }
        });


    }
}