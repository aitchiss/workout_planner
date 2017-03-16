package com.codeclan.workoutplannerapp;

import java.util.ArrayList;

/**
 * Created by user on 16/03/2017.
 */

public class WorkoutLog {

    private ArrayList<Workout> workoutTemplates;

    public WorkoutLog(){
        this.workoutTemplates = new ArrayList<Workout>();
    }

    public void addWorkoutTemplate(Workout workout){
        this.workoutTemplates.add(workout);
    }

    public Workout getWorkoutTemplate(String workoutName){
        for (Workout workout : workoutTemplates){
            if (workout.getName().equals(workoutName)){
                return workout;
            }
        }
        return null;
    }
}
