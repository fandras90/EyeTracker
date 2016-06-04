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

    float threshold = 0.0f;

    private int blurKernel = area / 3;

    private Frame frame;
    private Image image;
    private Coordinate leftCoord, rightCoord;

    private final double[][] configX;
    private final double[][] configY;
    private final ConvolutionMatrix convMatrixX;
    private final ConvolutionMatrix convMatrixY;

    private int[] result;
    private int width, height;

    public ImageInteractor() {
        frame = new Frame();
        image = new Image();
        leftCoord = new Coordinate();
        rightCoord = new Coordinate();

        configX = new double[][] {
                {  -3, 0,  3 },
                { -10, 0, 10 },
                {  -3, 0,  3 }
        };
        configY = new double[][] {
                { -10, -3, -10 },
                {   0,  0,   0 },
                {  10,  3,  10 }
        };

        convMatrixX = new ConvolutionMatrix(3);
        convMatrixX.applyConfig(configX);
        convMatrixX.Factor = 1;
        convMatrixX.Offset = 0;

        convMatrixY = new ConvolutionMatrix(3);
        convMatrixY.applyConfig(configY);
        convMatrixY.Factor = 1;
        convMatrixY.Offset = 0;
    }

    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;

        result = new int[width * height];
    }

    public Frame processMat(byte[] data) {

        if (data == null)
            return null;

        // fix blur kernel
        if (blurKernel % 2 == 0)
            blurKernel++;

        ConvolutionMatrix.computeConvolution3x3(data, result, width, height, convMatrixX, convMatrixY);

//        for (int i = 0; i < gradX.rows(); i++) {
//            for (int j = 0; j < gradX.cols(); j++) {
//                double xVal = gradX.get(i, j)[0];
//                double yVal = gradY.get(i, j)[0];
//                gradientField.put(i, j, xVal, yVal);
//            }
//        }
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

        image.setWidth(width);
        image.setHeight(height);
        image.setUrl("http://www.geek.com/wp-content/uploads/2013/11/eye-track-header.jpg");
        image.setData(result);

        frame.setImage(image);
        frame.setLeftCoordinates(leftCoord);
        frame.setRightCoordinates(rightCoord);
        frame.setCreatedOn(new Date());
        frame.setFilterType("Filter");

        return frame;
    }

}
