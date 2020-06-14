package com.example.anodatesttask;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.anodatesttask.model.DataProvider;
import com.example.anodatesttask.model.data.DataObject;
import com.example.anodatesttask.presenters.MainPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.observers.TestObserver;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class UnitTestPresenter {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);
    MainPresenter presenter;

    @Before
    public void setUp(){
        presenter = new MainPresenter(mActivityRule.getActivity());
    }

    @Test
    public void isCompletedGetData() {
        assertEquals("Hello ANODA", presenter.getDescription("Hello", "ANODA").toString());
    }

    @After
    public void tearDown() {
        presenter = null;
    }
}
