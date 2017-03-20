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

public class WorkoutContentsAdapter extends ArrayAdapter<String> {

    public WorkoutContentsAdapter(Context context, ArrayList<String> setDetails){
        super(context, 0, setDetails);

    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent){

        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.workout_contents_list_item, parent,false);
        }

        String currentSet = getItem(position);


        TextView setActivity = (TextView) listItemView.findViewById(R.id.set_activity_name);
        setActivity.setText(currentSet);

        ImageView minusSetButton = (ImageView) listItemView.findViewById(R.id.minus_set_button);
        minusSetButton.setTag(currentSet);

        return listItemView;
    }
}
