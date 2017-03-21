package com.codeclan.workoutplannerapp;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class WorkoutTemplateTest {
    WorkoutTemplate workoutTemplate;
    Set set1;
    Set set2;

    @Before
    public void before(){
        workoutTemplate = new WorkoutTemplate("legs day");
        set1 = new Set(Activity.DEADLIFT, 8, 70);
        set2 = new Set(Activity.SQUAT, 8, 55);
        workoutTemplate.addSet(set1);
        workoutTemplate.addSet(set2);
    }

    @Test
    public void testWorkoutHasName(){
        assertEquals("legs day", workoutTemplate.getName());
    }

    @Test
    public void testWorkoutCanHoldAndReturnSets(){
        assertEquals(set1, workoutTemplate.getSet(0));
        assertEquals(set2, workoutTemplate.getSet(1));
    }

    @Test
    public void canACreateAndAddMultipleSets(){
        workoutTemplate.addMultipleSets(Activity.LUNGES, 5, 30, 5);
        Set set3 = workoutTemplate.getSet(2);
        Set set4 = workoutTemplate.getSet(6);
        assertEquals((Integer) 5, set3.getReps());
        assertEquals("lunges", set4.getActivity());
    }

    @Test
    public void testGetAllSets(){
        ArrayList<Set> allSets = workoutTemplate.getAllSets();
        assertEquals(2, allSets.size());
    }


    @Test
    public void canDeleteASetByActivityName(){
        workoutTemplate.deleteSetByName("deadlift");
        Set newFirstSet = workoutTemplate.getSet(0);
        assertEquals(set2, newFirstSet);
    }

    @Test
    public void deleteSetByNameOnlyDeletesOneSet(){
        workoutTemplate.addSet(set2);
        workoutTemplate.deleteSetByName("squat");
        Set secondSet = workoutTemplate.getSet(1);
        assertEquals(set2, secondSet);
        assertEquals(2, workoutTemplate.getAllSets().size());
    }

    @Test
    public void canDeleteSetFromWorkout(){
        workoutTemplate.deleteSet(set1);
        Set newFirstSet = workoutTemplate.getSet(0);
        assertEquals(set2, newFirstSet);
    }

    @Test
    public void addMultipleSetsWorksForStringCustomActivity(){
        workoutTemplate.addMultipleSets("leg raises", 8, 20, 2);
        assertEquals("leg raises", workoutTemplate.getAllSets().get(3).getActivity());
    }

    @Test
    public void workoutHasUniqueId(){
        WorkoutTemplate workoutTemplate2 = new WorkoutTemplate("test again");
        assertNotEquals(workoutTemplate.getId(), workoutTemplate2.getId());
    }

}