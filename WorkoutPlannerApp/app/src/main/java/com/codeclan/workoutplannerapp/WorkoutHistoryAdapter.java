package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 18/03/2017.
 */

public class WorkoutHistoryAdapter extends ArrayAdapter<Workout>{

    public WorkoutHistoryAdapter(Context context, ArrayList<Workout> workouts){
        super(context, 0, workouts);

    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent){

        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.workout_history_list_item, parent,false);
        }

        Workout currentWorkout = getItem(position);

        TextView dateEntry = (TextView) listItemView.findViewById(R.id.workout_completed_date);
        dateEntry.setText(currentWorkout.getCompletedDate());

        return listItemView;
    }
}
