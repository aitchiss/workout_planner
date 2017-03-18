package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class EditWorkoutActivity extends AppCompatActivity {

    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";
    Workout workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);

        Gson gson = new Gson();
        SharedPreferences sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        String retrievedLog = sharedPref.getString("WorkoutLog", "Nothing here");

        TypeToken<WorkoutLog> workoutLogTypeToken = new TypeToken<WorkoutLog>(){};
        workoutLog = gson.fromJson(retrievedLog, workoutLogTypeToken.getType());


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String selectedWorkoutName = extras.getString("workout");


        workout = workoutLog.getWorkoutTemplate(selectedWorkoutName);

        TextView title = (TextView) findViewById(R.id.workout_to_edit);
        title.setText("edit workout: " + workout.getName());

        ArrayList<Set> listOfSets = workout.getAllSets();

        WorkoutContentsAdapter workoutContentsAdapter = new WorkoutContentsAdapter(this, listOfSets);
        ListView listView = (ListView) findViewById(R.id.workout_contents);

        listView.setAdapter(workoutContentsAdapter);

        ArrayList<Activity> allActivities = Activity.getAllActivities();

        ListAvailableActivitiesAdapter listAvailableActivitiesAdapter = new ListAvailableActivitiesAdapter(this, allActivities);
        ListView activitiesList = (ListView) findViewById(R.id.available_activities_list);
        activitiesList.setAdapter(listAvailableActivitiesAdapter);
    }

    public void onAddSetButtonClick(View button){

        Button viewButton = (Button) button;
        Activity selectedActivity = (Activity) viewButton.getTag();
        String activityName = selectedActivity.toString();

        Intent intent = new Intent(this, AddSetActivity.class);
        intent.putExtra("activity", activityName);
        intent.putExtra("workout", workout.getName());
        startActivity(intent);
    }

    public void onDoneEditingButtonClick(View button){
        Intent intent = new Intent(this, ViewWorkoutActivity.class);
        intent.putExtra("workout", workout.getName());
        startActivity(intent);
    }
}

