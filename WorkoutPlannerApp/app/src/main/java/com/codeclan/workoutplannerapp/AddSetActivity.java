package com.codeclan.workoutplannerapp;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
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
    WorkoutTemplate workout;
    Activity activity;
    AppHistory appHistory;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_set);

        sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        appHistory = new AppHistory();
        workoutLog = appHistory.setup(sharedPref);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        int selectedWorkoutId = extras.getInt("workout");
        workout = workoutLog.getWorkoutTemplate(selectedWorkoutId);

        String selectedActivityName = extras.getString("activity");
        activity = Activity.valueOf(selectedActivityName);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Adding to: " + workout.getName());

        TextView activityName = (TextView) findViewById(R.id.add_set_activity_name);
        activityName.setText(selectedActivityName.toLowerCase());
    }

    public void confirmAddSetButtonClick(View button){

        EditText numberOfSetsInput = (EditText) findViewById(R.id.choose_set_number);
        Integer numberOfSets = Integer.valueOf(numberOfSetsInput.getText().toString());

        EditText numberOfRepsInput = (EditText) findViewById(R.id.choose_reps_number);
        Integer numberOfReps = Integer.valueOf(numberOfRepsInput.getText().toString());

        if (numberOfSets.equals(0) || numberOfReps.equals(0)){
            errorDialog();
        } else {
            EditText numberOfWeightInput = (EditText) findViewById(R.id.choose_weight_number);
            Integer numberOfWeight = Integer.valueOf(numberOfWeightInput.getText().toString());

            workout.addMultipleSets(activity, numberOfReps, numberOfWeight, numberOfSets);

            appHistory.updateLog(sharedPref, workoutLog);

            Intent intent = new Intent(this, ViewWorkoutActivity.class);
            intent.putExtra("workout", workout.getId());
            startActivity(intent);
        }

    }

    public void errorDialog(){
        FragmentManager fm = getFragmentManager();
        InputErrorWarning dialogFragment = new InputErrorWarning ();
        setTheme(R.style.DialogWarning);
        dialogFragment.show(fm, "Error");
    }
}
