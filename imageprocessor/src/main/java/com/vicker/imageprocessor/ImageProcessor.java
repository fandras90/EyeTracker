package com.vicker.imageprocessor;

/**
 * Created by fabia on 6/10/2016.
 */
public class ImageProcessor {

    static {
        System.loadLibrary("imgproc");
    }

    private int width, height;

    public ImageProcessor(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int[] processImage(byte[] yuv) {
        int[] rgba = new int[]{};
        processImageNative(width, height, yuv, rgba);
        return rgba;
    }

    private native void processImageNative(int width, int height, byte[] yuv, int[] rgba);

}
