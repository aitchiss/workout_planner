package com.codeclan.workoutplannerapp;



import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by user on 16/03/2017.
 */

public class Workout {

    private String name;
    private ArrayList<Set> sets;
    private Date completedDate;


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

    public ArrayList<Set> getAllSets(){
        return this.sets;
    }

    public void addMultipleSets(Activity activity, int reps, int weight, int number_of_sets){
        for (int i = 0; i < number_of_sets; i++){
            Set set = new Set(activity, reps, weight);
            addSet(set);
        }
    }

    public void markComplete(){
        completedDate = new Date();
    }

    public String getCompletedDate(){
        String formattedDate = DateFormat.getDateInstance().format(this.completedDate);
        return formattedDate;
    }

    public int getNumberOfSimilarSets(String activityName){
        int counter = 0;
        for (Set set: getAllSets()){
            if (set.getActivity().equals(activityName)){
                counter++;
            }
        }
        return counter;
    }





}
