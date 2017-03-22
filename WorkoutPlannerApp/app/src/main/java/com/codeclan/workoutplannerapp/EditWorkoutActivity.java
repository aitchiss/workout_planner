package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class EditWorkoutActivity extends AppCompatActivity {

    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";
    WorkoutTemplate workout;
    AppHistory appHistory;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);

        sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        appHistory = new AppHistory();
        workoutLog = appHistory.setup(sharedPref);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int selectedWorkoutId = extras.getInt("workout");

        workout = workoutLog.getWorkoutTemplate(selectedWorkoutId);

        setupActionBar();
        populateActivitiesList();
    }

    public void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add sets to: " + workout.getName());
    }

    public void populateActivitiesList(){
        ArrayList<Activity> allActivities = Activity.getAllActivities();

        ListAvailableActivitiesAdapter listAvailableActivitiesAdapter =
                new ListAvailableActivitiesAdapter(this, allActivities);
        ListView activitiesList = (ListView) findViewById(R.id.available_activities_list);
        activitiesList.setAdapter(listAvailableActivitiesAdapter);
    }

    public void onAddSetButtonClick(View view){
        String activityName = getActivityName(view);

        Intent intent = new Intent(this, AddSetActivity.class);
        intent.putExtra("activity", activityName);
        intent.putExtra("workout", workout.getId());
        startActivity(intent);
    }

    public String getActivityName(View view){
        TextView addSetText = (TextView) view;
        Activity selectedActivity = (Activity) addSetText.getTag();
        return selectedActivity.toString();
    }

    public void addCustomSetClick(View button){
        Intent intent = new Intent(this, AddCustomSetActivity.class);
        intent.putExtra("workout", workout.getId());
        startActivity(intent);
    }




}

