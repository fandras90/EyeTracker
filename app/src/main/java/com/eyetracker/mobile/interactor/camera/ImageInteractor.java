package com.eyetracker.mobile.interactor.camera;

import com.eyetracker.mobile.model.Coordinate;
import com.eyetracker.mobile.model.Frame;
import com.eyetracker.mobile.model.Image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.Date;

/**
 * Created by fabia on 4/24/2016.
 */
public class ImageInteractor {

    private final int flowResolution = 2;
    private final int area = 30;
    private final int areaSmall = area / 3;
    private final float EPSYLON = 0.001f;

    private final boolean isApplyContrast = true;
    private final boolean isApplyGaussian = true;

    private final float alpha = 1.8f;
    private final float beta = 70.0f;

    private int blurKernel = area / 3;

    private Coordinate leftCoord, rightCoord;

    public Frame processMat(byte[] data) {

        Mat init = Imgcodecs.imdecode(new MatOfByte(data), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
        leftCoord = new Coordinate();
        rightCoord = new Coordinate();

        if (blurKernel % 2 == 0)
            blurKernel++;

        if (isApplyContrast)
            init.convertTo(init, -1, alpha, beta); // alpha * src + beta

        if (isApplyGaussian)
            Imgproc.GaussianBlur(init, init, new Size(3, 3), 0, 0);

        Core.transpose(init, init);
        Core.flip(init, init, 0);
        Core.flip(init, init, 1);

        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".png", init, mob);

        leftCoord.setxCoord(10.3f);
        leftCoord.setyCoord(20.3f);
        rightCoord.setxCoord(30.3f);
        rightCoord.setyCoord(40.3f);

        Frame ret = new Frame();
        Image img = new Image();
        img.setWidth(mob.width());
        img.setHeight(mob.height());
        img.setUrl("http://www.geek.com/wp-content/uploads/2013/11/eye-track-header.jpg");
        img.setData(mob.toArray());

        ret.setImage(img);
        ret.setLeftCoordinates(leftCoord);
        ret.setRightCoordinates(rightCoord);
        ret.setCreatedOn(new Date());
        ret.setFilterType("Filter");

        return ret;
    }

}
