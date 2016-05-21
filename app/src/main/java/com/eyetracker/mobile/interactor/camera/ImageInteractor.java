package com.eyetracker.mobile.interactor.camera;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

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

    public Mat processMat(Mat data) {

        if (blurKernel % 2 == 0)
            blurKernel++;

        if (isApplyContrast)
            data.convertTo(data, -1, alpha, beta); // alpha * src + beta

        if (isApplyGaussian)
            Imgproc.GaussianBlur(data, data, new Size(3, 3), 0, 0);

        Core.transpose(data, data);
        Core.flip(data, data, 1);

        return data;
    }

}
