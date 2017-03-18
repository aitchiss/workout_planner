package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AddSetActivity extends AppCompatActivity {

    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";
    Workout workout;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_set);

        Gson gson = new Gson();
        SharedPreferences sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        String retrievedLog = sharedPref.getString("WorkoutLog", "Nothing here");

        TypeToken<WorkoutLog> workoutLogTypeToken = new TypeToken<WorkoutLog>(){};
        workoutLog = gson.fromJson(retrievedLog, workoutLogTypeToken.getType());

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String selectedWorkoutName = extras.getString("workout");
        workout = workoutLog.getWorkoutTemplate(selectedWorkoutName);

        String selectedActivityName = extras.getString("activity");
        activity = Activity.valueOf(selectedActivityName);

        TextView pageTitle = (TextView) findViewById(R.id.add_set_title);
        pageTitle.setText("add to workout: " + workout.getName());

        TextView activityName = (TextView) findViewById(R.id.add_set_activity_name);
        activityName.setText(selectedActivityName.toLowerCase());
    }

    public void confirmAddSetButtonClick(View button){

        EditText numberOfSetsInput = (EditText) findViewById(R.id.choose_set_number);
        Integer numberOfSets = Integer.valueOf(numberOfSetsInput.getText().toString());

        EditText numberOfRepsInput = (EditText) findViewById(R.id.choose_reps_number);
        Integer numberOfReps = Integer.valueOf(numberOfRepsInput.getText().toString());

        EditText numberOfWeightInput = (EditText) findViewById(R.id.choose_weight_number);
        Integer numberOfWeight = Integer.valueOf(numberOfWeightInput.getText().toString());

        workout.addMultipleSets(activity, numberOfReps, numberOfWeight, numberOfSets);

        Gson gson = new Gson();
        SharedPreferences sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("WorkoutLog", gson.toJson(workoutLog));
        editor.apply();

        Intent intent = new Intent(this, EditWorkoutActivity.class);
        intent.putExtra("workout", workout.getName());
        startActivity(intent);
    }
}
