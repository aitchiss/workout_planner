package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";
    AppHistory appHistory;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        appHistory = new AppHistory();
        workoutLog = appHistory.setup(sharedPref);

        ArrayList<WorkoutTemplate> list = workoutLog.getAllWorkoutTemplates();

        appHistory.updateLog(sharedPref, workoutLog);

        WorkoutListAdapter workoutListAdapter = new WorkoutListAdapter(this, list);
        ListView listView = (ListView) findViewById(R.id.all_workouts_list);
        listView.setAdapter(workoutListAdapter);
    }


    public void onViewButtonClick(View view){
        TextView viewWorkout = (TextView) view;
        WorkoutTemplate workoutSelected = (WorkoutTemplate) viewWorkout.getTag();
        String workoutName = workoutSelected.getName();

        Intent intent = new Intent(this, ViewWorkoutActivity.class);
        intent.putExtra("workout", workoutName);

        startActivity(intent);
    }

    public void createNewWorkoutClick(View button){
        Intent intent = new Intent(this, CreateWorkoutActivity.class);
        startActivity(intent);
    }


}
