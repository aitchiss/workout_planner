package com.codeclan.workoutplannerapp;



import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by user on 16/03/2017.
 */

public class Workout {

    private String name;
    private ArrayList<Set> sets;
    private Date completedDate;
    private Date templateLastUsedDate;


    public Workout(String name){
        this.name = name;
        this.sets = new ArrayList<Set>();
        this.templateLastUsedDate = null;
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

    public void templateUpdateLastUsedDate(){
        this.templateLastUsedDate = new Date();
    }

    public String getTemplateLastUsedDate(){
        if (this.templateLastUsedDate == null){
            return "never";
        } else {
            String formattedDate = DateFormat.getDateInstance().format(this.templateLastUsedDate);
            return formattedDate;
        }
    }

    public String getCompletedDate(){
        String formattedDate = DateFormat.getDateInstance().format(this.completedDate);
        return formattedDate;
    }

    public Date getRawCompletedDate(){
        return this.completedDate;
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


    public String getWorkoutPeformance(){
        String performanceInfo = "";
        for (String setInfo : getSetDetailsConciseForm()){
            performanceInfo += setInfo + "\n";
        }

        return performanceInfo;
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

    public String getActivityFromConciseSetDetails(String setDetails){
        String stringWithNoOfSetsRemoved = setDetails.substring(setDetails.indexOf("x"));
        int colonIndex = setDetails.indexOf(":");
        int endIndex = (colonIndex - 2);
        String stringWithRepsRemoved = stringWithNoOfSetsRemoved.substring(2, endIndex);

        return stringWithRepsRemoved;
    }



    public void deleteSetFromConciseSetDetails(String setDetails){
        String activity = getActivityFromConciseSetDetails(setDetails);
        deleteSetByName(activity);
    }





}
