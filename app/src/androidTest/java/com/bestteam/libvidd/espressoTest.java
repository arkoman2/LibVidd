package com.bestteam.libvidd;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by Arkadi on 17/01/2018.
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class espressoTest {

    @Test
    public void checkPackageName() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.bestteam.libvidd", appContext.getPackageName());
    }
    @Rule
    public ActivityTestRule<AddPicAfterActivity> addPickAct =  new ActivityTestRule(AddPicAfterActivity.class);

    @Test
    public  void checkTitleOnAct(){
        onView(withText("Welcome, Please Upload your photo")).check(matches(isDisplayed()));

    }
}