package com.eyetracker.mobile.ui.camera;

import org.opencv.core.Mat;

/**
 * Created by fabia on 4/22/2016.
 */
public interface CameraScreen {

    void showProcessedImage(byte[] image);

    void discardResults();

    void uploadFrame(Mat mat);

}
