package com.codeclan.workoutplannerapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SetTest {

    Set set;

    @Before
    public void before(){
        set = new Set(Activity.DEADLIFT, 6, 80);
    }

    @Test
    public void testSetHasActivityType(){
        assertEquals("deadlift", set.getActivity());
    }

    @Test
    public void testSetHasReps(){
        Integer reps = set.getReps();
        assertEquals((Integer) 6, reps);
    }

    @Test
    public void testSetHasWeight(){
        assertEquals((Integer) 80, set.getWeight());
    }

    @Test
    public void canChangeReps(){
        set.setReps(4);
        assertEquals((Integer) 4, set.getReps());
    }

    @Test
    public void canChangeWeight(){
        set.setWeight(65);
        assertEquals((Integer) 65, set.getWeight());
    }

    @Test
    public void canUpdateSetWithNewRepsAndWeight(){
        set.updateSet(2, 20);
        assertEquals((Integer) 2, set.getReps());
        assertEquals((Integer) 20, set.getWeight());
    }


}