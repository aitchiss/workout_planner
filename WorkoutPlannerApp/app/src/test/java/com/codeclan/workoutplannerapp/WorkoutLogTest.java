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


    @Before
    public void before(){
        workoutLog = new WorkoutLog();

        workoutTemplate = new WorkoutTemplate("test workout", 1);
        workoutTemplate.addMultipleSets(Activity.BENCHPRESS, 5, 35, 3);
        workoutTemplate.addMultipleSets(Activity.SQUAT, 6, 50, 3);

        workoutTemplate2 = new WorkoutTemplate("second test workout", 2);
        workoutTemplate2.addMultipleSets(Activity.LUNGES, 10, 25, 4);

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
        workoutLog.startWorkout(workoutTemplate.getId());
        Set set1 = workoutLog.getCurrentSet();
        assertEquals(Activity.BENCHPRESS, set1.getActivityType());
    }

    @Test
    public void startingWorkoutDoesntAlterTemplate(){
        workoutLog.startWorkout(workoutTemplate.getId());
        workoutLog.getCurrentSet().setReps(122);
        assertEquals((Integer) 122, workoutLog.getCurrentSet().getReps());
        assertEquals((Integer) 5, workoutLog.getWorkoutTemplate("test workout").getSet(0).getReps());
    }

    @Test
    public void canSaveCompletedWorkoutToHistory(){
        workoutLog.startWorkout(workoutTemplate.getId());
        workoutLog.finishCurrentWorkout();
        Workout firstCompletedWorkout = workoutLog.getCompletedWorkouts().get(0);
        assertEquals((Integer) 5, firstCompletedWorkout.getSet(0).getReps());
    }

    @Test
    public void finishCurrentWorkoutResetsCurrentWorkoutToNull(){
        workoutLog.startWorkout(workoutTemplate.getId());
        workoutLog.finishCurrentWorkout();
        assertEquals(null, workoutLog.getCurrentWorkout());
    }

    @Test
    public void updateCurrentSetUpdatesRepsAndWeight(){
        workoutLog.startWorkout(workoutTemplate.getId());
        workoutLog.updateCurrentSet(5, 25);
        assertEquals((Integer) 25, workoutLog.getCurrentSet().getWeight());
    }

    @Test
    public void finishCurrentSetUpdatesCurrentSet(){
        workoutLog.startWorkout(workoutTemplate.getId());
        workoutLog.finishCurrentSet(5, 25);
        assertEquals((Integer) 35, workoutLog.getCurrentSet().getWeight());
    }

    @Test
    public void anotherSetRemainingReturnsFalseIfFinalSet(){
        WorkoutTemplate shortWorkout = new WorkoutTemplate("short workout", 3);
        shortWorkout.addMultipleSets(Activity.LUNGES, 2, 33, 2);
        workoutLog.addWorkoutTemplate(shortWorkout);
        workoutLog.startWorkout(workoutTemplate.getId());
        workoutLog.finishCurrentSet(2, 33);

        assertEquals(false, workoutLog.anotherSetRemaining());
    }

    @Test
    public void anotherSetRemainingReturnsTrueIfNotFinalSet(){
        WorkoutTemplate shortWorkout = new WorkoutTemplate("short workout", 3);
        shortWorkout.addMultipleSets(Activity.LUNGES, 2, 33, 2);
        workoutLog.addWorkoutTemplate(shortWorkout);
        workoutLog.startWorkout(shortWorkout.getId());
        assertEquals(true, workoutLog.anotherSetRemaining());
    }

    @Test
    public void finishCurrentSetEndsWorkoutIfLastSet(){
        WorkoutTemplate shortWorkout = new WorkoutTemplate("short workout", 3);
        shortWorkout.addMultipleSets(Activity.LUNGES, 2, 33, 2);
        workoutLog.addWorkoutTemplate(shortWorkout);
        workoutLog.startWorkout(shortWorkout.getId());
        workoutLog.finishCurrentSet(2, 33);
        workoutLog.finishCurrentSet(2, 33);
        assertEquals(null, workoutLog.getCurrentWorkout());
        assertEquals("short workout", workoutLog.getCompletedWorkouts().get(0).getName());
    }

    @Test
    public void testCanRetrieveLastDateCompleted(){
        workoutLog.startWorkout(workoutTemplate.getId());
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

    @Test
    public void testCanGetCurrentProgress(){
        workoutLog.startWorkout(workoutTemplate.getId());
        assertEquals("1 of 3", workoutLog.getCurrentSetProgress("benchpress"));
    }

    @Test
    public void canGetProgressMidWayThroughWorkout(){
        workoutLog.startWorkout(workoutTemplate.getId());
        workoutLog.finishCurrentSet(2, 15);
        assertEquals("2 of 3", workoutLog.getCurrentSetProgress("benchpress"));
    }

    @Test
    public void canAddAllWorkoutTypes(){
        WorkoutType template1 = new WorkoutTemplate("test", 4);
        WorkoutType workout1 = new Workout(workoutTemplate);
        workoutLog.addToAllWorkouts(template1);
        workoutLog.addToAllWorkouts(workout1);
        assertEquals(2, workoutLog.getAllWorkoutTypes().size());
    }


}