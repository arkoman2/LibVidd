package com.bestteam.libvidd;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by Arkadi on 17/01/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class mockitoTest
{
    private static final String FAKE_STRING = "HELLO WORLD";

    @Mock
    Context mMockContext;

//    @Test
//    public void checkIfUserObjWorks() {
//        // Given a mocked Context injected into the object under test...
//        when(mMockContext.getString(R.string.app_name))
//                .thenReturn(FAKE_STRING);
//
//        assertThat(mMockContext.getString(R.string.app_name), is(FAKE_STRING));
//    }

    @Test
    public void checkUserName   ()
    {
        User test = mock(User.class);

        when(test.getUserName()).thenReturn("works!");

        assertEquals(test.getUserName(), "works!");
    }
}
