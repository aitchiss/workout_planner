package com.codeclan.workoutplannerapp;

import java.util.ArrayList;

/**
 * Created by user on 16/03/2017.
 */

public enum Activity {

    BENCHPRESS,
    DEADLIFT,
    LUNGES,
    SHOULDERPRESS,
    SQUAT;

   public static ArrayList<Activity> getAllActivities(){
       ArrayList<Activity> allActivities = new ArrayList<Activity>();
       for (Activity activity : Activity.values()){
           allActivities.add(activity);
       }

       return allActivities;
   }
}


