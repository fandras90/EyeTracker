package com.eyetracker.mobile.ui.camera;

import com.eyetracker.mobile.ui.Presenter;

import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by fabia on 4/22/2016.
 */
public class CameraPresenter extends Presenter<CameraScreen> {

    private Mat mRgba;
    private Mat mGray;
    private Mat mIntermediateMat;
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
        mIntermediateMat = new Mat(height, width, CvType.CV_8UC4);
        mGray = new Mat(height, width, CvType.CV_8UC1);
    }

    public Mat calculateEyeCenters(CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();
        Imgproc.Canny(inputFrame.gray(), mIntermediateMat, 80, 100);
        Imgproc.cvtColor(mIntermediateMat, mRgba, Imgproc.COLOR_GRAY2RGBA, 4);
        return mRgba;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
