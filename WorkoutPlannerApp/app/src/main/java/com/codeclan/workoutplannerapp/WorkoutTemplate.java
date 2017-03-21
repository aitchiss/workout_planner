package com.codeclan.workoutplannerapp;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by user on 21/03/2017.
 */

public class WorkoutTemplate implements WorkoutType {

    private String name;
    private ArrayList<Set> sets;
    private Date templateLastUsedDate;
    private static AtomicInteger nextId = new AtomicInteger();
    private int id;

    public WorkoutTemplate(String name) {
        this.name = name;
        this.sets = new ArrayList<Set>();
        this.templateLastUsedDate = null;
        this.id = nextId.incrementAndGet();
    }

    public int getId(){
        return this.id;
    }

    public Set getSet(int index){
        return this.sets.get(index);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Set> getAllSets(){
        return this.sets;
    }

    public String getTemplateLastUsedDate(){
        if (this.templateLastUsedDate == null){
            return "never";
        } else {
            String formattedDate = DateFormat.getDateInstance().format(this.templateLastUsedDate);
            return formattedDate;
        }
    }

    public void templateUpdateLastUsedDate(){
        this.templateLastUsedDate = new Date();
    }

    public void addSet(Set set){
        this.sets.add(set);
    }

    public void addSetAtIndex(Set set, int index){
        this.sets.add(index, set);
    }

    public void addMultipleSets(Activity activity, int reps, int weight, int number_of_sets){
        for (int i = 0; i < number_of_sets; i++){
            Set set = new Set(activity, reps, weight);
            addSet(set);
        }
    }

    public void addMultipleSets(String activity, int reps, int weight, int number_of_sets){
        for (int i = 0; i < number_of_sets; i++){
            Set set = new Set(activity, reps, weight);
            addSet(set);
        }
    }

    public void deleteSet(Set setToDelete){
        this.sets.remove(setToDelete);
    }

    public void deleteSetByName(String activityName){
        for (Set set: this.sets){
            if (set.getActivity().equals(activityName)){
                deleteSet(set);
                return;
            }
        }
    }

    public void addMultipleWithSuperSet(Set set1, Set set2, int noOfSets){
        for (int i = 0; i < noOfSets; i++){
            Set setMain = new Set(set1.getActivityType(), set1.getReps(), set1.getWeight());
            Set setSuperSet = new Set(set2.getActivityType(), set2.getReps(), set2.getWeight());
            addSet(setMain);
            addSet(setSuperSet);
        }
    }




}
