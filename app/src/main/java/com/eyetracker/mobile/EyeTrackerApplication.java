package com.eyetracker.mobile;

import android.app.Application;

import com.eyetracker.mobile.ui.UIModule;

/**
 * Created by fabia on 4/20/2016.
 */
public class EyeTrackerApplication extends Application {

    public static EyeTrackerApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        switch (BuildConfig.FLAVOR) {
            case "production":
                injector =
                        DaggerEyeTrackerApplicationComponent.builder().
                                uIModule(new UIModule(this)).build();
            case "mock":
                injector =
                        DaggerEyeTrackerMockApplicationComponent.builder().
                                uIModule(new UIModule(this)).build();
        }
    }

}
