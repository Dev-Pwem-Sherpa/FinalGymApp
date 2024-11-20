package com.example.gymfitnessapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.gymfitnessapp.Database.GymDB;

import java.util.Calendar;
import java.util.Date;

public class Setting extends AppCompatActivity {

    Button btnSave;
    RadioButton rdyEasy,rdyMedium,rdyHard;
    RadioGroup radioGroup;
    GymDB gymDB;
    ToggleButton switchAlarm;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btnSave = findViewById(R.id.btnSave);
        radioGroup = (RadioGroup)findViewById(R.id.ready);
        rdyEasy = (RadioButton)findViewById(R.id.rdyEasy);
        rdyMedium = (RadioButton)findViewById(R.id.rdyMedium);
        rdyHard = (RadioButton)findViewById(R.id.rdyHard);
        switchAlarm = (ToggleButton)findViewById(R.id.switchAlarm);
        timePicker = (TimePicker)findViewById(R.id.timePicker);

        gymDB = new GymDB(this);

        int mode = gymDB.getSettingMode();
        setRadioButton(mode);

        //event
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveWorkOutMode();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    saveAlarm(switchAlarm.isChecked());
                }
                Toast.makeText(Setting.this, R.string.saved, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void saveAlarm(boolean checked) {
        if (checked) {
            Intent alarmIntent = new Intent(Setting.this, AlarmNotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    this,
                    0,
                    alarmIntent,
                    PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_CANCEL_CURRENT // Ensures old PendingIntent is replaced
            );

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                if (!alarmManager.canScheduleExactAlarms()) {
                    Toast.makeText(this, "Permission required to set exact alarms.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
                    startActivity(intent);
                    return;
                }
            }

            int hour, minute;
            if (Build.VERSION.SDK_INT >= 23) {
                hour = timePicker.getHour();
                minute = timePicker.getMinute();
            } else {
                hour = timePicker.getCurrentHour();
                minute = timePicker.getCurrentMinute();
            }

            Calendar cal_alarm = Calendar.getInstance();
            cal_alarm.set(Calendar.HOUR_OF_DAY, hour);
            cal_alarm.set(Calendar.MINUTE, minute);
            cal_alarm.set(Calendar.SECOND, 0);

            Calendar cal_now = Calendar.getInstance();
            if (cal_alarm.before(cal_now)) {
                cal_alarm.add(Calendar.DATE, 1);
            }

            Log.d("TimePicker", "Hour: " + hour + ", Minute: " + minute);
            Log.d("saveAlarm", "TimePicker hour: " + hour + ", minute: " + minute);
            Log.d("saveAlarm", "Alarm set for: " + cal_alarm.getTime());

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    cal_alarm.getTimeInMillis(),
                    pendingIntent
            );
            Toast.makeText(this, "Alarm set successfully!", Toast.LENGTH_SHORT).show();
        }
    }


    private void saveWorkOutMode() {
        int selectedID = radioGroup.getCheckedRadioButtonId();
        if (selectedID == rdyEasy.getId())
            gymDB.saveSettingMode(0);
        else if (selectedID == rdyMedium.getId())
            gymDB.saveSettingMode(1);
        else if (selectedID == rdyHard.getId())
            gymDB.saveSettingMode(2);
    }

    private void setRadioButton(int mode) {
        if (mode == 0)
            radioGroup.check(R.id.rdyEasy);
        else if (mode == 1)
            radioGroup.check(R.id.rdyMedium);
        else if (mode == 2)
            radioGroup.check(R.id.rdyHard);
    }
}
