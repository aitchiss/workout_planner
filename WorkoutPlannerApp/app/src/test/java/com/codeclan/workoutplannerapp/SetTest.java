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
}