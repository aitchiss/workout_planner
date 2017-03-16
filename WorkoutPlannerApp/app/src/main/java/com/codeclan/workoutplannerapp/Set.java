package com.codeclan.workoutplannerapp;

/**
 * Created by user on 16/03/2017.
 */

public class Set {

    private Activity activity;
    private int reps;
    private int weight;

    public Set(Activity activity, int reps, int weight){
        this.activity = activity;
        this.reps = reps;
        this.weight = weight;
    }

    public String getActivity(){
        return this.activity.toString().toLowerCase();
    }

    public int getReps() {
        return reps;
    }

    public int getWeight() {
        return weight;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void updateSet(int reps, int weight){
        setReps(reps);
        setWeight(weight);
    }
}
