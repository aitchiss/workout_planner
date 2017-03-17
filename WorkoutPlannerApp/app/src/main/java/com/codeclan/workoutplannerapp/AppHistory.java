package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by user on 17/03/2017.
 */



public class AppHistory {

    WorkoutLog workoutLog;

    public void setup(SharedPreferences sharedPref){

        Gson gson = new Gson();
//        SharedPreferences sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
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
    }


    public void updateLog(SharedPreferences sharedPref, WorkoutLog newLog){
        Gson gson = new Gson();
        this.workoutLog = newLog;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("WorkoutLog", gson.toJson(workoutLog));
        editor.apply();
    }
}
