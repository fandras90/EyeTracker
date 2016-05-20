package com.eyetracker.mobile.ui.framedetail;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.di.Network;
import com.eyetracker.mobile.interactor.frame.FrameInteractor;
import com.eyetracker.mobile.interactor.frame.event.GetFrameEvent;
import com.eyetracker.mobile.interactor.frame.event.GetFramesEvent;
import com.eyetracker.mobile.model.Frame;
import com.eyetracker.mobile.ui.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

/**
 * Created by fabia on 4/25/2016.
 */
public class FrameDetailPresenter extends Presenter<FrameDetailScreen> {

    @Inject
    @Network
    Executor networkExecutor;

    @Inject
    FrameInteractor frameInteractor;

    @Override
    public void attachScreen(FrameDetailScreen screen) {
        super.attachScreen(screen);
        EyeTrackerApplication.injector.inject(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachScreen() {
        EventBus.getDefault().unregister(this);
        super.detachScreen();
    }

    public void getFrame(final Long id) {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                frameInteractor.getFrame(id);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetFrameEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showNetworkError(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                screen.showFrame(event.getResult());
            }
        }
    }

    public void showFrame(Frame f) {
        screen.showFrame(f);
    }

}
