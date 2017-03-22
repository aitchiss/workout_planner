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

    public WorkoutLog setup(SharedPreferences sharedPref){

        Gson gson = new Gson();
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

        return workoutLog;
    }


    public void updateLog(SharedPreferences sharedPref, WorkoutLog log){
        Gson gson = new Gson();
        this.workoutLog = log;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("WorkoutLog", gson.toJson(workoutLog));
        editor.apply();
    }

    public int setupID(SharedPreferences sharedPref){
        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPref.edit();

        String retrievedIDLog = sharedPref.getString("IDHistory", "Nothing here");
        if (retrievedIDLog.equals("Nothing here")){
            editor.putString("IDHistory", "1");
            editor.apply();
            return 1;
        } else {
            TypeToken<String> IDType = new TypeToken<String>(){};
            String lastID = gson.fromJson(retrievedIDLog, IDType.getType());
            return Integer.valueOf(lastID);
        }
    }

    public void updateIdLog(SharedPreferences sharedPref, Integer latestID){
        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("IDHistory", gson.toJson(String.valueOf(latestID)));
        editor.apply();
    }


}
