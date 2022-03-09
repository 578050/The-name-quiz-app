package com.example.thenamequizapp;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class EntryValidatorTest {

    @Rule
    public ActivityScenarioRule<DatabaseActivity> mActivityRule = new ActivityScenarioRule<>(
            DatabaseActivity.class);


    @Test
    public void addPerson() {

        onView(withId(R.id.addName)).perform(replaceText("Thomas"));
        onView(withId(R.id.pic)).perform(click());


    }

    @Test
    public void deletePerson() {
        onView(withId(R.id.delete));

        onData(anything())
                .inAdapterView(withId(android.R.id.list))
                .atPosition(0)
                .check(matches(withText("Cat")));


        //   onData(is(instanceOf(String.class)), is("Americano")));

   //     onView(allOf(withId(R.id.delete), hasSibling(withText("Cat")))).perform(click());
    }
}
