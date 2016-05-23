package com.eyetracker.mobile.ui.camera;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.di.Network;
import com.eyetracker.mobile.interactor.camera.ImageInteractor;
import com.eyetracker.mobile.interactor.frame.FrameInteractor;
import com.eyetracker.mobile.interactor.frame.event.UploadFrameEvent;
import com.eyetracker.mobile.model.Frame;
import com.eyetracker.mobile.ui.Presenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

/**
 * Created by fabia on 4/22/2016.
 */
public class CameraPresenter extends Presenter<CameraScreen> {

    private Frame actualFrame;

    @Inject
    @Network
    Executor networkExecutor;

    @Inject
    ImageInteractor imageInteractor;

    @Inject
    FrameInteractor frameInteractor;

    @Override
    public void attachScreen(CameraScreen screen) {
        super.attachScreen(screen);

        EyeTrackerApplication.injector.inject(this);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void processRawImage(byte[] data) {
        actualFrame = imageInteractor.processMat(data);

        screen.showProcessedImage(actualFrame.getImage().getData());
    }

    public void discard() {
        screen.discardResults();
    }

    public void upload() {
        screen.uploadFrame(actualFrame);
    }

    public void startUpload(String title) {
        actualFrame.setTitle(title);
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                frameInteractor.uploadFrame(actualFrame);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final UploadFrameEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showNetworkError(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                screen.showUploadSuccess();
            }
        }
    }

}

