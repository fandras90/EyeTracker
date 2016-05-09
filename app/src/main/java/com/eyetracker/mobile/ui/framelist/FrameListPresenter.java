package com.eyetracker.mobile.ui.framelist;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.di.Network;
import com.eyetracker.mobile.interactor.frame.FrameInteractor;
import com.eyetracker.mobile.interactor.frame.event.GetFramesEvent;
import com.eyetracker.mobile.ui.Presenter;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

/**
 * Created by fabia on 4/20/2016.
 */
public class FrameListPresenter extends Presenter<FrameListScreen> {

    @Inject
    @Network
    Executor networkExecutor;

    @Inject
    FrameInteractor frameInteractor;

    @Override
    public void attachScreen(FrameListScreen screen) {
        super.attachScreen(screen);
        EyeTrackerApplication.injector.inject(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachScreen() {
        EventBus.getDefault().unregister(this);
        super.detachScreen();
    }

    public void refreshFrames() {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                frameInteractor.getFrames();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetFramesEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showNetworkError(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                screen.showFrames(event.getResult());
            }
        }
    }

    public void startCamera() {
        if (screen != null)
            screen.startCamera();
        else
            ; // TODO ERROR?
    }

}
