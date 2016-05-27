package com.eyetracker.mobile.ui.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.R;
import com.eyetracker.mobile.model.Coordinate;
import com.eyetracker.mobile.model.Frame;
import com.eyetracker.mobile.ui.upload.UploadActivity;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.core.Size;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fabia on 4/22/2016.
 */
public class CameraActivity  extends Activity implements CameraScreen {

    public static final String TAG = "ACTIVITY_CAMERA";
    private static final String name = "Camera activity";

    private Tracker tracker;

    static {
        if (!OpenCVLoader.initDebug()) {
            Log.i(TAG, "OpenCVLoader Failed");
        } else {
            Log.i(TAG, "OpenCVLoader Succeeded");
            System.loadLibrary("opencv_java3");
        }

        System.loadLibrary("imageProcessorJNI");
    }

    private Camera camera;
    private CameraSurfaceView preview;

    @Inject
    CameraPresenter cameraPresenter;

    @Bind(R.id.flCameraPreview)
    FrameLayout previewLayout;
    @Bind(R.id.ivProcessed)
    ImageView iv_processed;

    @OnClick(R.id.btnRun)
    public void processImage(View v) {
        camera.takePicture(null, null, mPicture);
    }

    @OnClick(R.id.btnDiscard)
    public void discard(View v) {
        cameraPresenter.discard();
    }

    @OnClick(R.id.btnUpload)
    public void upload(View v) {
        cameraPresenter.upload();
    }

    @Override
    public void showProcessedImage(byte[] image) {
        ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        iv_processed.setImageBitmap(theImage);
    }

    @Override
    public void discardResults() {
        iv_processed.setImageBitmap(null);

        if (camera != null) {
            camera.startPreview();
        }
    }

    @Override
    public void uploadFrame(Frame frame) {
        Intent intent = new Intent(CameraActivity.this, UploadActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void showNetworkError(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showUploadSuccess() {
        Toast.makeText(this, "Successful upload", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String title = data.getStringExtra(UploadActivity.EXTRA_RETURNTITLE);
                cameraPresenter.startUpload(title);
                iv_processed.setImageBitmap(null);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // Obtain the shared Tracker instance.
        EyeTrackerApplication application = (EyeTrackerApplication) getApplication();
        tracker = application.getDefaultTracker();

        EyeTrackerApplication.injector.inject(this);

        ButterKnife.bind(this);

        //camera = Camera.open(1);
        preview = new CameraSurfaceView(this, null);

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
    public void onPause() {
        if(camera != null) {
            camera.stopPreview();
            preview.setCamera(null);
            camera.release();
            camera = null;
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i(TAG, "Setting screen name: " + name);
        tracker.setScreenName("Image~" + name);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());

        try{
            camera = Camera.open(1);
            camera.startPreview();
            preview.setCamera(camera);
        } catch (RuntimeException ex){
            Toast.makeText(this, "No camera no party", Toast.LENGTH_LONG).show();
        }
    }

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken (byte[] data, Camera camera) {
            cameraPresenter.processRawImage(data);
        }
    };

    //public native byte[] getNativeResult();

}
