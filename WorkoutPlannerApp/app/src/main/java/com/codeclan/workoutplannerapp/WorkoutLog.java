package com.codeclan.workoutplannerapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by user on 16/03/2017.
 */

public class WorkoutLog {

    private ArrayList<WorkoutTemplate> workoutTemplates;
    private Workout currentWorkout;
    private Set currentSet;
    private ArrayList<Workout> completedWorkouts;
    private ArrayList<WorkoutType> allWorkouts;


    public WorkoutLog(){

        this.workoutTemplates = new ArrayList<WorkoutTemplate>();
        this.completedWorkouts = new ArrayList<Workout>();
        this.allWorkouts = new ArrayList<WorkoutType>();
    }

    public void addToAllWorkouts(WorkoutType workout){
        this.allWorkouts.add(workout);
    }

    public ArrayList<WorkoutType> getAllWorkoutTypes(){
        return this.allWorkouts;
    }

    public void addWorkoutTemplate(WorkoutTemplate workoutTemplate){
        this.workoutTemplates.add(0, workoutTemplate);
    }

    public WorkoutTemplate getWorkoutTemplate(String workoutName){
        for (WorkoutTemplate workoutTemplate : workoutTemplates){
            if (workoutTemplate.getName().equals(workoutName)){
                return workoutTemplate;
            }
        }
        return null;
    }

    public WorkoutTemplate getWorkoutTemplate(int workoutId){
        for (WorkoutTemplate workoutTemplate : workoutTemplates){
            if (workoutTemplate.getId() == workoutId){
                return workoutTemplate;
            }
        }
        return null;
    }

    public ArrayList<WorkoutTemplate> getAllWorkoutTemplates(){
        return this.workoutTemplates;
    }

    public Workout getCurrentWorkout() {
        return currentWorkout;
    }

    public void startWorkout(int workoutId){
        WorkoutTemplate workoutTemplate = getWorkoutTemplate(workoutId);
        workoutTemplate.templateUpdateLastUsedDate();

        this.workoutTemplates.remove(workoutTemplate);
        this.workoutTemplates.add(0, workoutTemplate);

        Workout newWorkout = new Workout(workoutTemplate);

        currentWorkout = newWorkout;
        currentSet = currentWorkout.getSet(0);
    }


    public Set getCurrentSet() {
        return currentSet;
    }

    public ArrayList<Workout> getCompletedWorkouts() {
        return completedWorkouts;
    }


    public ArrayList<Workout> getCompletedWorkoutsById(int workoutId){
        ArrayList<Workout> matchingWorkouts = new ArrayList<Workout>();
        for (Workout workout : this.completedWorkouts){
            if (workout.getId() == workoutId){
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


    public void deleteWorkoutTemplate(WorkoutTemplate workoutTemplate){
        this.workoutTemplates.remove(workoutTemplate);
    }

    public String getCurrentSetProgress(String activityName){
        int similarSets = this.currentWorkout.getNumberOfSimilarSets(activityName);
        ArrayList<Set> positions = new ArrayList<Set>();
        for (Set set: this.currentWorkout.getAllSets()){
            if (set.getActivity().equals(activityName)){
                positions.add(set);
            }
        }
        int currentProgress = positions.indexOf(this.currentSet) + 1;
        String progress = currentProgress + " of " + similarSets;
        return progress;
    }
}
