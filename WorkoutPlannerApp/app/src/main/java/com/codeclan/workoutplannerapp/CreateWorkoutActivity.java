package com.codeclan.workoutplannerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class CreateWorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);

        ArrayList<Activity> allActivities = Activity.getAllActivities();

        ListAvailableActivitiesAdapter listAvailableActivitiesAdapter = new ListAvailableActivitiesAdapter(this, allActivities);
        ListView listView = (ListView) findViewById(R.id.available_activities_list);
        listView.setAdapter(listAvailableActivitiesAdapter);
    }
}
