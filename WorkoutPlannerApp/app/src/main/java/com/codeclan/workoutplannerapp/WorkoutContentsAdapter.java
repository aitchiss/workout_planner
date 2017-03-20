package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 18/03/2017.
 */

public class WorkoutContentsAdapter extends ArrayAdapter<Set> {

    public WorkoutContentsAdapter(Context context, ArrayList<Set> setDetails){
        super(context, 0, setDetails);

    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent){

        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.workout_contents_list_item, parent,false);
        }

        Set currentSet = getItem(position);


        TextView setActivity = (TextView) listItemView.findViewById(R.id.set_activity_name);
        setActivity.setText(currentSet.getActivity() + ": ");

        TextView setReps = (TextView) listItemView.findViewById(R.id.set_reps_number);
        setReps.setText(currentSet.getReps().toString() + " reps  x ");

        TextView setWeight = (TextView) listItemView.findViewById(R.id.set_weight_number);
        setWeight.setText(currentSet.getWeight().toString() + "kg");

        ImageView minusSetButton = (ImageView) listItemView.findViewById(R.id.minus_set_button);
        minusSetButton.setTag(currentSet);

        ImageView plusSetButton = (ImageView) listItemView.findViewById(R.id.plus_set_button);
        plusSetButton.setTag(currentSet);

        return listItemView;
    }
}
