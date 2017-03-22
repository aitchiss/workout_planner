package com.codeclan.workoutplannerapp;

/**
 * Created by user on 16/03/2017.
 */

public class Set {

    private Activity activity;
    private String customActivity;
    private Integer reps;
    private Integer weight;

    public Set(Activity activity, Integer reps, Integer weight){
        this.activity = activity;
        this.reps = reps;
        this.weight = weight;
    }

    public Set(String activity, Integer reps, Integer weight){
        this.customActivity = activity;
        this.reps = reps;
        this.weight = weight;
    }

    public String getActivity(){
        if (this.activity == null){
            return this.customActivity;
        }
        return this.activity.toString();
    }

    public Integer getReps() {
        return reps;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void updateSet(Integer reps, Integer weight){
        setReps(reps);
        setWeight(weight);
    }

    public Activity getActivityType(){
        return this.activity;
    }
}
