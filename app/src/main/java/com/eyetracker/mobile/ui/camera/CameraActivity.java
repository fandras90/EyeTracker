package com.eyetracker.mobile.ui.camera;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.R;

import org.opencv.core.Size;

import java.io.ByteArrayInputStream;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fabia on 4/22/2016.
 */
public class CameraActivity  extends Activity implements CameraScreen {

    public static final String KEY_CAMERA = "KEY_CAMERA";

    private Camera camera;
    private CameraSurfaceView preview;

    @Inject
    CameraPresenter cameraPresenter;

    @Bind(R.id.flCameraPreview)
    FrameLayout previewLayout;
    @Bind(R.id.ivProcessed)
    ImageView iv_processed;

    @OnClick(R.id.btnRun)
    public void calculate(View v) {
        camera.takePicture(null, null, mPicture);
    }

    @OnClick(R.id.btnDiscard)
    public void discard(View v) {
        cameraPresenter.discard();
    }

    @Override
    public void showProcessedImage(byte[] image) {
        ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        iv_processed.setImageBitmap(theImage);
    }

    @Override
    public void discardResults() {
        if (camera != null) {
            camera.startPreview();
        }
    }

    @Override
    public void upload() {

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

        cameraPresenter.initialize(preview.getWidth(), preview.getHeight());
    }

    @Override
    protected void onStop() {
        if (camera != null)
            camera.release();

        cameraPresenter.detachScreen();
        super.onStop();
    }

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken (byte[] data, Camera camera) {
            cameraPresenter.processRawImage(data);
        }
    };
}
