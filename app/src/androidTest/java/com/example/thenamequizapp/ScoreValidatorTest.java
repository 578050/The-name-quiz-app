package com.example.thenamequizapp;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class ScoreValidatorTest {

    @Rule
    public ActivityScenarioRule<Quiz> mActivityRule = new ActivityScenarioRule<>(
            Quiz.class);

    @Test
    public void correctScore() throws InterruptedException {
        Thread.sleep(5000);
     //   onView(withId(R.id.quizList)).perform(click());
     //   onData(anything()).inAdapterView(withId(R.id.quizList)).atPosition(0).perform(click());

        onData(allOf())
                .inAdapterView(withId(R.id.quizList))
                .atPosition(0)
                .perform(click());
  //     int number = 1;
    //    onView(withId(R.id.score)).check(matches(withSubstring("Score: " + 1 + "/" + number)));
    }



}
