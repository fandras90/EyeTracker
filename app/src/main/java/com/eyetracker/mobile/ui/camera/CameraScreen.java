package com.eyetracker.mobile.ui.camera;

import org.opencv.core.Size;

/**
 * Created by fabia on 4/22/2016.
 */
public interface CameraScreen {
    void showEyecenters(Size left, Size right);

    void discardResults();
}
