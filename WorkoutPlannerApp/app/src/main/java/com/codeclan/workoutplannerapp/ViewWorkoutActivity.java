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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workout);


        Gson gson = new Gson();
        SharedPreferences sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        String retrievedLog = sharedPref.getString("WorkoutLog", "Nothing here");

        TypeToken<WorkoutLog> workoutLogTypeToken = new TypeToken<WorkoutLog>(){};
        workoutLog = gson.fromJson(retrievedLog, workoutLogTypeToken.getType());


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String selectedWorkoutName = extras.getString("workout");


        workout = workoutLog.getWorkoutTemplate(selectedWorkoutName);

        TextView listingView = (TextView) findViewById(R.id.workout_name);
        listingView.setText(workout.getName());

        ArrayList<Set> listOfSets = workout.getAllSets();

        WorkoutContentsAdapter workoutContentsAdapter = new WorkoutContentsAdapter(this, listOfSets);
        ListView listView = (ListView) findViewById(R.id.workout_contents);

        listView.setAdapter(workoutContentsAdapter);
    }

    public void onEditButtonClick(View button){
        String selectedWorkout = workout.getName();
        Intent intent = new Intent(this, EditWorkoutActivity.class);
        intent.putExtra("workout", selectedWorkout);
        startActivity(intent);
    }
}
