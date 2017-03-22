package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class CreateWorkoutActivity extends AppCompatActivity {

    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";
    public static final String IDHISTORY = "IDHistory";
    AppHistory appHistory;
    SharedPreferences sharedPref;
    SharedPreferences sharedPrefID;
    private static AtomicInteger nextId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);

        sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        sharedPrefID = getSharedPreferences(IDHISTORY, Context.MODE_PRIVATE);
        appHistory = new AppHistory();
        workoutLog = appHistory.setup(sharedPref);

        nextId = new AtomicInteger();
        nextId.set(appHistory.setupID(sharedPrefID));

        setupActionBar();
    }

    public void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Create new workout");
    }

    public void onSaveButtonClick(View view){

        String workoutName = getWorkoutName();
        int nextId = getIDAndUpdateLog();

        int workoutID = storeNewWorkoutTemplateAndReturnID(workoutName, nextId);

        Intent intent = new Intent(this, ViewWorkoutActivity.class);
        intent.putExtra("workout", workoutID);
        startActivity(intent);
    }

    public int storeNewWorkoutTemplateAndReturnID(String name, int id){
        WorkoutTemplate workout = new WorkoutTemplate(name, id);
        int workoutId = workout.getId();

        workoutLog.addWorkoutTemplate(workout);
        appHistory.updateLog(sharedPref, workoutLog);

        return workoutId;
    }

    public int getIDAndUpdateLog(){
        int id = nextId.incrementAndGet();
        appHistory.updateIdLog(sharedPrefID, id);
        return id;
    }

    public String getWorkoutName(){
        EditText workoutNameInput = (EditText) findViewById(R.id.workout_name_input);
        String workoutName = workoutNameInput.getText().toString();

        if (workoutName.equals("")){
            return"my workout";
        } else {
            return workoutName;
        }
    }
}
