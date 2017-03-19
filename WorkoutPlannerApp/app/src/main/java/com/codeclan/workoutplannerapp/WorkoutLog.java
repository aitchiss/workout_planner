package com.codeclan.workoutplannerapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by user on 16/03/2017.
 */

public class WorkoutLog {

    private ArrayList<Workout> workoutTemplates;
    private Workout currentWorkout;
    private Set currentSet;
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
        currentSet = currentWorkout.getSet(0);
    }

    public Set getCurrentSet() {
        return currentSet;
    }

    public ArrayList<Workout> getCompletedWorkouts() {
        return completedWorkouts;
    }

    public ArrayList<Workout> getCompletedWorkoutsByName(String workoutName){
        ArrayList<Workout> matchingWorkouts = new ArrayList<Workout>();
        for (Workout workout : this.completedWorkouts){
            if (workout.getName().equals(workoutName)){
                matchingWorkouts.add(workout);
            }
        }
        Collections.reverse(matchingWorkouts);
        return matchingWorkouts;
    }

    public void finishCurrentWorkout(){
        this.currentWorkout.markComplete();
        addToCompletedWorkouts(this.currentWorkout);
        this.currentWorkout = null;
        this.currentSet = null;
    }

    public void addToCompletedWorkouts(Workout workout){
        this.completedWorkouts.add(workout);
    }

    public void updateCurrentSet(Integer reps, Integer weight){
        this.currentSet.setReps(reps);
        this.currentSet.setWeight(weight);
    }

    public void finishCurrentSet(Integer reps, Integer weight){
        updateCurrentSet(reps, weight);

        if (anotherSetRemaining() == true){
            int currentSetIndex = currentWorkout.getAllSets().indexOf(this.currentSet);
            int newSetIndex = currentSetIndex + 1;
            currentSet = currentWorkout.getSet(newSetIndex);
        } else {
            finishCurrentWorkout();
        }

    }

    public Boolean anotherSetRemaining(){
        int currentSetIndex = currentWorkout.getAllSets().indexOf(this.currentSet);
        int newSetIndex = currentSetIndex + 1;
        int numberOfSetsInWorkout = currentWorkout.getAllSets().size();
        if (newSetIndex >= numberOfSetsInWorkout){
            return false;
        } else {
            return true;
        }
    }
}
