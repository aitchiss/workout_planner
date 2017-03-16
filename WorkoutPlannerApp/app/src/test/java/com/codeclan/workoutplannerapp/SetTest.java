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
        assertEquals(6, set.getReps());
    }

    @Test
    public void testSetHasWeight(){
        assertEquals(80, set.getWeight());
    }

    @Test
    public void canChangeReps(){
        set.setReps(4);
        assertEquals(4, set.getReps());
    }

    @Test
    public void canChangeWeight(){
        set.setWeight(65);
        assertEquals(65, set.getWeight());
    }

    @Test
    public void canUpdateSetWithNewRepsAndWeight(){
        set.updateSet(2, 20);
        assertEquals(2, set.getReps());
        assertEquals(20, set.getWeight());
    }
}