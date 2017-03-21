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

    WorkoutTemplate workoutTemplate;
    WorkoutTemplate workoutTemplate2;
    Workout workout;
    Workout workout2;

    @Before
    public void before(){
        workoutLog = new WorkoutLog();

        workoutTemplate = new WorkoutTemplate("test workout");
        workoutTemplate.addMultipleSets(Activity.BENCHPRESS, 5, 35, 3);
        workoutTemplate.addMultipleSets(Activity.SQUAT, 6, 50, 3);

//        workout = new Workout(workoutTemplate);

        workoutTemplate2 = new WorkoutTemplate("second test workout");
        workoutTemplate2.addMultipleSets(Activity.LUNGES, 10, 25, 4);

//        workout2 = new Workout(workoutTemplate2);


        workoutLog.addWorkoutTemplate(workoutTemplate);
        workoutLog.addWorkoutTemplate(workoutTemplate2);
    }

    @Test
    public void canSaveWorkoutTemplate(){
        WorkoutTemplate foundTemplate = workoutLog.getWorkoutTemplate("test workout");
        assertEquals(workoutTemplate, foundTemplate);
    }

    @Test
    public void testGetTemplateCanSearchMultipleWorkouts(){
        WorkoutTemplate foundTemplate = workoutLog.getWorkoutTemplate("second test workout");
        assertEquals(workoutTemplate2, foundTemplate);
    }

    @Test
    public void canGetAllWorkoutTemplates(){
        ArrayList<WorkoutTemplate> allWorkouts = workoutLog.getAllWorkoutTemplates();
        assertEquals(workoutTemplate, allWorkouts.get(0));
        assertEquals(workoutTemplate2, allWorkouts.get(1));
    }

    @Test
    public void canStartWorkoutFromTemplate(){
        workoutLog.startWorkout("test workout");
        Set set1 = workoutLog.getCurrentSet();
        assertEquals(Activity.BENCHPRESS, set1.getActivityType());
    }

    @Test
    public void startingWorkoutDoesntAlterTemplate(){
        workoutLog.startWorkout("test workout");
        workoutLog.getCurrentSet().setReps(122);
        assertEquals((Integer) 122, workoutLog.getCurrentSet().getReps());
        assertEquals((Integer) 5, workoutLog.getWorkoutTemplate("test workout").getSet(0).getReps());
    }

    @Test
    public void canSaveCompletedWorkoutToHistory(){
        workoutLog.startWorkout("test workout");
        workoutLog.finishCurrentWorkout();
        Workout firstCompletedWorkout = workoutLog.getCompletedWorkouts().get(0);
        assertEquals((Integer) 5, firstCompletedWorkout.getSet(0).getReps());
    }

    @Test
    public void finishCurrentWorkoutResetsCurrentWorkoutToNull(){
        workoutLog.startWorkout("test workout");
        workoutLog.finishCurrentWorkout();
        assertEquals(null, workoutLog.getCurrentWorkout());
    }

    @Test
    public void updateCurrentSetUpdatesRepsAndWeight(){
        workoutLog.startWorkout("test workout");
        workoutLog.updateCurrentSet(5, 25);
        assertEquals((Integer) 25, workoutLog.getCurrentSet().getWeight());
    }

    @Test
    public void finishCurrentSetUpdatesCurrentSet(){
        workoutLog.startWorkout("test workout");
        workoutLog.finishCurrentSet(5, 25);
        assertEquals((Integer) 35, workoutLog.getCurrentSet().getWeight());
    }

    @Test
    public void anotherSetRemainingReturnsFalseIfFinalSet(){
        WorkoutTemplate shortWorkout = new WorkoutTemplate("short workout");
        shortWorkout.addMultipleSets(Activity.LUNGES, 2, 33, 2);
        workoutLog.addWorkoutTemplate(shortWorkout);
        workoutLog.startWorkout("short workout");
        workoutLog.finishCurrentSet(2, 33);

        assertEquals(false, workoutLog.anotherSetRemaining());
    }

    @Test
    public void anotherSetRemainingReturnsTrueIfNotFinalSet(){
        WorkoutTemplate shortWorkout = new WorkoutTemplate("short workout");
        shortWorkout.addMultipleSets(Activity.LUNGES, 2, 33, 2);
        workoutLog.addWorkoutTemplate(shortWorkout);
        workoutLog.startWorkout("short workout");
        assertEquals(true, workoutLog.anotherSetRemaining());
    }

    @Test
    public void finishCurrentSetEndsWorkoutIfLastSet(){
        WorkoutTemplate shortWorkout = new WorkoutTemplate("short workout");
        shortWorkout.addMultipleSets(Activity.LUNGES, 2, 33, 2);
        workoutLog.addWorkoutTemplate(shortWorkout);
        workoutLog.startWorkout("short workout");
        workoutLog.finishCurrentSet(2, 33);
        workoutLog.finishCurrentSet(2, 33);
        assertEquals(null, workoutLog.getCurrentWorkout());
        assertEquals("short workout", workoutLog.getCompletedWorkouts().get(0).getName());
    }

    @Test
    public void testCanRetrieveLastDateCompleted(){
        workoutLog.startWorkout("test workout");
        assertEquals("19-Mar-2017", workoutLog.getWorkoutTemplate("test workout").getTemplateLastUsedDate());
    }

    @Test
    public void canDeleteAWorkoutTemplate(){
        workoutLog.deleteWorkoutTemplate(workoutTemplate);
        assertEquals(1, workoutLog.getAllWorkoutTemplates().size());
    }

    @Test
    public void canGetWorkoutById(){
        WorkoutTemplate foundWorkout = workoutLog.getWorkoutTemplate(workoutTemplate.getId());
        assertEquals(workoutTemplate, foundWorkout);
    }


}