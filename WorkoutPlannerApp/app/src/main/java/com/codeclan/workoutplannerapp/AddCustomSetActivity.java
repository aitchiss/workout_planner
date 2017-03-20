package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddCustomSetActivity extends AppCompatActivity {

    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";
    Workout workout;
    AppHistory appHistory;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_set);

        sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        appHistory = new AppHistory();
        workoutLog = appHistory.setup(sharedPref);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String selectedWorkoutName = extras.getString("workout");
        workout = workoutLog.getWorkoutTemplate(selectedWorkoutName);

        TextView pageTitle = (TextView) findViewById(R.id.add_set_title);
        pageTitle.setText("add to workout: " + workout.getName());

    }

    public void addCustomSetButtonClick(View button){

        EditText activity = (EditText) findViewById(R.id.enter_custom_activity);
        String activityName = activity.getText().toString();

        EditText numberOfSetsInput = (EditText) findViewById(R.id.choose_set_number);
        Integer numberOfSets = Integer.valueOf(numberOfSetsInput.getText().toString());

        EditText numberOfRepsInput = (EditText) findViewById(R.id.choose_reps_number);
        Integer numberOfReps = Integer.valueOf(numberOfRepsInput.getText().toString());

        EditText numberOfWeightInput = (EditText) findViewById(R.id.choose_weight_number);
        Integer numberOfWeight = Integer.valueOf(numberOfWeightInput.getText().toString());

        workout.addMultipleSets(activityName, numberOfReps, numberOfWeight, numberOfSets);

        appHistory.updateLog(sharedPref, workoutLog);

        Intent intent = new Intent(this, ViewWorkoutActivity.class);
        intent.putExtra("workout", workout.getName());
        startActivity(intent);
    }
}