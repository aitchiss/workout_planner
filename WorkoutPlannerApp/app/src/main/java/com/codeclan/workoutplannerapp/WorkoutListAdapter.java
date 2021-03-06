package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by user on 16/03/2017.
 */

public class WorkoutListAdapter extends ArrayAdapter<WorkoutTemplate> {

    public WorkoutListAdapter(Context context, ArrayList<WorkoutTemplate> workouts){
        super(context, 0, workouts);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent){

        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.workout_list_item, parent,false);
        }

        WorkoutTemplate currentWorkout = getItem(position);

        TextView workoutName = (TextView) listItemView.findViewById(R.id.workout_name);
        workoutName.setText(currentWorkout.getName());
        workoutName.setTag(currentWorkout);

        TextView lastCompletedDate = (TextView) listItemView.findViewById(R.id.last_completed_date);
        lastCompletedDate.setText("Last completed: " + currentWorkout.getTemplateLastUsedDate());


        return listItemView;
    }

}
