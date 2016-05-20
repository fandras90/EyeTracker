package com.eyetracker.mobile.ui.framedetail;

import com.eyetracker.mobile.model.Frame;

/**
 * Created by fabia on 4/25/2016.
 */
public interface FrameDetailScreen {

    void showFrame(Frame f);

    void showNetworkError(String errorMsg);

}
