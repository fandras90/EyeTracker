package com.eyetracker.mobile.ui.camera;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.R;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.core.Size;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fabia on 4/24/2016.
 */
public class LiveActivity extends AppCompatActivity implements CameraScreen {

    public static final String KEY_CAMERA = "KEY_CAMERA";

    private Camera camera;
    private CameraSurfaceView preview;

    @Inject
    CameraPresenter cameraPresenter;

    @Bind(R.id.camera_preview)
    FrameLayout previewLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(KEY_CAMERA, "called onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_camera_view);

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

    @Override
    public void showEyecenters(Size left, Size right) {

    }

    @Override
    public void discardResults() {

    }
}