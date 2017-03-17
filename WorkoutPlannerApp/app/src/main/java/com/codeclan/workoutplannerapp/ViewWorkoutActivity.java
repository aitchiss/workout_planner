package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ViewWorkoutActivity extends AppCompatActivity {

    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workout);


        Gson gson = new Gson();
        SharedPreferences sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        String retrievedLog = sharedPref.getString("WorkoutLog", "Nothing here");

        TypeToken<WorkoutLog> workoutLogTypeToken = new TypeToken<WorkoutLog>(){};
        WorkoutLog workoutLog = gson.fromJson(retrievedLog, workoutLogTypeToken.getType());


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String selectedWorkoutName = extras.getString("workout");


        Workout workout = workoutLog.getWorkoutTemplate(selectedWorkoutName);

        TextView listingView = (TextView) findViewById(R.id.workout_name);
        listingView.setText(workout.getName());
    }
}
