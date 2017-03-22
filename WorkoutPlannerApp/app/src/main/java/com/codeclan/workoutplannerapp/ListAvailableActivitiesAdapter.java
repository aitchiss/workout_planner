package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 17/03/2017.
 */

public class ListAvailableActivitiesAdapter extends ArrayAdapter<Activity> {

    public ListAvailableActivitiesAdapter(Context context, ArrayList<Activity> activities){
        super(context, 0, activities);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent){

        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_item, parent,false);
        }

        Activity currentActivity = getItem(position);

        TextView activityName = (TextView) listItemView.findViewById(R.id.activity_name);
        activityName.setText(currentActivity.toString());
        activityName.setTag(currentActivity);

        return listItemView;
    }
}
