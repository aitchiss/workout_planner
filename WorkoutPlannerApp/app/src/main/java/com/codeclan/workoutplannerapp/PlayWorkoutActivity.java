package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PlayWorkoutActivity extends AppCompatActivity {

    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";
    Workout workout;
    Set set;
    AppHistory appHistory;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_workout);

        sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        appHistory = new AppHistory();
        workoutLog = appHistory.setup(sharedPref);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int selectedWorkoutId = extras.getInt("workout");

        workoutLog.startWorkout(selectedWorkoutId);
        workout = workoutLog.getCurrentWorkout();
        set = workoutLog.getCurrentSet();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("In progress: " + workout.getName());

        displayCurrentActivity();
        displaySetProgress();
        displayNumberOfReps();
        displayWeight();
    }

    public void displayCurrentActivity(){
        TextView currentActivity = (TextView) findViewById(R.id.current_activity_name);
        currentActivity.setText(set.getActivity());
    }

    public void displayWeight(){
        EditText weight = (EditText) findViewById(R.id.current_weight);
        weight.setText(set.getWeight().toString());
    }

    public void displayNumberOfReps(){
        EditText numberOfReps = (EditText) findViewById(R.id.current_reps);
        numberOfReps.setText(set.getReps().toString());
    }


    public void displaySetProgress(){
        TextView currentSetNumber = (TextView) findViewById(R.id.current_set_number);
        currentSetNumber.setText(workoutLog.getCurrentSetProgress(set.getActivity()));
    }

    public void finishSetButtonClicked(View button){
        EditText numberOfRepsInput = (EditText) findViewById(R.id.current_reps);
        String numberOfRepsAchieved = numberOfRepsInput.getText().toString();
        Integer numberOfReps = Integer.valueOf(numberOfRepsAchieved);

        EditText weightInput = (EditText) findViewById((R.id.current_weight));
        String weightAchieved = weightInput.getText().toString();
        Integer actualWeight = Integer.valueOf(weightAchieved);

        workoutLog.finishCurrentSet(numberOfReps, actualWeight);
        appHistory.updateLog(sharedPref, workoutLog);

        if (workoutLog.getCurrentSet() == null){

            Toast.makeText(this, "Workout Finished", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ViewWorkoutActivity.class);
            intent.putExtra("workout", workout.getId());
            startActivity(intent);

        } else {

            set = workoutLog.getCurrentSet();
            displaySetProgress();
            displayNumberOfReps();
            displayWeight();
            displayCurrentActivity();
        }
    }
}
