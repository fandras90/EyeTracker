package com.eyetracker.mobile.ui.camera;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.R;

import org.opencv.core.Size;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fabia on 4/22/2016.
 */
public class CameraActivity  extends Activity implements CameraScreen {

    public static final String KEY_CAMERA = "KEY_CAMERA";

    private Camera camera;
    private CameraSurfaceView preview;

    @Inject
    CameraPresenter cameraPresenter;

    @Bind(R.id.camera_preview)
    FrameLayout previewLayout;

    @Override
    public void showEyecenters(Size left, Size right) {

    }

    @Override
    public void discardResults() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(KEY_CAMERA, "called onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        EyeTrackerApplication.injector.inject(this);
        ButterKnife.bind(this);

        camera = Camera.open(1);
        preview = new CameraSurfaceView(this, camera);

        previewLayout.addView(preview);
    }

    @Override
    protected void onStart() {
        super.onStart();
        cameraPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        if (camera != null)
            camera.release();

        cameraPresenter.detachScreen();
        super.onStop();
    }

}
