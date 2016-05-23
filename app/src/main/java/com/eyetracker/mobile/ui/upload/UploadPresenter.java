package com.eyetracker.mobile.ui.upload;

import com.eyetracker.mobile.ui.Presenter;

/**
 * Created by fabia on 4/25/2016.
 */
public class UploadPresenter extends Presenter<UploadScreen> {

    @Override
    public void attachScreen(UploadScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void uploadFrame(String text) {
        if (text.isEmpty())
            screen.showErrorMessage();
        else
            screen.sendTitle(text);
    }

}
