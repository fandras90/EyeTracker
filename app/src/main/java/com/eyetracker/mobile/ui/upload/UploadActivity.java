package com.eyetracker.mobile.ui.upload;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.R;
import com.eyetracker.mobile.ui.camera.CameraActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fabia on 4/25/2016.
 */
public class UploadActivity extends Activity implements UploadScreen {

    public static final String TAG = "ACTIVITY_UPLOAD";

    @Inject
    UploadPresenter uploadPresenter;

    @Bind(R.id.btnUpload)
    Button btnUpload;
    @Bind(R.id.etTitle)
    EditText etTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        EyeTrackerApplication.injector.inject(this);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        byte[] image = intent.getByteArrayExtra(CameraActivity.EXTRA_IMAGE);
        uploadPresenter.setUploadable(image);
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

    @Override
    public void uploadFrame() {
        uploadPresenter.uploadFrame(etTitle.getText().toString());
    }

    @Override
    public void showUploadedFrame() {

    }
}
