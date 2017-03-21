package com.codeclan.workoutplannerapp;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class WorkoutTest {
    WorkoutTemplate workoutTemplate;
    Workout workout;
    Set set1;
    Set set2;

    @Before
    public void before(){
        set1 = new Set(Activity.DEADLIFT, 8, 70);
        set2 = new Set(Activity.SQUAT, 8, 55);
        workoutTemplate = new WorkoutTemplate("legs day", 1);
        workoutTemplate.addSet(set1);
        workoutTemplate.addSet(set2);
        workout = new Workout(workoutTemplate);
    }

    @Test
    public void idMatchesTemplateId(){
        assertEquals(workoutTemplate.getId(), workout.getId());
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
    public void testGetAllSets(){
        ArrayList<Set> allSets = workout.getAllSets();
        assertEquals(2, allSets.size());
    }


    @Test
    public void getConciseSetDetails(){
        String expected = "1 x deadlift: 8reps x 70kg";
        String returned = workout.getSetDetailsConciseForm().get(0);
        assertEquals(returned, expected);
    }


    @Test
    public void getActivityFromConciseSetDetails(){
        String setDetails = workout.getSetDetailsConciseForm().get(0);
        assertEquals("deadlift", workout.getActivityFromConciseSetDetails(setDetails));
    }



}