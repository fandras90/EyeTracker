package com.eyetracker.mobile.ui.upload;

import android.app.Activity;
import android.os.Bundle;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.R;

import javax.inject.Inject;

/**
 * Created by fabia on 4/25/2016.
 */
public class UploadActivity extends Activity implements UploadScreen {

    public static final String TAG = "ACTIVITY_UPLOAD";

    @Inject
    UploadPresenter uploadPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        EyeTrackerApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        uploadPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        uploadPresenter.detachScreen();
        super.onStop();
    }

}
