package com.codeclan.workoutplannerapp;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ViewWorkoutActivity extends AppCompatActivity {

    WorkoutLog workoutLog;
    public static final String WORKOUTLOG = "WorkoutLog";
    WorkoutTemplate workout;
    AppHistory appHistory;
    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_workout);
        
        sharedPref = getSharedPreferences(WORKOUTLOG, Context.MODE_PRIVATE);
        appHistory = new AppHistory();
        workoutLog = appHistory.setup(sharedPref);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int selectedWorkoutId = extras.getInt("workout");
        workout = workoutLog.getWorkoutTemplate(selectedWorkoutId);

        setupActionBar();
        fillListView();;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_view_workout, menu);
        return true;
    }

    public void setupActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("workout: " + workout.getName());
    }


    public void fillListView(){
        ArrayList<Set> listOfSets = workout.getAllSets();
        checkIfEmpty(listOfSets);

        WorkoutContentsAdapter workoutContentsAdapter = new WorkoutContentsAdapter(this, listOfSets);
        ListView listView = (ListView) findViewById(R.id.workout_contents);
        listView.setAdapter(workoutContentsAdapter);
    }

    public void checkIfEmpty(ArrayList<Set> listOfSets){
        TextView emptySets = (TextView) findViewById(R.id.empty_sets);
        if (listOfSets.size() == 0){
            emptySets.setVisibility(View.VISIBLE);
        }
    }

    public void onEditNameClick(MenuItem item){
        Intent intent = new Intent(this, EditNameActivity.class);
        intent.putExtra("workout", workout.getId());
        startActivity(intent);
    }

    public void onEditButtonClick(View button){
        int selectedWorkout = workout.getId();
        Intent intent = new Intent(this, EditWorkoutActivity.class);
        intent.putExtra("workout", selectedWorkout);
        startActivity(intent);
    }

    public void onStartButtonClick(View button){
        Intent intent = new Intent(this, PlayWorkoutActivity.class);
        intent.putExtra("workout", workout.getId());
        startActivity(intent);
    }

    public void onMinusSetButtonClick(View button){
        ImageView minusButton = (ImageView) button;
        Set setToDelete = (Set) minusButton.getTag();
        workout.deleteSet(setToDelete);
        appHistory.updateLog(sharedPref, workoutLog);

        fillListView();
    }

    public void onPlusButtonClick(View button){
        ImageView plusButton = (ImageView) button;
        Set setToCopy = (Set) plusButton.getTag();
        int indexOfSetToCopy = workout.getAllSets().indexOf(setToCopy);
        Set setToAdd = new Set(setToCopy.getActivity(), setToCopy.getReps(), setToCopy.getWeight());
        workout.addSetAtIndex(setToAdd, indexOfSetToCopy);

        appHistory.updateLog(sharedPref, workoutLog);
        fillListView();
    }

    public void deleteWorkoutButtonClick(View button){
        FragmentManager fm = getFragmentManager();
        DeleteWarning dialogFragment = new DeleteWarning ();
        setTheme(R.style.DialogWarning);
        dialogFragment.show(fm, "Confirm delete");
    }

    public void confirmDeleteButtonClick(View button){
        workoutLog.deleteWorkoutTemplate(workout);
        appHistory.updateLog(sharedPref, workoutLog);

        Toast.makeText(this, "Workout Deleted", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void cancelDeleteButtonClick(View button){
        Intent intent = new Intent (this, ViewWorkoutActivity.class);
        intent.putExtra("workout", workout.getId());
        startActivity(intent);
    }

    public void viewHistoryButtonClick(View button){
        Intent intent = new Intent (this, WorkoutHistoryActivity.class);
        intent.putExtra("workout", workout.getId());
        startActivity(intent);
    }


}
