package com.eyetracker.mobile.ui.framelist;

import com.eyetracker.mobile.model.Frame;
import com.eyetracker.mobile.model.Frames;

/**
 * Created by fabia on 4/20/2016.
 */
public interface FrameListScreen {

    void startCamera();

    void showDetails(Frame frame);

    void showFrames(Frames frames);

    void showNetworkError(String errorMsg);

}
