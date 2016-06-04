package com.eyetracker.mobile.ui.camera;

import com.eyetracker.mobile.model.Frame;

/**
 * Created by fabia on 4/22/2016.
 */
public interface CameraScreen {

    void showProcessedImage(int[] image);

    void discardResults();

    void uploadFrame(Frame frame);

    void showNetworkError(String errorMsg);

    void showUploadSuccess();

}
