package com.eyetracker.mobile.test;

import com.eyetracker.mobile.BuildConfig;
import com.eyetracker.mobile.ui.camera.CameraPresenter;
import com.eyetracker.mobile.ui.camera.CameraScreen;
import com.eyetracker.mobile.utils.RobolectricDaggerTestRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static com.eyetracker.mobile.TestHelper.setTestInjector;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by fabia on 5/21/2016.
 */
@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class CameraTest {

    private CameraPresenter cameraPresenter;
    private CameraScreen cameraScreen;

    private int width = 200, height = 400;

//    static {
//        if (!OpenCVLoader.initDebug()) {
//        } else {
//            System.loadLibrary("opencv_java3");
//        }
//    }

    @Before
    public void setup() throws Exception {
        setTestInjector();

        cameraScreen = mock(CameraScreen.class);
        cameraPresenter = new CameraPresenter();
        cameraPresenter.attachScreen(cameraScreen);

        cameraPresenter.initialize(width, height);
    }

    @Test
    public void testProcessImage() {
        byte[] data = new byte[]{};
        cameraPresenter.processRawImage(data);
        ArgumentCaptor<byte[]> imageCaptor = ArgumentCaptor.forClass(
                byte[].class);
        verify(cameraScreen).showProcessedImage(imageCaptor.capture());
    }

    @Test
    public void testDiscardImage() {
        cameraPresenter.discard();
        verify(cameraScreen).discardResults();
    }

    @Test
    public void testUploadImage() {
        cameraPresenter.upload();
        ArgumentCaptor<byte[]> imageCaptor = ArgumentCaptor.forClass(
                byte[].class);
        verify(cameraScreen).uploadFrame(imageCaptor.capture());
    }

    @After
    public void tearDown() {
        cameraPresenter.detachScreen();
    }

}
