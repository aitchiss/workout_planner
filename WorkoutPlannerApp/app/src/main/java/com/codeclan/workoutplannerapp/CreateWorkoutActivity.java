package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class CreateWorkoutActivity extends AppCompatActivity {

    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";
    AppHistory appHistory;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);

        sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        appHistory = new AppHistory();
        workoutLog = appHistory.setup(sharedPref);
    }


    public void onSaveButtonClick(View view){

        EditText workoutNameInput = (EditText) findViewById(R.id.workout_name_input);
        String workoutName = workoutNameInput.getText().toString();
        Workout workout = new Workout(workoutName);
        workoutLog.addWorkoutTemplate(workout);

        appHistory.updateLog(sharedPref, workoutLog);

        Intent intent = new Intent(this, ViewWorkoutActivity.class);
        intent.putExtra("workout", workoutName);
        startActivity(intent);
    }
}
