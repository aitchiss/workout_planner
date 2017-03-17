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
public class WorkoutTest {
    Workout workout;
    Set set1;
    Set set2;

    @Before
    public void before(){
        workout = new Workout("legs day");
        set1 = new Set(Activity.DEADLIFT, 8, 70);
        set2 = new Set(Activity.SQUAT, 8, 55);
        workout.addSet(set1);
        workout.addSet(set2);
    }

    @Test
    public void testWorkoutHasName(){
        assertEquals("legs day", workout.getName());
    }

    @Test
    public void testWorkoutCanHoldAndReturnSets(){
        assertEquals(set1, workout.getSet(0));
        assertEquals(set2, workout.getSet(1));
    }

    @Test
    public void canACreateAndAddMultipleSets(){
        workout.addMultipleSets(Activity.LUNGES, 5, 30, 5);
        Set set3 = workout.getSet(2);
        Set set4 = workout.getSet(6);
        assertEquals(5, set3.getReps());
        assertEquals("lunges", set4.getActivity());
    }

    @Test
    public void testGetAllSets(){
        ArrayList<Set> allSets = workout.getAllSets();
        assertEquals(2, allSets.size());
    }


}