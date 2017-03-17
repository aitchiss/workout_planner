package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Gson gson = new Gson();
        SharedPreferences sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
//        AppHistory appHistory = new AppHistory();
//        appHistory.setup(sharedPref);
        SharedPreferences.Editor editor = sharedPref.edit();

        String retrievedWorkoutLog = sharedPref.getString("WorkoutLog", "Nothing here");

        if (retrievedWorkoutLog.equals("Nothing here")){
            workoutLog = new WorkoutLog();
            editor.putString("WorkoutLog", gson.toJson(workoutLog));
            editor.apply();
        } else {
            TypeToken<WorkoutLog> workoutLogType = new TypeToken<WorkoutLog>(){};
            workoutLog = gson.fromJson(retrievedWorkoutLog, workoutLogType.getType());
        }
        

        ArrayList<Workout> list = workoutLog.getAllWorkoutTemplates();

//        save the new version of the log
        editor.putString("WorkoutLog", gson.toJson(workoutLog));
        editor.apply();


        WorkoutListAdapter workoutListAdapter = new WorkoutListAdapter(this, list);
        ListView listView = (ListView) findViewById(R.id.all_workouts_list);
        listView.setAdapter(workoutListAdapter);
    }

    public void onViewButtonClick(View button){

        Button viewButton = (Button) button;
        Workout workoutSelected = (Workout) viewButton.getTag();
        String workoutName = workoutSelected.getName();

        Intent intent = new Intent(this, ViewWorkoutActivity.class);
        intent.putExtra("workout", workoutName);

        startActivity(intent);

    }


}
