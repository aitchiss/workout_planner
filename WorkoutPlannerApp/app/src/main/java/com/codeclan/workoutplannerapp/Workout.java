package com.codeclan.workoutplannerapp;



import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by user on 16/03/2017.
 */

public class Workout {

    private String name;
    private ArrayList<Set> sets;
    private Date completedDate;
    private int id;


    public Workout(WorkoutTemplate workoutTemplate){
        this.id = workoutTemplate.getId();
        this.name = workoutTemplate.getName();
        this.sets = new ArrayList<Set>();
        for (Set set : workoutTemplate.getAllSets()){
            Integer reps = set.getReps();
            Integer weight = set.getWeight();
            if (set.getActivityType() == null){
                String activity = set.getActivity();
                Set setToAdd = new Set(activity, reps, weight);
                this.sets.add(setToAdd);
            } else{
                Activity activity = set.getActivityType();
                Set setToAdd = new Set(activity, reps, weight);
                this.sets.add(setToAdd);
            }
        }
    }

    public int getId(){
        return this.id;
    }

    public void addSet(Set set){
        this.sets.add(set);
    }

    public String getName() {
        return name;
    }

    public Set getSet(int index){
        return this.sets.get(index);
    }

    public ArrayList<Set> getAllSets(){
        return this.sets;
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


    public ArrayList<String> getSetDetailsConciseForm(){
        ArrayList<String> setDetails = new ArrayList<String>();
        for (Set set: getAllSets()){
            String stringToAdd = set.getActivity() + ": " + set.getReps() + "reps x " + set.getWeight() + "kg";
            setDetails.add(stringToAdd);
        }
        ArrayList<String> conciseList = new ArrayList<String>();

        for (String string : setDetails){
            int occurences = Collections.frequency(setDetails, string);
            String newString = occurences + " x " + string;
            if (!conciseList.contains(newString)){
                conciseList.add(newString);
            }
        }
        return conciseList;
    }

    public String getWorkoutPerformance(){
        String performanceInfo = "";
        for (String setInfo : getSetDetailsConciseForm()){
            performanceInfo += setInfo + "\n";
        }

        return performanceInfo;
    }

    public String getActivityFromConciseSetDetails(String setDetails){
        String stringWithNoOfSetsRemoved = setDetails.substring(setDetails.indexOf("x"));
        int colonIndex = setDetails.indexOf(":");
        int endIndex = (colonIndex - 2);
        String stringWithRepsRemoved = stringWithNoOfSetsRemoved.substring(2, endIndex);

        return stringWithRepsRemoved;
    }


}
