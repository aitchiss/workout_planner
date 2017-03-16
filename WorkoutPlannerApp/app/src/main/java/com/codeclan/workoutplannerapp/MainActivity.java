package com.codeclan.workoutplannerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //do I do something here to recall persisted saved data, and then add it back into a
        //workout log? Would need to all be handled in WorkoutLog class.
        //e.g. restoreWorkoutTemplates, restoreCompletedWorkouts.

        WorkoutLog workoutLog = new WorkoutLog();
        //below just to test adapter/display
        Workout workout = new Workout("test workout");
        workout.addMultipleSets(Activity.LUNGES, 10, 25, 3);
        workoutLog.addWorkoutTemplate(workout);
        ArrayList<Workout> list = workoutLog.getAllWorkoutTemplates();

        WorkoutListAdapter workoutListAdapter = new WorkoutListAdapter(this, list);
        ListView listView = (ListView) findViewById(R.id.all_workouts_list);
        listView.setAdapter(workoutListAdapter);
    }
}
