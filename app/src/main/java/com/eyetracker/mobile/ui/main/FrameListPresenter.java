package com.eyetracker.mobile.ui.main;

import com.eyetracker.mobile.ui.Presenter;

/**
 * Created by fabia on 4/20/2016.
 */
public class FrameListPresenter extends Presenter<FrameListScreen> {

    @Override
    public void attachScreen(FrameListScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void startCamera() {
        if (screen != null)
            screen.startCamera();
        else
            ; // TODO ERROR?
    }
}
