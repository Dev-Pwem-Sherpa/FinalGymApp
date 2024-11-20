package com.example.gymfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import info.hoang8f.widget.FButton;

public class MainActivity extends AppCompatActivity {

    Button btnExercises, btnSetting, btnCalendar;
    ImageView btnTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExercises = findViewById(R.id.btnExercise);
        btnSetting = findViewById(R.id.btnSetting);
        btnTraining = (ImageView)findViewById(R.id.btnTraining);
        btnCalendar = findViewById(R.id.btnCalender);

        btnExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentExercise = new Intent(MainActivity.this, ListExercisesActivity.class);
                startActivity(intentExercise);
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSetting = new Intent(MainActivity.this, Setting.class);
                startActivity(intentSetting);
            }
        });

        btnTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Daily_Training.class);
                startActivity(intent);
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Calendar.class);
                startActivity(intent);
            }
        });
    }
}

