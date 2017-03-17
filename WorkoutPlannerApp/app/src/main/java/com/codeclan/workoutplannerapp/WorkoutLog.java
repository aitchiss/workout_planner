package com.codeclan.workoutplannerapp;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by user on 16/03/2017.
 */

public class WorkoutLog {

    private ArrayList<Workout> workoutTemplates;
    private Workout currentWorkout;
    private ArrayList<Workout> completedWorkouts;



    public WorkoutLog(){

        this.workoutTemplates = new ArrayList<Workout>();
        this.completedWorkouts = new ArrayList<Workout>();
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

    public ArrayList<Workout> getAllWorkoutTemplates(){
        return this.workoutTemplates;
    }

    public Workout getCurrentWorkout() {
        return currentWorkout;
    }

    public void startWorkout(String workoutName){
        Workout workout = getWorkoutTemplate(workoutName);
        Workout newWorkout = new Workout(workout.getName());

        for (Set set : workout.getAllSets()){
            Activity activity = set.getActivityType();
            int reps = set.getReps();
            int weight = set.getWeight();
            Set newSet = new Set(activity, reps, weight);
            newWorkout.addSet(newSet);
        }

        currentWorkout = newWorkout;
    }

    public ArrayList<Workout> getCompletedWorkouts() {
        return completedWorkouts;
    }

    public void finishCurrentWorkout(){
        this.currentWorkout.markComplete();
        addToCompletedWorkouts(this.currentWorkout);
        this.currentWorkout = null;
    }

    public void addToCompletedWorkouts(Workout workout){
        this.completedWorkouts.add(workout);
    }
}
