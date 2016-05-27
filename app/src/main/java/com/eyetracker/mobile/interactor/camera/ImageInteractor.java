package com.eyetracker.mobile.interactor.camera;

import com.eyetracker.mobile.model.Coordinate;
import com.eyetracker.mobile.model.Frame;
import com.eyetracker.mobile.model.Image;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
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

    int scale = 1;
    int delta = 0;
    int ddepth = CvType.CV_32F; //CV_16S

    float threshold = 0.0f;

    private final boolean isApplyContrast = true;
    private final boolean isApplyGaussian = true;

    private final float alpha = 1.8f;
    private final float beta = 70.0f;

    private int blurKernel = area / 3;

    private final String filterType = "SCHARR";

    private Coordinate leftCoord, rightCoord;

    public Frame processMat(byte[] data) {

        Mat img = Imgcodecs.imdecode(new MatOfByte(data), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);

        leftCoord = new Coordinate();
        rightCoord = new Coordinate();

        if (blurKernel % 2 == 0)
            blurKernel++;

        if (isApplyContrast)
            img.convertTo(img, -1, alpha, beta); // alpha * src + beta

        if (isApplyGaussian)
            Imgproc.GaussianBlur(img, img, new Size(3, 3), 0, 0);

        //Mat gradientFieldHelper = Mat.zeros(img.rows(), img.cols(), CvType.CV_32FC2);
        MatOfPoint2f gradientField = new MatOfPoint2f();

        Mat gradX = new Mat(), gradY = new Mat();
        Mat absGradX = new Mat(), absGradY = new Mat();
        Mat grad = new Mat();

        switch (filterType) {
            case "SCHARR":
                Imgproc.Scharr(img, gradX, ddepth, 1, 0, scale, delta);
                Imgproc.Scharr(img, gradY, ddepth, 0, 1, scale, delta);
            default:
                ;
        }

        Core.convertScaleAbs(gradX, absGradX);
        Core.convertScaleAbs(gradY, absGradY);

        Core.addWeighted(absGradX, 0.5, absGradY, 0.5, 0, grad);

        for (int i = 0; i < gradX.rows(); i++) {
            for (int j = 0; j < gradX.cols(); j++) {
                double xVal = gradX.get(i, j)[0];
                double yVal = gradY.get(i, j)[0];
                gradientField.put(i, j, xVal, yVal);
            }
        }

        //Imgproc.cvtColor(grad, grad, Imgproc.COLOR_GRAY2BGRA);

        double qMax = calculateQMax(gradientField, flowResolution);
        threshold = (float)(qMax / 10) * 3;

        Point MAXVALUE = new Point();
        float maxValue = 0.0f;
        Point MAXCOUNT = new Point();
        int maxCount = 0;
        Point MAXVALUEANDCOUNT = new Point();
        float maxValueAndCount = 0.0f;

        float normQMax = 0.0f;
        int normQCount = 0;

//        for (int i = 0; i < grad.rows(); i++) {
//            for (int j = 0; j < grad.cols(); j++) {
//                Point X = new Point(j, i);
//                float SUM = 0.0f;
//                int COUNT = 0;
//                float SUMCOUNT = 0.0f;
//
//                for (int m = Math.max(0, i - area); m < Math.min(grad.rows(), i + area); m++) {
//                    for (int n = Math.max(0, j - area); m < Math.min(grad.cols(), j + area); m++) {
//                        Point Y = new Point(n, m);
//                        Point Q = new Point(gradientField.get(n, m)[0], gradientField.get(n, m)[1]); //// Attempt to read from null array
//
//                        double normQ = Math.sqrt(Q.x * Q.x + Q.y * Q.y);
//
//                        if (normQ > threshold) {
////                            Point QNorm = new Point(Q.x / normQ, Q.y / normQ);
//                            Point YX = new Point(Y.x - X.x, Y.y - X.y);
//
//                            double normYX = Math.sqrt(YX.x * YX.x + YX.y * YX.y);
//
//                            if (normYX > 1.0f) {
//                                Point YXnorm = new Point(YX.x / normYX, YX.y / normYX);
//
//                                double S = Math.abs(Q.dot(YXnorm));
//                                double cosa = S / normQ;
//
//                                if (cosa > 0.990) {
//                                    SUM += S;
//                                    COUNT++;
//
//                                    SUMCOUNT += SUM * COUNT;
//                                }
//                            }
//                        }
//                    }
//
//                    if (SUM > maxValue) {
//                        maxValue = SUM;
//                        MAXVALUE = X;
//                    }
//
//                    if (COUNT > maxCount) {
//                        maxCount = COUNT;
//                        MAXCOUNT = X;
//                    }
//
//                    if (SUMCOUNT > maxValueAndCount) {
//                        maxValueAndCount = SUMCOUNT;
//                        MAXVALUEANDCOUNT = X;
//                    }
//
//                }
//            }
//        }

        Imgproc.circle(grad, MAXVALUEANDCOUNT, area, new Scalar(255, 0, 255));

        Core.transpose(grad, grad);
        Core.flip(grad, grad, 0);
        Core.flip(grad, grad, 1);

        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".png", grad, mob);

        leftCoord.setxCoord(10.3f);
        leftCoord.setyCoord(20.3f);
        rightCoord.setxCoord(30.3f);
        rightCoord.setyCoord(40.3f);

        Frame ret = new Frame();
        Image image = new Image();
        image.setWidth(mob.width());
        image.setHeight(mob.height());
        image.setUrl("http://www.geek.com/wp-content/uploads/2013/11/eye-track-header.jpg");
        image.setData(mob.toArray());

        ret.setImage(image);
        ret.setLeftCoordinates(leftCoord);
        ret.setRightCoordinates(rightCoord);
        ret.setCreatedOn(new Date());
        ret.setFilterType("Filter");

        return ret;
    }

    private double calculateQMax(Mat grad, int res) {
        double max = 0.0f;
        for (int i = 0; i < grad.rows(); i += res) {
            for (int j = 0; j < grad.cols(); j += res) {
                Point Q = new Point(grad.get(j, i)[0], grad.get(j, i)[1]);
                double normQ = Math.sqrt(Q.x * Q.x + Q.y * Q.y);

                if (normQ > max)
                    max = normQ;
            }
        }
        return max;
    }

}
