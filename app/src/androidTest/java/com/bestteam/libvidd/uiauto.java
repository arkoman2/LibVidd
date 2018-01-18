package com.bestteam.libvidd;

/**
 * Created by Arkadi on 17/01/2018.
 */

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.remote.InteractionResponse;
import android.support.test.filters.SdkSuppress;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Until;

import java.util.Calendar;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class uiauto {

    UiDevice emulator;
    @Rule
    public ActivityTestRule<MainActivity> mainAct =  new ActivityTestRule(MainActivity.class);

    @Test
    public void testGoBackButton(){
        emulator.getInstance(InstrumentationRegistry.getInstrumentation());
    }
}

