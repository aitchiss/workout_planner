package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 18/03/2017.
 */

public class WorkoutHistoryAdapter extends ArrayAdapter<Workout>{

    Context context;

    public WorkoutHistoryAdapter(Context context, ArrayList<Workout> workouts){
        super(context, 0, workouts);
        this.context = context;

    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent){

        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.workout_history_list_item, parent,false);
        }

        Workout currentWorkout = getItem(position);

        TextView dateEntry = (TextView) listItemView.findViewById(R.id.workout_completed_date);
        dateEntry.setText(currentWorkout.getCompletedDate());

        ListView historySetDetailsList = (ListView) listItemView.findViewById(R.id.workout_contents);
        ArrayList<Set> setHistoryList = currentWorkout.getAllSets();

        WorkoutContentsAdapter workoutContentsAdapter = new WorkoutContentsAdapter(context, setHistoryList);
        historySetDetailsList.setAdapter(workoutContentsAdapter);

        return listItemView;
    }
}
