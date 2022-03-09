package com.example.thenamequizapp;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class DatabaseTest {

    @Rule
    public ActivityScenarioRule<DatabaseActivity> mActivityRule = new ActivityScenarioRule<>(
            DatabaseActivity.class);

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
