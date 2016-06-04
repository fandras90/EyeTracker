package com.eyetracker.mobile.interactor.camera;

/**
 * Created by fabia on 6/4/2016.
 */
public class ConvolutionMatrix {

    public static final int SIZE = 3;

    public double[][] Matrix;
    public double Factor = 16;
    public double Offset = 0;

    private static byte[][] pixels;

    public ConvolutionMatrix(int size) {
        Matrix = new double[size][size];
        pixels = new byte[size][size];
    }

    public void setAll(double value) {
        for (int x = 0; x < SIZE; ++x) {
            for (int y = 0; y < SIZE; ++y) {
                Matrix[x][y] = value;
            }
        }
    }

    public void applyConfig(double[][] config) {
        for(int x = 0; x < SIZE; ++x) {
            for(int y = 0; y < SIZE; ++y) {
                Matrix[x][y] = config[x][y];
            }
        }
    }

    public static void computeConvolution3x3(byte[] src, int[] result, int width, int height, ConvolutionMatrix matrixX, ConvolutionMatrix matrixY) {
        int lum;
        int sumX, sumY;

        for(int y = 0; y < height - 2; y++) {
            for(int x = 0; x < width - 2; x++) {

                // get pixel matrix
                for(int i = 0; i < SIZE; ++i) {
                    for(int j = 0; j < SIZE; ++j) {
                        pixels[i][j] = src[(y + j) * width + (x + i)];
                    }
                }

                // init color sum
                lum = sumX = sumY = 0;

                // get sum of RGB on matrix
                for(int i = 0; i < SIZE; ++i) {
                    for(int j = 0; j < SIZE; ++j) {
                        sumX += (pixels[i][j] * matrixX.Matrix[i][j]);
                        sumY += (pixels[i][j] * matrixY.Matrix[i][j]);
                    }
                }

                // get final Red
                lum += (int)((sumX / matrixX.Factor + matrixX.Offset) * 0.5);
                lum += (int)((sumY / matrixY.Factor + matrixY.Offset) * 0.5);
                lum = (lum < 0) ? 0 : (lum > 255) ? 255 : lum;

                // apply new pixel
                result[y * width + x] = 0xff000000 | lum << 16 | lum << 8 | lum;
            }
        }
    }

}
