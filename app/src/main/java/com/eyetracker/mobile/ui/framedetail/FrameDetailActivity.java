package com.eyetracker.mobile.ui.framedetail;

import android.app.Activity;
import android.os.Bundle;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.R;

import javax.inject.Inject;

/**
 * Created by fabia on 4/25/2016.
 */
public class FrameDetailActivity extends Activity implements FrameDetailScreen {

    public static final String TAG = "ACTIVITY_FRAMEDETAIL";

    @Inject
    FrameDetailPresenter frameDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framedetail);

        EyeTrackerApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        frameDetailPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        frameDetailPresenter.detachScreen();
        super.onStop();
    }

}
