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
import org.opencv.core.Mat;
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
        ArgumentCaptor<byte[]> frameCaptor = ArgumentCaptor.forClass(
                byte[].class);
        verify(cameraScreen).showProcessedImage(frameCaptor.capture());
    }

    @Test
    public void testDiscardImage() {
        cameraPresenter.discard();
        verify(cameraScreen).discardResults();
    }

    @Test
    public void testUploadImage() {
        Mat mat = new Mat();
        cameraPresenter.upload();
        verify(cameraScreen).uploadFrame(mat);
    }

    @After
    public void tearDown() {
        cameraPresenter.detachScreen();
    }

}
