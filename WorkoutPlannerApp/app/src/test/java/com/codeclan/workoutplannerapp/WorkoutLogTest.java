package com.codeclan.workoutplannerapp;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class WorkoutLogTest {

    WorkoutLog workoutLog;

    Workout workout;
    Workout workout2;

    @Before
    public void before(){
        workoutLog = new WorkoutLog();

        workout = new Workout("test workout");
        workout.addMultipleSets(Activity.BENCHPRESS, 5, 35, 3);
        workout.addMultipleSets(Activity.SQUAT, 6, 50, 3);

        workout2 = new Workout("second test workout");
        workout.addMultipleSets(Activity.LUNGES, 10, 25, 4);

        workoutLog.addWorkoutTemplate(workout);
        workoutLog.addWorkoutTemplate(workout2);
    }

    @Test
    public void canSaveWorkoutAsTemplate(){
        Workout foundTemplate = workoutLog.getWorkoutTemplate("test workout");
        assertEquals(workout, foundTemplate);
    }

    @Test
    public void testGetTempateCanSearchMultipleWorkouts(){
        Workout foundTemplate = workoutLog.getWorkoutTemplate("second test workout");
        assertEquals(workout2, foundTemplate);
    }

    @Test
    public void canGetAllWorkoutTemplates(){
        ArrayList<Workout> allWorkouts = workoutLog.getAllWorkoutTemplates();
        assertEquals(workout, allWorkouts.get(0));
        assertEquals(workout2, allWorkouts.get(1));
    }
}