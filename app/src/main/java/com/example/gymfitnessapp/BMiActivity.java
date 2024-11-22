package com.example.gymfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gymfitnessapp.Database.UserDB;
import com.example.gymfitnessapp.Model.User;

public class BMiActivity extends AppCompatActivity {
    TextView weightValueText, ageValueText, increaseWeightButton, decreaseWeightButton, increaseAgeButton, decreaseAgeButton, calculateBtn, displayHeight;
    SeekBar heightSeekBar;
    LinearLayout genderMaleLinearLayout, genderFemaleLinearLayout;
    int heightValue, weightValue, ageValue;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        weightValueText = findViewById(R.id.weightText);
        ageValueText = findViewById(R.id.ageText);
        increaseAgeButton = findViewById(R.id.plusAge);
        decreaseAgeButton = findViewById(R.id.minusAge);
        increaseWeightButton = findViewById(R.id.plusWeight);
        decreaseWeightButton = findViewById(R.id.minusWeight);
        calculateBtn = findViewById(R.id.calculateBtn);
        heightSeekBar = findViewById(R.id.seekBar);
        displayHeight = findViewById(R.id.displayHeight);
        heightValue = Integer.valueOf(displayHeight.getText().toString());
        weightValue = Integer.valueOf(weightValueText.getText().toString());
        ageValue = Integer.valueOf(ageValueText.getText().toString());
        genderMaleLinearLayout = findViewById(R.id.genderMaleLinear);
        genderFemaleLinearLayout = findViewById(R.id.genderFemaleLinear);
        genderMaleLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int children = genderMaleLinearLayout.getChildCount();
                for (int i = 0; i < children; i++) {
                    if (genderMaleLinearLayout.getChildAt(i) instanceof TextView) {
                        TextView genderTextView = (TextView) genderMaleLinearLayout.getChildAt(i);
                        gender = String.valueOf(genderTextView.getText());
                    }
                }
                genderMaleLinearLayout.setBackgroundColor(getResources().getColor(R.color.colorDarkGreen));
                genderFemaleLinearLayout.setBackgroundColor(getResources().getColor(R.color.colorDarkGreen));
            }
        });
        genderFemaleLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int children = genderFemaleLinearLayout.getChildCount();
                for (int i = 0; i < children; i++) {
                    if (genderFemaleLinearLayout.getChildAt(i) instanceof TextView) {
                        gender = String.valueOf(((TextView) genderFemaleLinearLayout.getChildAt(i)).getText());
                    }
                }
                genderFemaleLinearLayout.setBackgroundColor(getResources().getColor(R.color.colorDarkGreen));
                genderMaleLinearLayout.setBackgroundColor(getResources().getColor(R.color.colorDarkGreen));
            }
        });
        heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String height = String.valueOf(progress);
                displayHeight.setText(height);
                heightValue = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        increaseWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightValue++;

                weightValueText.setText(weightValue + "");
            }
        });
        decreaseWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightValue--;
                weightValueText.setText(weightValue + "");
            }
        });
        decreaseAgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ageValue--;
                ageValueText.setText(ageValue + "");
            }
        });
        increaseAgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ageValue++;
                ageValueText.setText(ageValue + "");
            }
        });
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BMiActivity.this, LoginActivity.class);
                User user = getIntent().getParcelableExtra("user");

                double bmi = ((double)weightValue/(heightValue*heightValue))*10000;
                user.setBmi(bmi);
                UserDB userDB = new UserDB(getBaseContext());
                userDB.saveUser(user);
                startActivity(intent);
            }
        });

    }

}