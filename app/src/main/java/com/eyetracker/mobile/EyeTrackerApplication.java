package com.eyetracker.mobile;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.eyetracker.mobile.ui.UIModule;
import com.orm.SugarApp;

/**
 * Created by fabia on 4/20/2016.
 */
public class EyeTrackerApplication extends SugarApp {

    public static EyeTrackerApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector =
                DaggerEyeTrackerApplicationComponent.builder().
                        uIModule(new UIModule(this)).build();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
