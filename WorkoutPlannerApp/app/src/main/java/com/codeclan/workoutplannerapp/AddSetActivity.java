package com.codeclan.workoutplannerapp;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AddSetActivity extends AppCompatActivity {

    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";
    WorkoutTemplate workout;
    Activity activity;
    AppHistory appHistory;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_set);

        sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        appHistory = new AppHistory();
        workoutLog = appHistory.setup(sharedPref);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        int selectedWorkoutId = extras.getInt("workout");
        workout = workoutLog.getWorkoutTemplate(selectedWorkoutId);

        String selectedActivityName = extras.getString("activity");
        activity = Activity.getActivityByString(selectedActivityName);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Adding to: " + workout.getName());

        TextView activityName = (TextView) findViewById(R.id.add_set_activity_name);
        activityName.setText(selectedActivityName);
    }

    public void confirmAddSetButtonClick(View button){

        EditText numberOfSetsInput = (EditText) findViewById(R.id.choose_set_number);
        EditText numberOfRepsInput = (EditText) findViewById(R.id.choose_reps_number);

        if (isEmpty(numberOfSetsInput) || isEmpty(numberOfRepsInput)){
            errorDialog();
        } else {
            Integer numberOfSets = Integer.valueOf(numberOfSetsInput.getText().toString());
            Integer numberOfReps = Integer.valueOf(numberOfRepsInput.getText().toString());

            Integer numberOfWeight = getWeightInput();

            workout.addMultipleSets(activity, numberOfReps, numberOfWeight, numberOfSets);

            appHistory.updateLog(sharedPref, workoutLog);

            Intent intent = new Intent(this, ViewWorkoutActivity.class);
            intent.putExtra("workout", workout.getId());
            startActivity(intent);
        }
    }

    public void errorDialog(){
        FragmentManager fm = getFragmentManager();
        InputErrorWarning dialogFragment = new InputErrorWarning ();
        setTheme(R.style.DialogWarning);
        dialogFragment.show(fm, "Error");
    }

    public void onAddSuperSetButtonClick(View button){
        EditText numberOfSetsInput = (EditText) findViewById(R.id.choose_set_number);
        EditText numberOfRepsInput = (EditText) findViewById(R.id.choose_reps_number);

        if (isEmpty(numberOfSetsInput) || isEmpty(numberOfRepsInput)){
            errorDialog();
        } else {
            Integer numberOfSets = Integer.valueOf(numberOfSetsInput.getText().toString());
            Integer numberOfReps = Integer.valueOf(numberOfRepsInput.getText().toString());

            Integer numberOfWeight = getWeightInput();

            Intent intent = new Intent(this, SelectSuperSetActivity.class);
            intent.putExtra("workout", workout.getId());
            intent.putExtra("main activity", activity.toString().toLowerCase());
            intent.putExtra("main sets", numberOfSets);
            intent.putExtra("main reps", numberOfReps);
            intent.putExtra("main weight", numberOfWeight);

            startActivity(intent);
        }
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().length() == 0;
    }

    public Integer getWeightInput(){
        EditText numberOfWeightInput = (EditText) findViewById(R.id.choose_weight_number);

        if (isEmpty(numberOfWeightInput)){
            return 0;
        } else {
            return Integer.valueOf(numberOfWeightInput.getText().toString());
        }
    }

}
