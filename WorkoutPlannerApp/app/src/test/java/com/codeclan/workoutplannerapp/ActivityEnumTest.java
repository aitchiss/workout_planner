package com.codeclan.workoutplannerapp;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ActivityEnumTest {
    @Test
    public void testCanGetArrayList(){
        ArrayList<Activity> allActivities = Activity.getAllActivities();
        Activity firstActivity = allActivities.get(0);
        Activity secondActivity = allActivities.get(1);
        assertEquals(Activity.DEADLIFT, firstActivity);
        assertEquals(Activity.SQUAT, secondActivity);
    }

    @Test
    public void toStringGetsActivityName(){
        assertEquals("benchpress", Activity.BENCHPRESS.toString());
    }

}