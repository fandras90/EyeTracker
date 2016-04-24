package com.eyetracker.mobile.ui.camera;

import com.eyetracker.mobile.ui.Presenter;

import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by fabia on 4/22/2016.
 */
public class CameraPresenter extends Presenter<CameraScreen> {

    private Mat mRgba;
    private Mat mGray;
    private Mat mIntermediate;
    private Mat mTranspose;
    private int width;
    private int height;

    @Override
    public void attachScreen(CameraScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void setMats() {
        mRgba = new Mat(height, width, CvType.CV_8UC4);
        mIntermediate = new Mat(height, width, CvType.CV_8UC4);
        mGray = new Mat(height, width, CvType.CV_8UC1);

        mTranspose = new Mat(width, width, CvType.CV_8UC4);  // NOTE width,width is NOT a typo

    }

    public Mat calculateEyeCenters(CvCameraViewFrame inputFrame) {
//        Imgproc.Canny(inputFrame.gray(), mIntermediate, 80, 100);
  //      Imgproc.cvtColor(mIntermediate, mRgba, Imgproc.COLOR_GRAY2RGBA, 4);


        mRgba = inputFrame.rgba();
        // Rotate mRgba 90 degrees
        Core.transpose(mRgba, mTranspose);
        Imgproc.resize(mTranspose, mIntermediate, mIntermediate.size(), 0, 0, 0);
        Core.flip(mIntermediate, mRgba, 1);
        return mRgba;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
