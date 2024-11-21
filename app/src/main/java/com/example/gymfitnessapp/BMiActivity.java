package com.example.gymfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BMiActivity extends AppCompatActivity {

    TextView weightValueText;
    TextView ageValueText;
    TextView increaseWeightButton;
    TextView decreaseWeightButton;
    TextView increaseAgeButton;
    TextView decreaseAgeButton;
    TextView calculateBtn;
    TextView displayHeight;
    SeekBar heightSeekBar;

    int heightValue;
    int weightValue;
    int ageValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        weightValueText = findViewById(R.id.weightText);
        ageValueText = findViewById(R.id.ageText);
        increaseWeightButton = findViewById(R.id.plusWeight);
        decreaseWeightButton = findViewById(R.id.minusWeight);
        increaseAgeButton = findViewById(R.id.plusAge);
        decreaseAgeButton = findViewById(R.id.minusAge);
        calculateBtn = findViewById(R.id.calcuulateBtn);
        displayHeight = findViewById(R.id.displayHeight);
        heightSeekBar = findViewById(R.id.seekBar);

        heightValue = Integer.parseInt(displayHeight.getText().toString());
        weightValue = Integer.parseInt(weightValueText.getText().toString());
        ageValue = Integer.parseInt(ageValueText.getText().toString());

        heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String height = String.valueOf(progress);
                displayHeight.setText(height);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        increaseWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightValue++;
                weightValueText.setText(weightValue+"");
            }
        });

        decreaseWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightValue--;
                weightValueText.setText(weightValue+"");
            }
        });

        increaseAgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ageValue++;
                ageValueText.setText(ageValue+"");
            }
        });

        decreaseAgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ageValue--;
                ageValueText.setText(ageValue+"");
            }
        });

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heightValue = Integer.parseInt(displayHeight.getText().toString());
                weightValue = Integer.parseInt(weightValueText.getText().toString());
                float heightinMeters = heightValue/100f;
                float BMI = weightValue / (heightinMeters * heightinMeters);

//                Log.d("BMI_CALCULATION", "Height: " + heightValue + " Weight: " + weightValue + " BMI: " + BMI);

//                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
//                intent.putExtra("BMI_RESULT", BMI);
//                startActivity(intent);
            }
        });

    }

}