package com.eyetracker.mobile.interactor.camera;

import android.util.Size;

import com.eyetracker.mobile.model.Coordinate;
import com.eyetracker.mobile.model.Frame;
import com.eyetracker.mobile.model.Image;

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
    //int ddepth = CvType.CV_32F; //CV_16S

    float threshold = 0.0f;

    private final boolean isApplyContrast = true;
    private final boolean isApplyGaussian = true;

    private final float alpha = 1.8f;
    private final float beta = 70.0f;

    private int blurKernel = area / 3;

    private final String filterType = "SCHARR";

    private Coordinate leftCoord = new Coordinate(), rightCoord = new Coordinate();

    public Frame processMat(byte[] data, int width, int height) {

        if (data == null)
            return null;

        // init structures
        Frame frame = new Frame();
        leftCoord = new Coordinate();
        rightCoord = new Coordinate();

        int size = width * height;
        int offset = size;

        int[] pixels = new int[size / 8 * 12];

        // fix blur kernel
        if (blurKernel % 2 == 0)
            blurKernel++;

        int yolo = data.length;
        int p;
        for (int i = 0; i < yolo; i++) {
            p = data[i] & 0xFF;
            pixels[i] = 0xff000000 | p << 16 | p << 8 | p;
//            if (isApplyContrast) {
//                pixels[i] *= alpha;
//                pixels[i] += beta;
//            }
        }

//        if (isApplyGaussian)
//            Imgproc.GaussianBlur(img, img, new Size(3, 3), 0, 0);
//
        Size[] gradientField = new Size[size];
//
//        Mat gradX = new Mat(), gradY = new Mat();
//        Mat absGradX = new Mat(), absGradY = new Mat();
//        Mat grad = new Mat();
//
//        switch (filterType) {
//            case "SCHARR":
//                Imgproc.Scharr(img, gradX, ddepth, 1, 0, scale, delta);
//                Imgproc.Scharr(img, gradY, ddepth, 0, 1, scale, delta);
//            default:
//                ;
//        }
//
//        Core.convertScaleAbs(gradX, absGradX);
//        Core.convertScaleAbs(gradY, absGradY);
//
//        Core.addWeighted(absGradX, 0.5, absGradY, 0.5, 0, grad);
//
//        for (int i = 0; i < gradX.rows(); i++) {
//            for (int j = 0; j < gradX.cols(); j++) {
//                double xVal = gradX.get(i, j)[0];
//                double yVal = gradY.get(i, j)[0];
//                gradientField.put(i, j, xVal, yVal);
//            }
//        }
//
//        Imgproc.cvtColor(grad, grad, Imgproc.COLOR_GRAY2BGRA);
//
//        double qMax = calculateQMax(gradientField, flowResolution);
//        threshold = (float)(qMax / 10) * 3;
//
//        Point MAXVALUE = new Point();
//        float maxValue = 0.0f;
//        Point MAXCOUNT = new Point();
//        int maxCount = 0;
//        Point MAXVALUEANDCOUNT = new Point();
//        float maxValueAndCount = 0.0f;
//
//        float normQMax = 0.0f;
//        int normQCount = 0;
//
////        for (int i = 0; i < grad.rows(); i++) {
////            for (int j = 0; j < grad.cols(); j++) {
////                Point X = new Point(j, i);
////                float SUM = 0.0f;
////                int COUNT = 0;
////                float SUMCOUNT = 0.0f;
////
////                for (int m = Math.max(0, i - area); m < Math.min(grad.rows(), i + area); m++) {
////                    for (int n = Math.max(0, j - area); m < Math.min(grad.cols(), j + area); m++) {
////                        Point Y = new Point(n, m);
////                        Point Q = new Point(gradientField.get(n, m)[0], gradientField.get(n, m)[1]); //// Attempt to read from null array
////
////                        double normQ = Math.sqrt(Q.x * Q.x + Q.y * Q.y);
////
////                        if (normQ > threshold) {
//////                            Point QNorm = new Point(Q.x / normQ, Q.y / normQ);
////                            Point YX = new Point(Y.x - X.x, Y.y - X.y);
////
////                            double normYX = Math.sqrt(YX.x * YX.x + YX.y * YX.y);
////
////                            if (normYX > 1.0f) {
////                                Point YXnorm = new Point(YX.x / normYX, YX.y / normYX);
////
////                                double S = Math.abs(Q.dot(YXnorm));
////                                double cosa = S / normQ;
////
////                                if (cosa > 0.990) {
////                                    SUM += S;
////                                    COUNT++;
////
////                                    SUMCOUNT += SUM * COUNT;
////                                }
////                            }
////                        }
////                    }
////
////                    if (SUM > maxValue) {
////                        maxValue = SUM;
////                        MAXVALUE = X;
////                    }
////
////                    if (COUNT > maxCount) {
////                        maxCount = COUNT;
////                        MAXCOUNT = X;
////                    }
////
////                    if (SUMCOUNT > maxValueAndCount) {
////                        maxValueAndCount = SUMCOUNT;
////                        MAXVALUEANDCOUNT = X;
////                    }
////
////                }
////            }
////        }
//
//        Imgproc.circle(grad, MAXVALUEANDCOUNT, area, new Scalar(255, 0, 255));
//
//        Core.transpose(grad, grad);
//        Core.flip(grad, grad, 0);
//        Core.flip(grad, grad, 1);
//
//        MatOfByte mob = new MatOfByte();
//        Imgcodecs.imencode(".png", grad, mob);
//
//        leftCoord.setxCoord(10.3f);
//        leftCoord.setyCoord(20.3f);
//        rightCoord.setxCoord(30.3f);
//        rightCoord.setyCoord(40.3f);

        Image image = new Image();
        image.setWidth(width);
        image.setHeight(height);
        image.setUrl("http://www.geek.com/wp-content/uploads/2013/11/eye-track-header.jpg");
        image.setData(pixels);

        frame.setImage(image);
        frame.setLeftCoordinates(leftCoord);
        frame.setRightCoordinates(rightCoord);
        frame.setCreatedOn(new Date());
        frame.setFilterType("Filter");

        return frame;
    }

}
