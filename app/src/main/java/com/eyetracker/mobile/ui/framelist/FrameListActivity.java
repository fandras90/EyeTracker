package com.eyetracker.mobile.ui.framelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.R;
import com.eyetracker.mobile.model.Frame;
import com.eyetracker.mobile.model.Frames;
import com.eyetracker.mobile.ui.camera.CameraActivity;
import com.eyetracker.mobile.ui.framedetail.FrameDetailActivity;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrameListActivity extends AppCompatActivity implements FrameListScreen {

    public static final String TAG = "ACTIVITY_FRAMELIST";
    public static final String EXTRA_FRAMEID = "EXTRA_FRAMEID";
    private static final String name = "Frame list activity";

    private Tracker tracker;

    private List<Frame> frameList;
    private FrameAdapter adapter;

    @Inject
    FrameListPresenter frameListPresenter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.frames_swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.frames_recycler_view)
    RecyclerView recyclerViewFrames;

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
    public void showDetails(Frame frame) {
        Intent intent = new Intent(FrameListActivity.this, FrameDetailActivity.class);
        intent.putExtra(EXTRA_FRAMEID, frame.getId());
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

        if (frameList.isEmpty()) {
            recyclerViewFrames.setVisibility(View.GONE);
            //tvEmpty.setVisibility(View.VISIBLE);
        } else {
            recyclerViewFrames.setVisibility(View.VISIBLE);
            //tvEmpty.setVisibility(View.GONE);
        }
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

        // Obtain the shared Tracker instance.
        EyeTrackerApplication application = (EyeTrackerApplication) getApplication();
        tracker = application.getDefaultTracker();

        EyeTrackerApplication.injector.inject(this);

        ButterKnife.bind(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewFrames.setLayoutManager(llm);

        frameList = new ArrayList();
        adapter = new FrameAdapter(this, frameList);
        recyclerViewFrames.setAdapter(adapter);

        recyclerViewFrames.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                frameListPresenter.showDetails(adapter.getFrame(position));
            }
        }));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                frameListPresenter.refreshFrames();
            }
        });
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

        Log.i(TAG, "Setting screen name: " + name);
        tracker.setScreenName("Image~" + name);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
