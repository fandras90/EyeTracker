package com.eyetracker.mobile.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.R;
import com.eyetracker.mobile.ui.camera.CameraActivity;
import com.eyetracker.mobile.ui.camera.LiveActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrameListActivity extends AppCompatActivity implements FrameListScreen {

    public static final String KEY_FRAMES = "KEY_FRAMES";

    @Inject
    FrameListPresenter frameListPresenter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @OnClick(R.id.fab)
    public void onClick(View view) {
        frameListPresenter.startCamera();
    }

    @Override
    public void startCamera() {
        Intent intent = new Intent(FrameListActivity.this, LiveActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framelist);

        EyeTrackerApplication.injector.inject(this);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        frameListPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        frameListPresenter.detachScreen();
    }
}
