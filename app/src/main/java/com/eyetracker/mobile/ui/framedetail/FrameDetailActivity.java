package com.eyetracker.mobile.ui.framedetail;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.R;
import com.eyetracker.mobile.model.Frame;
import com.eyetracker.mobile.ui.framelist.FrameListActivity;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fabia on 4/25/2016.
 */
public class FrameDetailActivity extends Activity implements FrameDetailScreen {

    public static final String TAG = "ACTIVITY_FRAMEDETAIL";
    private static final String name = "Frame detail activity";

    private Tracker tracker;

    @Bind(R.id.ivImage)
    ImageView ivImage;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.tvLeftCoord)
    TextView tvLeftCoord;
    @Bind(R.id.tvRightCoord)
    TextView tvRightCoord;
    @Bind(R.id.tvAlgo)
    TextView tvAlgo;

    @Inject
    FrameDetailPresenter frameDetailPresenter;

    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framedetail);

        // Obtain the shared Tracker instance.
        EyeTrackerApplication application = (EyeTrackerApplication) getApplication();
        tracker = application.getDefaultTracker();

        EyeTrackerApplication.injector.inject(this);

        ButterKnife.bind(this);

        id = getIntent().getLongExtra(FrameListActivity.EXTRA_FRAMEID, -1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        frameDetailPresenter.attachScreen(this);

        frameDetailPresenter.getFrame(id);
    }

    @Override
    protected void onStop() {
        frameDetailPresenter.detachScreen();
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i(TAG, "Setting screen name: " + name);
        tracker.setScreenName("Image~" + name);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void showFrame(Frame f) {
        Glide.with(this).load(f.getImage().getUrl()).into(ivImage);
        tvTitle.setText(f.getTitle());
        tvAlgo.setText(f.getFilterType());
        tvLeftCoord.setText(f.getLeftCoordinates().getxCoord() + ": " + f.getLeftCoordinates().getyCoord());
        tvRightCoord.setText(f.getRightCoordinates().getxCoord() + ": " + f.getRightCoordinates().getyCoord());
    }

    @Override
    public void showNetworkError(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
    }
}
