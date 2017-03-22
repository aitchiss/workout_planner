package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditNameActivity extends AppCompatActivity {


    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";
    WorkoutTemplate workout;
    AppHistory appHistory;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);

        sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        appHistory = new AppHistory();
        workoutLog = appHistory.setup(sharedPref);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int selectedWorkoutId = extras.getInt("workout");
        workout = workoutLog.getWorkoutTemplate(selectedWorkoutId);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("edit name: " + workout.getName());

        EditText nameInputField = (EditText) findViewById(R.id.workout_name_input);
        nameInputField.setText(workout.getName());
    }

    public void onSaveButtonClick(View button){
        EditText workoutNameInput = (EditText) findViewById(R.id.workout_name_input);
        String workoutName = workoutNameInput.getText().toString();

        if (!workoutName.equals((""))){
            workout.setName(workoutName);
        }

        appHistory.updateLog(sharedPref, workoutLog);

        Intent intent = new Intent(this, ViewWorkoutActivity.class);
        intent.putExtra("workout", workout.getId());
        startActivity(intent);
    }
}
