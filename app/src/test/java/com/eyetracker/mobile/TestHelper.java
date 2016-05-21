package com.eyetracker.mobile;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

/**
 * Created by fabia on 5/20/2016.
 */
public class TestHelper {

    public static void setTestInjector() {
        ShadowLog.stream = System.out;
        EyeTrackerApplication application = (EyeTrackerApplication) RuntimeEnvironment.application;
        EyeTrackerApplicationComponent injector = DaggerTestComponent.builder().testModule(new TestModule(application.getApplicationContext())).build();
        application.injector = injector;
    }

}
