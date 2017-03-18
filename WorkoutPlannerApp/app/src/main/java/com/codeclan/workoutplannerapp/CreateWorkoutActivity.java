package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class CreateWorkoutActivity extends AppCompatActivity {

    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);

        Gson gson = new Gson();
        SharedPreferences sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        String retrievedLog = sharedPref.getString("WorkoutLog", "Nothing here");

        TypeToken<WorkoutLog> workoutLogTypeToken = new TypeToken<WorkoutLog>(){};
        workoutLog = gson.fromJson(retrievedLog, workoutLogTypeToken.getType());

//        ArrayList<Activity> allActivities = Activity.getAllActivities();
//
//        ListAvailableActivitiesAdapter listAvailableActivitiesAdapter = new ListAvailableActivitiesAdapter(this, allActivities);
//        ListView listView = (ListView) findViewById(R.id.available_activities_list);
//        listView.setAdapter(listAvailableActivitiesAdapter);
    }


    public void onAddSetButtonClick(View button){

        Button viewButton = (Button) button;
        Activity selectedActivity = (Activity) viewButton.getTag();
        String activityName = selectedActivity.toString();

        Intent intent = new Intent(this, AddSetActivity.class);
        intent.putExtra("activity", activityName);
        startActivity(intent);
    }

    public void onSaveButtonClick(View view){

        EditText workoutNameInput = (EditText) findViewById(R.id.workout_name_input);
        String workoutName = workoutNameInput.getText().toString();
        Workout workout = new Workout(workoutName);
        workoutLog.addWorkoutTemplate(workout);

        Gson gson = new Gson();
        SharedPreferences sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("WorkoutLog", gson.toJson(workoutLog));
        editor.apply();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
