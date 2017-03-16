package com.codeclan.workoutplannerapp;

import java.util.ArrayList;

/**
 * Created by user on 16/03/2017.
 */

public class Workout {

    private String name;
    private ArrayList<Set> sets;

    public Workout(String name){
        this.name = name;
        this.sets = new ArrayList<Set>();
    }

    public String getName() {
        return name;
    }

    public void addSet(Set set){
        this.sets.add(set);
    }

    public Set getSet(int index){
        return this.sets.get(index);
    }

    public void addMultipleSets(Activity activity, int reps, int weight, int number_of_sets){
        for (int i = 0; i < number_of_sets; i++){
            Set set = new Set(activity, reps, weight);
            addSet(set);
        }
    }
}
