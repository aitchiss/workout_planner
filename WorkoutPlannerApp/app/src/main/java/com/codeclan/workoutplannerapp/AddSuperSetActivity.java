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

public class AddSuperSetActivity extends AppCompatActivity {

    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";
    WorkoutTemplate workout;
    AppHistory appHistory;
    SharedPreferences sharedPref;
    Activity superSetActivity;
    String mainActivity;
    int mainSets;
    int mainReps;
    int mainWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_super_set);

        sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);

        appHistory = new AppHistory();
        workoutLog = appHistory.setup(sharedPref);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int selectedWorkoutId = extras.getInt("workout");
        workout = workoutLog.getWorkoutTemplate(selectedWorkoutId);

        mainActivity = extras.getString("main activity");
        mainSets = extras.getInt("main sets");
        mainReps = extras.getInt("main reps");
        mainWeight = extras.getInt("main weight");

        String selectedActivityName = extras.getString("activity");
        superSetActivity = Activity.valueOf(selectedActivityName);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add as superset with " + mainActivity);

        TextView activityName = (TextView) findViewById(R.id.add_set_activity_name);
        activityName.setText(selectedActivityName.toLowerCase());
    }


    public void confirmAddSetButtonClick(View button){

        EditText numberOfRepsInput = (EditText) findViewById(R.id.choose_reps_number);
        Integer numberOfReps = Integer.valueOf(numberOfRepsInput.getText().toString());

        if (numberOfReps.equals(0)){
            errorDialog();
        } else {
            EditText numberOfWeightInput = (EditText) findViewById(R.id.choose_weight_number);
            Integer numberOfWeight = Integer.valueOf(numberOfWeightInput.getText().toString());

            Set set1 = new Set(mainActivity, mainReps, mainWeight);
            Set set2 = new Set(superSetActivity, numberOfReps, numberOfWeight);
            workout.addMultipleWithSuperSet(set1, set2, mainSets);

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
}
