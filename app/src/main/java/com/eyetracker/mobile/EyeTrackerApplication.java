package com.eyetracker.mobile;

import com.crashlytics.android.Crashlytics;
import com.eyetracker.mobile.ui.UIModule;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.orm.SugarApp;
import io.fabric.sdk.android.Fabric;

/**
 * Created by fabia on 4/20/2016.
 */
public class EyeTrackerApplication extends SugarApp {

    public static EyeTrackerApplicationComponent injector;

    private Tracker mTracker;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        injector =
                DaggerEyeTrackerApplicationComponent.builder().
                        uIModule(new UIModule(this)).build();

    }

    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }

}
