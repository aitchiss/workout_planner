package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class WorkoutHistoryActivity extends AppCompatActivity {

    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";
    WorkoutTemplate workout;
    AppHistory appHistory;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_history);

        sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        appHistory = new AppHistory();
        workoutLog = appHistory.setup(sharedPref);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String selectedWorkoutName = extras.getString("workout");

        workout = workoutLog.getWorkoutTemplate(selectedWorkoutName);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("History for: " + workout.getName());

        ArrayList<Workout> listOfCompletedWorkouts = workoutLog.getCompletedWorkoutsByName(workout.getName());

        WorkoutHistoryAdapter workoutHistoryAdapter = new WorkoutHistoryAdapter(this, listOfCompletedWorkouts);
        ListView historyListView = (ListView) findViewById(R.id.workout_history_list);
        historyListView.setAdapter(workoutHistoryAdapter);
    }
}
