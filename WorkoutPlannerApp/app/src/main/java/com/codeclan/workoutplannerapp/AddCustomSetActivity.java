package com.codeclan.workoutplannerapp;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddCustomSetActivity extends AppCompatActivity {

    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";
    WorkoutTemplate workout;
    AppHistory appHistory;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_set);

        sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        appHistory = new AppHistory();
        workoutLog = appHistory.setup(sharedPref);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        int selectedWorkoutId = extras.getInt("workout");
        workout = workoutLog.getWorkoutTemplate(selectedWorkoutId);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Adding to: " + workout.getName());
    }

    public void addCustomSetButtonClick(View button){

        EditText numberOfSetsInput = (EditText) findViewById(R.id.choose_set_number);
        EditText numberOfRepsInput = (EditText) findViewById(R.id.choose_reps_number);

        if (isEmpty(numberOfSetsInput) || isEmpty(numberOfRepsInput)){
            errorDialog();
        } else{
            Integer numberOfSets = Integer.valueOf(numberOfSetsInput.getText().toString());
            Integer numberOfReps = Integer.valueOf(numberOfRepsInput.getText().toString());

            EditText numberOfWeightInput = (EditText) findViewById(R.id.choose_weight_number);
            Integer numberOfWeight;

            if (isEmpty(numberOfWeightInput)){
                numberOfWeight = 0;
            } else {
                numberOfWeight = Integer.valueOf(numberOfWeightInput.getText().toString());
            }

            EditText activity = (EditText) findViewById(R.id.enter_custom_activity);
            String activityName = activity.getText().toString();

            if (activityName.equals("")){
                activityName = "unnamed activity";
            }

            workout.addMultipleSets(activityName, numberOfReps, numberOfWeight, numberOfSets);

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

            EditText activityNameInput = (EditText) findViewById(R.id.enter_custom_activity);
            String activity = activityNameInput.getText().toString();

            if (activity.equals("")){
                activity = "unnamed activity";
            }

            EditText numberOfWeightInput = (EditText) findViewById(R.id.choose_weight_number);
            Integer numberOfWeight;

            if (isEmpty(numberOfWeightInput)){
                numberOfWeight = 0;
            } else {
                numberOfWeight = Integer.valueOf(numberOfWeightInput.getText().toString());
            }

            Intent intent = new Intent(this, SelectSuperSetActivity.class);
            intent.putExtra("workout", workout.getId());
            intent.putExtra("main activity", activity);
            intent.putExtra("main sets", numberOfSets);
            intent.putExtra("main reps", numberOfReps);
            intent.putExtra("main weight", numberOfWeight);

            startActivity(intent);
        }
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().length() == 0;
    }
}
