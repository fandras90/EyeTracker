package com.eyetracker.mobile.ui.upload;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.R;
import com.eyetracker.mobile.model.Coordinate;
import com.eyetracker.mobile.model.Frame;
import com.eyetracker.mobile.model.Image;
import com.eyetracker.mobile.ui.camera.CameraActivity;
import com.eyetracker.mobile.ui.framelist.FrameListActivity;

import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fabia on 4/25/2016.
 */
public class UploadActivity extends Activity implements UploadScreen {

    public static final String TAG = "ACTIVITY_UPLOAD";
    public static final String EXTRA_RETURNTITLE = "EXTRA_RETURNTITLE";

    @Inject
    UploadPresenter uploadPresenter;

    @Bind(R.id.etTitle)
    EditText etTitle;

    @OnClick(R.id.btnUpload)
    public void setTitle() {
        uploadPresenter.uploadFrame(etTitle.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        EyeTrackerApplication.injector.inject(this);

        ButterKnife.bind(this);
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
    public void showErrorMessage() {
        Toast.makeText(this, "Empty title is not valid", Toast.LENGTH_LONG).show();
    }

    @Override
    public void sendTitle(String title) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(EXTRA_RETURNTITLE, title);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

}
