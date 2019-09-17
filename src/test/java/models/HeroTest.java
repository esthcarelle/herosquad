package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class HeroTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Hero.clearAllTasks(); // clear out all the tasks before each test
    }

    @Test
    public void NewTaskObjectGetsCorrectlyCreated_true() throws Exception {
        Hero hero = setupNewTask();
        assertEquals(true, hero instanceof Hero);
    }

    @Test
    public void TaskInstantiatesWithDescription_true() throws Exception {
        Hero hero = setupNewTask();
        assertEquals("Mow the lawn", hero.getDescription());
    }

    @Test
    public void AllTasksAreCorrectlyReturned_true() throws Exception {
        Hero hero = setupNewTask();
        Hero otherHero = new Hero("Brush the cat");
        assertEquals(2, Hero.getAll().size());
    }

    @Test
    public void AllTasksContainsAllTasks_true() throws Exception {
        Hero hero = setupNewTask();
        Hero otherHero = new Hero("Brush the cat");
        assertTrue(Hero.getAll().contains(hero));
        assertTrue(Hero.getAll().contains(otherHero));
    }

    @Test
    public void isCompletedPropertyIsFalseAfterInstantiation() throws Exception {
        Hero hero = setupNewTask();
        assertEquals(false, hero.getCompleted()); //should never start as completed
    }

    @Test
    public void getCreatedAtInstantiatesWithCurrentTimeToday() throws Exception {
    Hero hero = setupNewTask();
        assertEquals(LocalDateTime.now().getDayOfWeek(), hero.getCreatedAt().getDayOfWeek());
    }

    @Test
    public void tasksInstantiateWithId() throws Exception {
        Hero hero = setupNewTask();
        assertEquals(1, hero.getId());
    }

    @Test
    public void findReturnsCorrectTask() throws Exception {
        Hero hero = setupNewTask();
        assertEquals(1, Hero.findById(hero.getId()).getId());
    }


    @Test
    public void findReturnsCorrectTaskWhenMoreThanOneTaskExists() throws Exception {
        Hero hero = setupNewTask();
        Hero otherHero = new Hero("Brush the cat");
        assertEquals(2, Hero.findById(otherHero.getId()).getId());
    }

    @Test
    public void updateChangesTaskContent() throws Exception {
        Hero hero = setupNewTask();
        String formerContent = hero.getDescription();
        LocalDateTime formerDate = hero.getCreatedAt();
        int formerId = hero.getId();

        hero.update("Floss the cat");

        assertEquals(formerId, hero.getId());
        assertEquals(formerDate, hero.getCreatedAt());
        assertNotEquals(formerContent, hero.getDescription());
    }

    @Test
    public void deleteDeletesASpecificTask() throws Exception {
        Hero hero = setupNewTask();
        Hero otherHero = new Hero("Brush the cat");
        hero.deleteTask();
        assertEquals(1, Hero.getAll().size()); //one is left
        assertEquals(Hero.getAll().get(0).getId(), 2); //the one that was deleted has the id of 2
    }

    @Test
    public void deleteAllTasksDeletesAllTasks() throws Exception {
        Hero hero = setupNewTask();
        Hero otherHero = setupNewTask();
        Hero.clearAllTasks();
        assertEquals(0, Hero.getAll().size());
    }


    //helper methods
    public Hero setupNewTask(){
        return new Hero("Mow the lawn");
    }
}