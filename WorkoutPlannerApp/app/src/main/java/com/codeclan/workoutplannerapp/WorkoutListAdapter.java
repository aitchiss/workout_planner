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

public class WorkoutListAdapter extends ArrayAdapter<Workout> {

    public WorkoutListAdapter(Context context, ArrayList<Workout> workouts){
        super(context, 0, workouts);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent){

        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.workout_list_item, parent,false);
        }

        Workout currentWorkout = getItem(position);


        TextView workoutName = (TextView) listItemView.findViewById(R.id.workout_name);
        workoutName.setText(currentWorkout.getName());

//        Button viewWorkoutButton = (Button) listItemView.findViewById(R.id.view_workout_button);
//        viewWorkoutButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//                public void onClick(View view){
//                Intent intent = new Intent(this, ViewWorkoutActivity.class);
//                intent.putExtra("workout", currentWorkout.getName());
//                startActivity(intent);
//            }
//        });

//        TextView lastCompletedDate = ??

        return listItemView;
    }

}
