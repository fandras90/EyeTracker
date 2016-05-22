package com.eyetracker.mobile.ui.camera;

import com.eyetracker.mobile.EyeTrackerApplication;
import com.eyetracker.mobile.interactor.camera.ImageInteractor;
import com.eyetracker.mobile.ui.Presenter;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.inject.Inject;

/**
 * Created by fabia on 4/22/2016.
 */
public class CameraPresenter extends Presenter<CameraScreen> {

    private Mat mRgba;
    private Mat mGray;
    private Mat mIntermediate;
    private int width;
    private int height;

    @Inject
    ImageInteractor imageInteractor;

    @Override
    public void attachScreen(CameraScreen screen) {
        super.attachScreen(screen);

        EyeTrackerApplication.injector.inject(this);

//        if (!OpenCVLoader.initDebug()) {
//            Log.e("TEST", "Cannot connect to OpenCV Manager");
//        }
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void initialize(int width, int height) {
        this.width = width;
        this.height = height;

        mRgba = new Mat(height, width, CvType.CV_8UC4);
        mIntermediate = new Mat(height, width, CvType.CV_8UC4);
        mGray = new Mat(height, width, CvType.CV_8UC1);
    }

    public void processRawImage(byte[] data) {
        //mRgba.put(0, 0, data);
        mRgba = Imgcodecs.imdecode(new MatOfByte(data), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
        mIntermediate = imageInteractor.processMat(mRgba);

        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".png", mIntermediate, mob);

        byte[] ret = mob.toArray();

        screen.showProcessedImage(ret);
    }

    public void discard() {
        screen.discardResults();
    }

    public void upload() {
        screen.uploadFrame(mIntermediate);
    }

}

