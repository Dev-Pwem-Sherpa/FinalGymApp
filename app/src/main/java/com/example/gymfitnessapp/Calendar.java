package com.example.gymfitnessapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymfitnessapp.Custom.WorkoutDoneDecorator;
import com.example.gymfitnessapp.Database.GymDB;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class Calendar extends AppCompatActivity {

    MaterialCalendarView materialCalendarView;
    HashSet<CalendarDay> list = new HashSet<>();

    GymDB gymDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        gymDB = new GymDB(this);

        materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendar);

        // Fetch the workout days from the database
        List<String> workoutDay = gymDB.getWorkoutDays();
        HashSet<CalendarDay> convertedList = new HashSet<>();

        for (String value : workoutDay) {
            // Convert the String timestamp to Date
            long timestamp = Long.parseLong(value);
            Date date = new Date(timestamp);

            //year, month, and day extracted from Date Create CalendarDay using the
            CalendarDay calendarDay = CalendarDay.from(date.getYear() + 1900, date.getMonth() + 1, date.getDate());
            convertedList.add(calendarDay);
        }

        // Add a decorator to mark workout days on the calendar
        materialCalendarView.addDecorator(new WorkoutDoneDecorator(convertedList));
    }
}
