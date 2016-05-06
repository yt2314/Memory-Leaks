package com.nimbledroid.memoryleaks;



import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Activity;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import java.io.IOException;

import nimbledroid.trace.Profile;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import nimbledroid.trace.Profile;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ApplicationTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void staticActivityTest() {
        start();
        onView(withId(R.id.sa_button)).perform(click());

        // This view is in a different Activity, no need to tell Espresso.
        onView(withId(R.id.leak_text)).check(matches(withText("You've triggered a memory leak")));
        stop();
    }

    private void start() {
        Profile.startProfile();
        android.util.Log.i("NDProfiler", "test started at "+System.currentTimeMillis());
    }

    private void stop() {
        android.util.Log.i("NDProfiler", "test stopped at "+System.currentTimeMillis());
        Profile.stopProfile();
    }
}