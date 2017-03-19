package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ViewWorkoutActivity extends AppCompatActivity {

    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";
    Workout workout;
    AppHistory appHistory;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workout);

        sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        appHistory = new AppHistory();
        workoutLog = appHistory.setup(sharedPref);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String selectedWorkoutName = extras.getString("workout");

        workout = workoutLog.getWorkoutTemplate(selectedWorkoutName);

        TextView listingView = (TextView) findViewById(R.id.workout_name);
        listingView.setText(workout.getName());

        ArrayList<String> listOfSetDetails = workout.getSetDetailsConciseForm();
        WorkoutContentsAdapter workoutContentsAdapter = new WorkoutContentsAdapter(this, listOfSetDetails);
        ListView listView = (ListView) findViewById(R.id.workout_contents);
        listView.setAdapter(workoutContentsAdapter);

        ArrayList<Workout> listOfCompletedWorkouts = workoutLog.getCompletedWorkoutsByName(workout.getName());

        WorkoutHistoryAdapter workoutHistoryAdapter = new WorkoutHistoryAdapter(this, listOfCompletedWorkouts);
        ListView historyListView = (ListView) findViewById(R.id.workout_history_list);
        historyListView.setAdapter(workoutHistoryAdapter);
    }

    public void onEditButtonClick(View button){
        String selectedWorkout = workout.getName();
        Intent intent = new Intent(this, EditWorkoutActivity.class);
        intent.putExtra("workout", selectedWorkout);
        startActivity(intent);
    }

    public void onStartButtonClick(View button){
        workoutLog.startWorkout(workout.getName());
        appHistory.updateLog(sharedPref, workoutLog);

        Intent intent = new Intent(this, PlayWorkoutActivity.class);
        startActivity(intent);
    }
}
