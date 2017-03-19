package com.codeclan.workoutplannerapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PlayWorkoutActivity extends AppCompatActivity {

    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";
    Workout workout;
    Set set;
    Integer count;
    AppHistory appHistory;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_workout);

        sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);

        appHistory = new AppHistory();
        workoutLog = appHistory.setup(sharedPref);

        workout = workoutLog.getCurrentWorkout();
        set = workoutLog.getCurrentSet();

        TextView title = (TextView) findViewById(R.id.current_workout_name);
        title.setText(workout.getName());

        TextView currentActivity = (TextView) findViewById(R.id.current_activity_name);
        currentActivity.setText(set.getActivity());

        TextView currentSetNumber = (TextView) findViewById(R.id.current_set_number);

        count = 1;

        Integer numberOfSimilarSets = (Integer) workout.getNumberOfSimilarSets(set.getActivity());
        currentSetNumber.setText(count.toString() + " of " + numberOfSimilarSets.toString());

        EditText numberOfReps = (EditText) findViewById(R.id.current_reps);
        numberOfReps.setText(set.getReps().toString());

        EditText weight = (EditText) findViewById(R.id.current_weight);
        weight.setText(set.getWeight().toString());

    }

    public void finishSetButtonClicked(View button){
        EditText numberOfRepsInput = (EditText) findViewById(R.id.current_reps);
        String numberOfRepsAchieved = numberOfRepsInput.getText().toString();
        Integer numberOfReps = Integer.valueOf(numberOfRepsAchieved);

        EditText weightInput = (EditText) findViewById((R.id.current_weight));
        String weightAchieved = weightInput.getText().toString();
        Integer actualWeight = Integer.valueOf(weightAchieved);

        workoutLog.finishCurrentSet(numberOfReps, actualWeight);
        appHistory.updateLog(sharedPref, workoutLog);

        if (workoutLog.getCurrentSet() == null){

            Toast.makeText(this, "Workout Finished", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, ViewWorkoutActivity.class);
            intent.putExtra("workout", workout.getName());
            startActivity(intent);
        } else {

            if (set.getActivity().equals(workoutLog.getCurrentSet().getActivity())){
                count++;
            } else {
                count = 1;
            }

            set = workoutLog.getCurrentSet();

            TextView currentSetNumber = (TextView) findViewById(R.id.current_set_number);

            Integer numberOfSimilarSets = (Integer) workout.getNumberOfSimilarSets(set.getActivity());
            currentSetNumber.setText(count.toString() + " of " + numberOfSimilarSets.toString());

            EditText newNumberOfReps = (EditText) findViewById(R.id.current_reps);
            newNumberOfReps.setText(set.getReps().toString());

            EditText weight = (EditText) findViewById(R.id.current_weight);
            weight.setText(set.getWeight().toString());

            TextView currentActivity = (TextView) findViewById(R.id.current_activity_name);
            currentActivity.setText(set.getActivity());

        }
    }
}