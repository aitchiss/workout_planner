package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SelectSuperSetActivity extends AppCompatActivity {

    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";
    WorkoutTemplate workout;
    AppHistory appHistory;
    SharedPreferences sharedPref;
    String mainActivity;
    int mainSets;
    int mainReps;
    int mainWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_super_set);

        sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);

        appHistory = new AppHistory();
        workoutLog = appHistory.setup(sharedPref);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int selectedWorkoutId = extras.getInt("workout");
        workout = workoutLog.getWorkoutTemplate(selectedWorkoutId);

        mainActivity = extras.getString("main activity");

        mainSets = extras.getInt("main sets");
        mainReps = extras.getInt("main reps");
        mainWeight = extras.getInt("main weight");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add superset to: " + workout.getName());

        ArrayList<Activity> allActivities = Activity.getAllActivities();

        ListAvailableActivitiesAdapter listAvailableActivitiesAdapter = new ListAvailableActivitiesAdapter(this, allActivities);
        ListView activitiesList = (ListView) findViewById(R.id.available_activities_list);
        activitiesList.setAdapter(listAvailableActivitiesAdapter);
    }

    public void onAddSetButtonClick(View view){
        TextView addSetText = (TextView) view;
        Activity selectedActivity = (Activity) addSetText.getTag();
        String activityName = selectedActivity.toString();

        Intent intent = new Intent(this, AddSuperSetActivity.class);
        intent.putExtra("activity", activityName);
        intent.putExtra("main activity", mainActivity);
        intent.putExtra("main sets", mainSets);
        intent.putExtra("main reps", mainReps);
        intent.putExtra("main weight", mainWeight);
        intent.putExtra("workout", workout.getId());
        startActivity(intent);
    }

    public void addCustomSuperSetClick(View button){
        Intent intent = new Intent(this, AddCustomSuperSetActivity.class);
        intent.putExtra("main activity", mainActivity);
        intent.putExtra("main sets", mainSets);
        intent.putExtra("main reps", mainReps);
        intent.putExtra("main weight", mainWeight);
        intent.putExtra("workout", workout.getId());
        startActivity(intent);
    }
}
