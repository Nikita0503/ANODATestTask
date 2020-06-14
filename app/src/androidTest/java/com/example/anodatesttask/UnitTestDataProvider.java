package com.example.anodatesttask;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.anodatesttask.model.DataProvider;
import com.example.anodatesttask.model.data.DataObject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertEquals;
@RunWith(AndroidJUnit4.class)
public class UnitTestDataProvider {
    DataProvider dataProvider;

    @Before
    public void setUp(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        dataProvider = new DataProvider(appContext);
    }

    @Test
    public void isCompletedGetData() {
        TestObserver<DataObject> testObserver = dataProvider.getData.test();
        testObserver.assertComplete();
    }

    @After
    public void tearDown() {
        dataProvider = null;
    }
}
