package com.eyetracker.mobile.ui.framelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.R;
import com.eyetracker.mobile.model.Frame;
import com.eyetracker.mobile.model.Frames;
import com.eyetracker.mobile.ui.camera.CameraActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrameListActivity extends AppCompatActivity implements FrameListScreen {

    public static final String TAG = "ACTIVITY_FRAMELIST";

    private List<Frame> frameList;
    private FrameAdapter adapter;

    @Inject
    FrameListPresenter frameListPresenter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.frames_swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @OnClick(R.id.fab)
    public void onClick(View view) {
        frameListPresenter.startCamera();
    }

    @Override
    public void startCamera() {
        Intent intent = new Intent(FrameListActivity.this, CameraActivity.class);
        startActivity(intent);
    }

    @Override
    public void showFrames(Frames frames) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }

        frameList.clear();
        frameList.addAll(frames.getFrames());
        adapter.notifyDataSetChanged();

//        if (frameList.isEmpty()) {
//            recyclerViewArtists.setVisibility(View.GONE);
//            tvEmpty.setVisibility(View.VISIBLE);
//        } else {
//            recyclerViewArtists.setVisibility(View.VISIBLE);
//            tvEmpty.setVisibility(View.GONE);
//        }
    }

    @Override
    public void showNetworkError(String errorMsg) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
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
        frameListPresenter.detachScreen();
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        frameListPresenter.refreshFrames();
    }
}
