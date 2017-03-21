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
        assertEquals((Integer) 5, set3.getReps());
        assertEquals("lunges", set4.getActivity());
    }

    @Test
    public void testGetAllSets(){
        ArrayList<Set> allSets = workout.getAllSets();
        assertEquals(2, allSets.size());
    }

    @Test
    public void getNumberOfSimilarSetsTest(){
        workout.addMultipleSets(Activity.LUNGES, 5, 30, 5);
        assertEquals(5, workout.getNumberOfSimilarSets("lunges"));
    }

    @Test
    public void getConciseSetDetails(){
        String expected = "1 x deadlift: 8reps x 70kg";
        String returned = workout.getSetDetailsConciseForm().get(0);
        assertEquals(returned, expected);
    }

    @Test
    public void getConciseSetDetailsWorksForMultiples(){
        Set newSet = new Set(Activity.DEADLIFT, 8, 70);
        workout.addSet(newSet);
        String expected = "2 x deadlift: 8reps x 70kg";
        String returned = workout.getSetDetailsConciseForm().get(0);
        assertEquals(returned, expected);
    }

    @Test
    public void getActivityFromConciseSetDetails(){
        String setDetails = workout.getSetDetailsConciseForm().get(0);
        assertEquals("deadlift", workout.getActivityFromConciseSetDetails(setDetails));
    }

    @Test
    public void canDeleteASetByActivityName(){
        workout.deleteSetByName("deadlift");
        Set newFirstSet = workout.getSet(0);
        assertEquals(set2, newFirstSet);
    }

    @Test
    public void deleteSetByNameOnlyDeletesOneSet(){
        workout.addSet(set2);
        workout.deleteSetByName("squat");
        Set secondSet = workout.getSet(1);
        assertEquals(set2, secondSet);
        assertEquals(2, workout.getAllSets().size());
    }

    @Test
    public void canDeleteSetFromWorkout(){
        workout.deleteSet(set1);
        Set newFirstSet = workout.getSet(0);
        assertEquals(set2, newFirstSet);
    }

    @Test
    public void addMultipleSetsWorksForStringCustomActivity(){
        workout.addMultipleSets("leg raises", 8, 20, 2);
        assertEquals("leg raises", workout.getAllSets().get(3).getActivity());
    }

    @Test
    public void workoutHasUniqueId(){
        Workout workout2 = new Workout("test again");
        assertNotEquals(workout.getId(), workout2.getId());
    }

}