package com.codeclan.workoutplannerapp;

import java.util.ArrayList;

/**
 * Created by user on 16/03/2017.
 */

public enum Activity {

    BACKEXTENSION("back extension"),
    BARBELLROWBENT("barbell row (bent over)"),
    BARBELLROWUPRIGHT("barbell row (upright)"),
    BENCHPRESS("bench press"),
    BENCHPRESSCLOSE("bench press (close grip)"),
    BENCHPRESSINCLINE("bench press (incline)"),
    BICEPCURL("bicep curl"),
    CALFRAISE("calf raise"),
    CLEANANDPRESS("clean and press"),
    DEADLIFT("deadlift"),
    DEADLIFTBULGARIAN("Bulgarian deadlift"),
    DEADLIFTSUMO("sumo deadlift"),
    LUNGES("lunges"),
    OVERHEADPRESSSEATED("overhead press (seated)"),
    OVERHEADHEADPRESSSTANDING("overhead press (standing)"),
    SHRUG("shrug"),
    SQUAT("squat"),
    STEPUP("step up"),
    TRICEPEXTENSION("tricep extension");

    private String stringValue;

    Activity(String value){
        this.stringValue = value;
    }

    public String toString(){
        return this.stringValue;
    }


   public static ArrayList<Activity> getAllActivities(){
       ArrayList<Activity> allActivities = new ArrayList<Activity>();
       for (Activity activity : Activity.values()){
           allActivities.add(activity);
       }

       return allActivities;
   }

   public static Activity getActivityByString(String name){
       for (Activity activity : getAllActivities()){
           if (activity.toString().equals(name)){
               return activity;
           }
       }
       return null;
   }


}


